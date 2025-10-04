package com.geogamenative.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
data class Country(
    @PrimaryKey
    val code: String,
    val name: String,
    val nameEn: String,
    val nameRu: String,
    val continent: String,
    val capital: String,
    val capitalEn: String,
    val capitalRu: String,
    val latitude: Double,
    val longitude: Double
)
