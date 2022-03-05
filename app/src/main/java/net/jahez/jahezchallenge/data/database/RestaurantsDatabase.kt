package net.jahez.jahezchallenge.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import net.jahez.jahezchallenge.data.database.model.RestaurantsEntity


@Database(entities = [RestaurantsEntity::class], version = 1, exportSchema = false)
internal abstract class RestaurantsDatabase : RoomDatabase() {

    abstract fun restaurantsDao(): RestaurantsDao
}
