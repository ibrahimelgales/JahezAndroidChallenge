package net.jahez.jahezchallenge.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.jahez.jahezchallenge.data.database.RestaurantsDatabase
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    internal fun provideDB(@ApplicationContext app: Context) = Room.databaseBuilder(
        app,
        RestaurantsDatabase::class.java,
        "jahez_restaurants_db"
    ).build()

    @Singleton
    @Provides
    internal fun provideYourDao(db: RestaurantsDatabase) =
        db.restaurantsDao()

}