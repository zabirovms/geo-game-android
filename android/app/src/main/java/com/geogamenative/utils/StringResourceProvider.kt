package com.geogamenative.utils

import android.content.Context
import com.geogamenative.R

class StringResourceProvider(private val context: Context) {
    
    fun getString(key: String): String {
        return when (key) {
            // Header
            "header.title" -> context.getString(R.string.header_title)
            "header.subTitle" -> context.getString(R.string.header_subtitle)
            "header.description" -> context.getString(R.string.header_description)
            
            // Modes
            "modes.countryName" -> context.getString(R.string.modes_country_name)
            "modes.capital" -> context.getString(R.string.modes_capital)
            "modes.flag" -> context.getString(R.string.modes_flag)
            
            // Continents
            "continents.africa" -> context.getString(R.string.continents_africa)
            "continents.asia" -> context.getString(R.string.continents_asia)
            "continents.europe" -> context.getString(R.string.continents_europe)
            "continents.northAmerica" -> context.getString(R.string.continents_north_america)
            "continents.southAmerica" -> context.getString(R.string.continents_south_america)
            "continents.oceania" -> context.getString(R.string.continents_oceania)
            
            // Actions
            "actions.play" -> context.getString(R.string.actions_play)
            "actions.restart" -> context.getString(R.string.actions_restart)
            "actions.close" -> context.getString(R.string.actions_close)
            "actions.next" -> context.getString(R.string.actions_next)
            "actions.finish" -> context.getString(R.string.actions_finish)
            
            // Getting Started
            "gettingStarted.title" -> context.getString(R.string.getting_started_title)
            "gettingStarted.intro" -> context.getString(R.string.getting_started_intro)
            
            // Game
            "game.score" -> context.getString(R.string.game_score)
            "game.time" -> context.getString(R.string.game_time)
            "game.question" -> context.getString(R.string.game_question)
            "game.correct" -> context.getString(R.string.game_correct)
            "game.wrong" -> context.getString(R.string.game_wrong)
            "game.timeUp" -> context.getString(R.string.game_time_up)
            "game.gameOver" -> context.getString(R.string.game_game_over)
            "game.finalScore" -> context.getString(R.string.game_final_score)
            
            // Languages
            "languages.en" -> context.getString(R.string.languages_en)
            "languages.ru" -> context.getString(R.string.languages_ru)
            "languages.tg" -> context.getString(R.string.languages_tg)
            
            else -> key
        }
    }
}
