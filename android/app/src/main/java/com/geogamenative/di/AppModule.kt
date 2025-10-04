package com.geogamenative.di

import android.content.Context
import com.geogamenative.data.CountryRepository
import com.geogamenative.data.local.CountryLocalDataSource
import com.geogamenative.data.remote.CountryRemoteDataSource
import com.geogamenative.utils.LocaleHelper
import com.geogamenative.utils.StringResourceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    
    @Provides
    @Singleton
    fun provideStringResourceProvider(@ApplicationContext context: Context): StringResourceProvider {
        return StringResourceProvider(context)
    }
    
    @Provides
    @Singleton
    fun provideCountryLocalDataSource(
        @ApplicationContext context: Context
    ): CountryLocalDataSource {
        val database = com.geogamenative.data.local.CountryDatabase.getDatabase(context)
        return CountryLocalDataSource(database.countryDao())
    }
    
    @Provides
    @Singleton
    fun provideCountryRemoteDataSource(): CountryRemoteDataSource {
        return CountryRemoteDataSource()
    }
    
    @Provides
    @Singleton
    fun provideCountryRepository(
        localDataSource: CountryLocalDataSource,
        remoteDataSource: CountryRemoteDataSource
    ): CountryRepository {
        return CountryRepository(localDataSource, remoteDataSource)
    }
}
