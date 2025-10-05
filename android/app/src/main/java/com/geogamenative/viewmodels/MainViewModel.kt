package com.geogamenative.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geogamenative.data.CountryRepository
import com.geogamenative.utils.LocaleHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
  private val countryRepository: CountryRepository
) : ViewModel() {

  fun initializeApp() {
    viewModelScope.launch {
      countryRepository.initializeData()
    }
  }

  fun getCurrentLanguage(): String = LocaleHelper.getCurrentLanguage()

  fun getSupportedLanguages(): List<LocaleHelper.Language> = LocaleHelper.getSupportedLanguages()
}
