package com.felipe.mapapp.domain.model

import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("_id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("country") val country: String,
    @SerializedName("coord") val coord: Coord
)

data class Coord(
    @SerializedName("lon") val lon: Double,
    @SerializedName("lat") val lat: Double
)



