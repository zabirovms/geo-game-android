package com.geogamenative.data.local

import com.geogamenative.data.model.Country
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountryLocalDataSource @Inject constructor(
    private val countryDao: CountryDao
) {
    
    fun getAllCountries(): Flow<List<Country>> = countryDao.getAllCountries()
    
    fun getCountriesByContinent(continent: String): Flow<List<Country>> = 
        countryDao.getCountriesByContinent(continent)
    
    suspend fun getCountryByCode(code: String): Country? = 
        countryDao.getCountryByCode(code)
    
    suspend fun getRandomCountryByContinent(continent: String): Country? = 
        countryDao.getRandomCountryByContinent(continent)
    
    suspend fun insertCountry(country: Country) = countryDao.insertCountry(country)
    
    suspend fun insertCountries(countries: List<Country>) = countryDao.insertCountries(countries)
    
    suspend fun updateCountry(country: Country) = countryDao.updateCountry(country)
    
    suspend fun deleteCountry(country: Country) = countryDao.deleteCountry(country)
    
    suspend fun deleteAllCountries() = countryDao.deleteAllCountries()
}
