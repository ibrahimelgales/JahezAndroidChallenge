package net.jahez.jahezchallenge.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import net.jahez.jahezchallenge.data.database.model.RestaurantsEntity

@Dao
internal interface RestaurantsDao {

    @Query("SELECT * FROM restaurants")
    suspend fun getAllRestaurants(): List<RestaurantsEntity>

//    @Query("SELECT * FROM restaurants where artist = :artistName and name = :albumName and mbId = :mbId")
//    suspend fun getAlbum(artistName: String, albumName: String, mbId: String?): AlbumEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRestaurants(listOfRestaurants: List<RestaurantsEntity>)

    @Query(
        "SELECT * FROM restaurants" +
                " where (:shouldHasOffer IS NULL OR hasOffer = :shouldHasOffer) ORDER BY" +
                " CASE WHEN :nearbyRestaurants = 1 THEN distance END ASC," +
                " CASE WHEN :highestRating = 1 THEN rating END ASC"
    )
    suspend fun getAllRestaurantsWithFilter(
        shouldHasOffer: Boolean? = null,
        nearbyRestaurants: Boolean? = null,
        highestRating: Boolean? = null
    ): List<RestaurantsEntity>
}
