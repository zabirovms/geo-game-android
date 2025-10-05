package com.geogamenative.ui.game

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.geogamenative.databinding.ActivityGameBinding
import com.geogamenative.utils.StringResourceProvider
import com.geogamenative.viewmodels.GameViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GameActivity : AppCompatActivity() {

  private lateinit var binding: ActivityGameBinding
  private lateinit var viewModel: GameViewModel

  @Inject
  lateinit var stringProvider: StringResourceProvider

  private var continent: String = "asia"
  private var mode: String = "countryName"

  @SuppressLint("SetJavaScriptEnabled")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityGameBinding.inflate(layoutInflater)
    setContentView(binding.root)

    // Get parameters from intent
    continent = intent.getStringExtra("continent") ?: "asia"
    mode = intent.getStringExtra("mode") ?: "countryName"

    viewModel = ViewModelProvider(this)[GameViewModel::class.java]

    // Initialize WebView with Leaflet
    val webView: WebView = binding.mapWebView
    val settings: WebSettings = webView.settings
    settings.javaScriptEnabled = true
    settings.domStorageEnabled = true
    settings.allowFileAccess = true
    settings.useWideViewPort = true
    settings.loadWithOverviewMode = true
    webView.webChromeClient = WebChromeClient()
    webView.addJavascriptInterface(MapBridge(), "Android")
    webView.loadUrl("file:///android_asset/map/index.html")

    setupUI()
    observeViewModel()

    // Start the game
    viewModel.startGame(continent, mode)
  }

  private fun setupUI() {
    binding.closeButton.text = stringProvider.getString("actions.close")
    binding.closeButton.setOnClickListener { finish() }

    binding.restartButton.text = stringProvider.getString("actions.restart")
    binding.restartButton.setOnClickListener { viewModel.restartGame() }
  }

  private fun observeViewModel() {
    viewModel.currentCountry.observe(this) { country ->
      country?.let {
        updateQuestionText(it)
        evaluateJs(
          "centerMap(${it.latitude}, ${it.longitude}, 4); addMarker(${it.latitude}, ${it.longitude}, '${escapeJs(it.name)}');"
        )
      }
    }

    viewModel.score.observe(this) { score ->
      binding.scoreText.text = "${stringProvider.getString("game.score")}: $score"
    }

    viewModel.timeLeft.observe(this) { timeLeft ->
      binding.timeText.text = "${stringProvider.getString("game.time")}: ${timeLeft}s"
    }

    viewModel.gameActive.observe(this) { isActive ->
      if (!isActive) showGameOverDialog()
    }

    viewModel.selectedLocation.observe(this) { location ->
      location?.let { (lat, lng) ->
        evaluateJs("addMarker($lat, $lng, '${escapeJs(stringProvider.getString("game.question"))}');")
      }
    }
  }

  private fun updateQuestionText(country: com.geogamenative.data.model.Country) {
    val questionText = when (mode) {
      "countryName" -> "${stringProvider.getString("modes.countryName")}: ${country.name}"
      "capital" -> "${stringProvider.getString("modes.capital")}: ${country.capital}"
      "flag" -> "${stringProvider.getString("modes.flag")}: ${country.name}"
      else -> country.name
    }
    binding.questionText.text = questionText
  }

  private fun showGameOverDialog() {
    val score = viewModel.score.value ?: 0
    androidx.appcompat.app.AlertDialog.Builder(this)
      .setTitle(stringProvider.getString("game.timeUp"))
      .setMessage("${stringProvider.getString("game.finalScore")}: $score")
      .setPositiveButton(stringProvider.getString("actions.restart")) { _, _ ->
        viewModel.restartGame()
      }
      .setNegativeButton(stringProvider.getString("actions.close")) { _, _ ->
        finish()
      }
      .setCancelable(false)
      .show()
  }

  private fun evaluateJs(script: String) {
    binding.mapWebView.post {
      binding.mapWebView.evaluateJavascript(script, null)
    }
  }

  private fun escapeJs(input: String): String = input.replace("'", "\\'")

  inner class MapBridge {
    @JavascriptInterface
    fun onMapClick(lat: Double, lng: Double) {
      runOnUiThread {
        viewModel.onMapClick(lat, lng)
      }
    }
  }
}
