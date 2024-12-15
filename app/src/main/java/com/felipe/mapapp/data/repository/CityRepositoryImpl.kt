package com.felipe.mapapp.data.repository

import com.felipe.mapapp.data.remote.CityApiService
import com.felipe.mapapp.data.remote.dao.CityDao
import com.felipe.mapapp.domain.model.City
import com.felipe.mapapp.domain.model.CityEntity
import com.felipe.mapapp.domain.repository.CityRepository

class CityRepositoryImpl(
    private val apiService: CityApiService,
    private val cityDao: CityDao
) : CityRepository {

    override suspend fun getCities(): List<City> {
        val citiesFromApi = apiService.getCities()
        return citiesFromApi
    }

    override suspend fun getFavorites(): List<CityEntity> {
        return cityDao.getFavorites()
    }

    override suspend fun addFavorite(cityEntity: CityEntity) {
        cityDao.insertFavorite(cityEntity)
    }

    override suspend fun deleteFavorite(cityId: Int) {
        cityDao.deleteFavorite(cityId)
    }
}


