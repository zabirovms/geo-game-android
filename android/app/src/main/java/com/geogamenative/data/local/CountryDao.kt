package com.geogamenative.data.local

import androidx.room.*
import com.geogamenative.data.model.Country
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {
    
    @Query("SELECT * FROM countries")
    fun getAllCountries(): Flow<List<Country>>
    
    @Query("SELECT * FROM countries WHERE continent = :continent")
    fun getCountriesByContinent(continent: String): Flow<List<Country>>
    
    @Query("SELECT * FROM countries WHERE code = :code")
    suspend fun getCountryByCode(code: String): Country?
    
    @Query("SELECT * FROM countries WHERE continent = :continent ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomCountryByContinent(continent: String): Country?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountry(country: Country)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountries(countries: List<Country>)
    
    @Update
    suspend fun updateCountry(country: Country)
    
    @Delete
    suspend fun deleteCountry(country: Country)
    
    @Query("DELETE FROM countries")
    suspend fun deleteAllCountries()
}
