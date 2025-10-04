package com.geogamenative.data

import com.geogamenative.data.local.CountryLocalDataSource
import com.geogamenative.data.model.Country
import com.geogamenative.data.remote.CountryRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountryRepository @Inject constructor(
    private val localDataSource: CountryLocalDataSource,
    private val remoteDataSource: CountryRemoteDataSource
) {
    
    fun getAllCountries(): Flow<List<Country>> = localDataSource.getAllCountries()
    
    fun getCountriesByContinent(continent: String): Flow<List<Country>> = 
        localDataSource.getCountriesByContinent(continent)
    
    suspend fun getCountryByCode(code: String): Country? = 
        localDataSource.getCountryByCode(code)
    
    suspend fun getRandomCountryByContinent(continent: String): Country? = 
        localDataSource.getRandomCountryByContinent(continent)
    
    suspend fun refreshCountries() {
        val remoteCountries = remoteDataSource.getAllCountries()
        localDataSource.insertCountries(remoteCountries)
    }
    
    suspend fun initializeData() {
        val localCountries = localDataSource.getAllCountries().first()
        if (localCountries.isEmpty()) {
            refreshCountries()
        }
    }
}
