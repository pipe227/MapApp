package com.felipe.mapapp.domain.usecase

import com.felipe.mapapp.domain.model.City
import com.felipe.mapapp.domain.repository.CityRepository

class GetCitiesUseCase(private val repository: CityRepository) {
    suspend operator fun invoke(): List<City> {
        return repository.getCities()
    }
}
