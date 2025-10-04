package com.geogamenative.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geogamenative.data.CountryRepository
import com.geogamenative.data.model.Country
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val countryRepository: CountryRepository
) : ViewModel() {
    
    private val _currentCountry = MutableLiveData<Country?>()
    val currentCountry: LiveData<Country?> = _currentCountry
    
    private val _score = MutableLiveData(0)
    val score: LiveData<Int> = _score
    
    private val _timeLeft = MutableLiveData(30)
    val timeLeft: LiveData<Int> = _timeLeft
    
    private val _gameActive = MutableLiveData(true)
    val gameActive: LiveData<Boolean> = _gameActive
    
    private val _selectedLocation = MutableLiveData<Pair<Double, Double>?>()
    val selectedLocation: LiveData<Pair<Double, Double>?> = _selectedLocation
    
    private var timerJob: Job? = null
    private var currentContinent: String = "asia"
    private var currentMode: String = "countryName"
    
    fun startGame(continent: String, mode: String) {
        currentContinent = continent
        currentMode = mode
        _score.value = 0
        _gameActive.value = true
        startNewQuestion()
    }
    
    private fun startNewQuestion() {
        viewModelScope.launch {
            val country = countryRepository.getRandomCountryByContinent(currentContinent)
            _currentCountry.value = country
            _selectedLocation.value = null
            _timeLeft.value = 30
            startTimer()
        }
    }
    
    private fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (_timeLeft.value ?: 0 > 0 && _gameActive.value == true) {
                delay(1000)
                _timeLeft.value = (_timeLeft.value ?: 0) - 1
            }
            if (_timeLeft.value == 0) {
                handleTimeUp()
            }
        }
    }
    
    private fun handleTimeUp() {
        _gameActive.value = false
        timerJob?.cancel()
    }
    
    fun onMapClick(latitude: Double, longitude: Double) {
        if (_gameActive.value != true) return
        
        _selectedLocation.value = Pair(latitude, longitude)
        
        val country = _currentCountry.value
        if (country != null) {
            val distance = calculateDistance(latitude, longitude, country.latitude, country.longitude)
            
            if (distance < 200) { // Within 200km
                _score.value = (_score.value ?: 0) + 1
                // Show correct answer
                startNewQuestion()
            } else {
                // Show wrong answer
                startNewQuestion()
            }
        }
    }
    
    private fun calculateDistance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val R = 6371 // Earth's radius in kilometers
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                Math.sin(dLon / 2) * Math.sin(dLon / 2)
        val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
        return R * c
    }
    
    fun restartGame() {
        _score.value = 0
        _gameActive.value = true
        startNewQuestion()
    }
    
    fun pauseGame() {
        _gameActive.value = false
        timerJob?.cancel()
    }
    
    fun resumeGame() {
        _gameActive.value = true
        startTimer()
    }
    
    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
}
