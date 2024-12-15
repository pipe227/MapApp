package com.felipe.mapapp.data.remote.dao
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.felipe.mapapp.domain.model.CityEntity

@Dao
interface CityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertFavorite(city: CityEntity)

    @Query("DELETE FROM cities WHERE id = :cityId")
     fun deleteFavorite(cityId: Int): Int

    @Query("SELECT * FROM cities")
     fun getFavorites(): List<CityEntity>
}



