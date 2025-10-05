package com.geogamenative

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.geogamenative.databinding.ActivityMainBinding
import com.geogamenative.ui.start.StartActivity
import com.geogamenative.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding
  private val viewModel: MainViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    // Initialize binding
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    // Initialize app data
    viewModel.initializeApp()

    // Navigate to StartActivity
    startActivity(Intent(this, StartActivity::class.java))
    finish()
  }
}
