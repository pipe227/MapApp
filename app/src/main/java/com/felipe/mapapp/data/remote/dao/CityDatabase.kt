package com.felipe.mapapp.data.remote.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.felipe.mapapp.domain.model.CityEntity

@Database(entities = [CityEntity::class], version = 1, exportSchema = false)
abstract class CityDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
}
