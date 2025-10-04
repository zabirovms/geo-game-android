package com.geogamenative.viewmodels

import androidx.lifecycle.ViewModel
import com.geogamenative.data.CountryRepository
import com.geogamenative.utils.LocaleHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    private val countryRepository: CountryRepository
) : ViewModel() {
    
    fun getSupportedLanguages(): List<LocaleHelper.Language> = LocaleHelper.getSupportedLanguages()
    
    fun getCurrentLanguage(): String = LocaleHelper.getCurrentLanguage()
}
