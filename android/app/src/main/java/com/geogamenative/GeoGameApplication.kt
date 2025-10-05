package com.geogamenative

import android.app.Application
import com.geogamenative.data.CountryRepository
import com.geogamenative.data.local.CountryDatabase
import com.geogamenative.data.local.CountryLocalDataSource
import com.geogamenative.data.remote.CountryRemoteDataSource
import com.geogamenative.utils.LocaleHelper
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GeoGameApplication : Application() {

  // Repository instance
  lateinit var countryRepository: CountryRepository
    private set

  override fun onCreate() {
    super.onCreate()

    // Initialize database
    val database = CountryDatabase.getDatabase(this)
    val localDataSource = CountryLocalDataSource(database.countryDao())
    val remoteDataSource = CountryRemoteDataSource()

    // Initialize repository
    countryRepository = CountryRepository(localDataSource, remoteDataSource)

    // Initialize locale helper
    LocaleHelper.initialize(this)
  }
}
