package com.geogamenative.utils

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import java.util.*

object LocaleHelper {
    
    private const val PREF_LANGUAGE = "pref_language"
    private const val DEFAULT_LANGUAGE = "tg"
    
    private var currentLanguage: String = DEFAULT_LANGUAGE
    
    fun initialize(context: Context) {
        val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        currentLanguage = prefs.getString(PREF_LANGUAGE, DEFAULT_LANGUAGE) ?: DEFAULT_LANGUAGE
    }
    
    fun setLanguage(context: Context, languageCode: String) {
        currentLanguage = languageCode
        val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        prefs.edit().putString(PREF_LANGUAGE, languageCode).apply()
        
        updateConfiguration(context, languageCode)
    }
    
    fun getCurrentLanguage(): String = currentLanguage
    
    fun getSupportedLanguages(): List<Language> = listOf(
        Language("en", "English"),
        Language("ru", "русский"),
        Language("tg", "тоҷикӣ")
    )
    
    private fun updateConfiguration(context: Context, languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        
        val config = Configuration(context.resources.configuration)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            config.setLocale(locale)
        } else {
            @Suppress("DEPRECATION")
            config.locale = locale
        }
        
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
    }
    
    data class Language(
        val code: String,
        val name: String
    )
}
