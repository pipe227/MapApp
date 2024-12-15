package com.felipe.mapapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities")
data class CityEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val country: String,
    val lon: Double,
    val lat: Double
)

