package com.geogamenative.ui.start

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.geogamenative.databinding.ActivityStartBinding
import com.geogamenative.ui.game.GameActivity
import com.geogamenative.utils.LocaleHelper
import com.geogamenative.utils.StringResourceProvider
import com.geogamenative.viewmodels.StartViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class StartActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityStartBinding
    private lateinit var viewModel: StartViewModel
    
    @Inject
    lateinit var stringProvider: StringResourceProvider
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        viewModel = ViewModelProvider(this)[StartViewModel::class.java]
        
        setupUI()
        observeViewModel()
    }
    
    private fun setupUI() {
        // Set header text
        binding.titleText.text = stringProvider.getString("header.title")
        binding.subtitleText.text = stringProvider.getString("header.subTitle")
        binding.descriptionText.text = stringProvider.getString("header.description")
        
        // Set section titles
        binding.languageSectionTitle.text = stringProvider.getString("languages.tg")
        binding.modesSectionTitle.text = stringProvider.getString("modes.countryName")
        binding.continentsSectionTitle.text = stringProvider.getString("continents.asia")
        
        // Setup language buttons
        setupLanguageButtons()
        
        // Setup mode buttons
        setupModeButtons()
        
        // Setup continent buttons
        setupContinentButtons()
        
        // Setup help button
        binding.helpButton.text = stringProvider.getString("gettingStarted.title")
        binding.helpButton.setOnClickListener {
            showGettingStartedDialog()
        }
    }
    
    private fun setupLanguageButtons() {
        val languages = LocaleHelper.getSupportedLanguages()
        val currentLanguage = LocaleHelper.getCurrentLanguage()
        
        binding.languageButtons.removeAllViews()
        
        languages.forEach { language ->
            val button = android.widget.Button(this).apply {
                text = language.name
                setOnClickListener {
                    LocaleHelper.setLanguage(this@StartActivity, language.code)
                    recreate() // Restart activity to apply language change
                }
                if (language.code == currentLanguage) {
                    setBackgroundColor(getColor(android.R.color.holo_blue_bright))
                    setTextColor(getColor(android.R.color.white))
                } else {
                    setBackgroundColor(getColor(android.R.color.darker_gray))
                    setTextColor(getColor(android.R.color.black))
                }
            }
            binding.languageButtons.addView(button)
        }
    }
    
    private fun setupModeButtons() {
        val modes = listOf(
            "countryName" to stringProvider.getString("modes.countryName"),
            "capital" to stringProvider.getString("modes.capital"),
            "flag" to stringProvider.getString("modes.flag")
        )
        
        binding.modeButtons.removeAllViews()
        
        modes.forEach { (mode, name) ->
            val button = android.widget.Button(this).apply {
                text = name
                setOnClickListener {
                    startGame("asia", mode)
                }
                setBackgroundColor(getColor(android.R.color.holo_green_dark))
                setTextColor(getColor(android.R.color.white))
            }
            binding.modeButtons.addView(button)
        }
    }
    
    private fun setupContinentButtons() {
        val continents = listOf(
            "africa" to stringProvider.getString("continents.africa"),
            "asia" to stringProvider.getString("continents.asia"),
            "europe" to stringProvider.getString("continents.europe"),
            "northAmerica" to stringProvider.getString("continents.northAmerica"),
            "southAmerica" to stringProvider.getString("continents.southAmerica"),
            "oceania" to stringProvider.getString("continents.oceania")
        )
        
        binding.continentButtons.removeAllViews()
        
        continents.forEach { (continent, name) ->
            val button = android.widget.Button(this).apply {
                text = name
                setOnClickListener {
                    startGame(continent, "countryName")
                }
                setBackgroundColor(getColor(android.R.color.darker_gray))
                setTextColor(getColor(android.R.color.white))
            }
            binding.continentButtons.addView(button)
        }
    }
    
    private fun startGame(continent: String, mode: String) {
        val intent = Intent(this, GameActivity::class.java).apply {
            putExtra("continent", continent)
            putExtra("mode", mode)
        }
        startActivity(intent)
    }
    
    private fun showGettingStartedDialog() {
        androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle(stringProvider.getString("gettingStarted.title"))
            .setMessage(stringProvider.getString("gettingStarted.intro"))
            .setPositiveButton(stringProvider.getString("actions.close")) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
    
    private fun observeViewModel() {
        // Observe any ViewModel data if needed
    }
}
