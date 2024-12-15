package com.felipe.mapapp.domain.repository

import com.felipe.mapapp.domain.model.City
import com.felipe.mapapp.domain.model.CityEntity

interface CityRepository {
    suspend fun getCities(): List<City>
    suspend fun getFavorites(): List<CityEntity>
    suspend fun addFavorite(cityEntity: CityEntity)
    suspend fun deleteFavorite(cityId: Int)
}

