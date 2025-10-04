package com.geogamenative.ui.game

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.geogamenative.databinding.ActivityGameBinding
import com.geogamenative.utils.StringResourceProvider
import com.geogamenative.viewmodels.GameViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class GameActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityGameBinding
    private lateinit var viewModel: GameViewModel
    
    @Inject
    lateinit var stringProvider: StringResourceProvider
    
    private var continent: String = "asia"
    private var mode: String = "countryName"
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // Get parameters from intent
        continent = intent.getStringExtra("continent") ?: "asia"
        mode = intent.getStringExtra("mode") ?: "countryName"
        
        viewModel = ViewModelProvider(this)[GameViewModel::class.java]
        
        setupUI()
        observeViewModel()
        
        // Start the game
        viewModel.startGame(continent, mode)
    }
    
    private fun setupUI() {
        // Setup header
        binding.closeButton.text = stringProvider.getString("actions.close")
        binding.closeButton.setOnClickListener {
            finish()
        }
        
        // Setup map click listener
        binding.mapView.setOnMapClickListener { latLng ->
            viewModel.onMapClick(latLng.latitude, latLng.longitude)
        }
        
        // Setup restart button
        binding.restartButton.text = stringProvider.getString("actions.restart")
        binding.restartButton.setOnClickListener {
            viewModel.restartGame()
        }
    }
    
    private fun observeViewModel() {
        // Observe current country
        viewModel.currentCountry.observe(this) { country ->
            country?.let {
                updateQuestionText(it)
                centerMapOnCountry(it)
            }
        }
        
        // Observe score
        viewModel.score.observe(this) { score ->
            binding.scoreText.text = "${stringProvider.getString("game.score")}: $score"
        }
        
        // Observe time left
        viewModel.timeLeft.observe(this) { timeLeft ->
            binding.timeText.text = "${stringProvider.getString("game.time")}: ${timeLeft}s"
        }
        
        // Observe game active state
        viewModel.gameActive.observe(this) { isActive ->
            if (!isActive) {
                showGameOverDialog()
            }
        }
        
        // Observe selected location
        viewModel.selectedLocation.observe(this) { location ->
            location?.let { (lat, lng) ->
                // Add marker for selected location
                binding.mapView.addMarker(
                    com.google.android.gms.maps.model.MarkerOptions()
                        .position(com.google.android.gms.maps.model.LatLng(lat, lng))
                        .title(stringProvider.getString("game.question"))
                )
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
    
    private fun centerMapOnCountry(country: com.geogamenative.data.model.Country) {
        val countryLocation = com.google.android.gms.maps.model.LatLng(
            country.latitude,
            country.longitude
        )
        
        binding.mapView.animateCamera(
            com.google.android.gms.maps.CameraUpdateFactory.newLatLngZoom(
                countryLocation,
                4f
            )
        )
        
        // Add marker for target country
        binding.mapView.addMarker(
            com.google.android.gms.maps.model.MarkerOptions()
                .position(countryLocation)
                .title(country.name)
                .snippet(country.capital)
        )
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
    
    override fun onPause() {
        super.onPause()
        viewModel.pauseGame()
    }
    
    override fun onResume() {
        super.onResume()
        viewModel.resumeGame()
    }
}
