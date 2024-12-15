package com.felipe.mapapp.data.di

import com.felipe.mapapp.data.remote.CityApiService
import com.felipe.mapapp.data.remote.RetrofitClient
import com.felipe.mapapp.domain.repository.CityRepository
import com.felipe.mapapp.data.repository.CityRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

import android.content.Context
import androidx.room.Room
import com.felipe.mapapp.data.remote.dao.CityDao
import com.felipe.mapapp.data.remote.dao.CityDatabase
import dagger.hilt.android.qualifiers.ApplicationContext


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCityApiService(): CityApiService {
        return RetrofitClient.cityApiService
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CityDatabase {
        return Room.databaseBuilder(
            context,
            CityDatabase::class.java,
            "city_database"
        ).build()
    }

    @Provides
    fun provideCityDao(database: CityDatabase): CityDao {
        return database.cityDao()
    }

    @Provides
    @Singleton
    fun provideCityRepository(
        apiService: CityApiService,
        cityDao: CityDao
    ): CityRepository {
        return CityRepositoryImpl(apiService, cityDao)
    }
}

