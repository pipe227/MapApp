package com.felipe.mapapp.data.remote

import com.felipe.mapapp.domain.model.City
import retrofit2.http.GET
import retrofit2.http.Headers


interface CityApiService {
    @Headers("Content-Type: application/json")
    @GET("hernan-uala/dce8843a8edbe0b0018b32e137bc2b3a/raw/0996accf70cb0ca0e16f9a99e0ee185fafca7af1/cities.json")
    suspend fun getCities(): List<City>
}


