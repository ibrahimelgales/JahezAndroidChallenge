package net.jahez.jahezchallenge.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.jahez.jahezchallenge.data.database.RestaurantsDao
import net.jahez.jahezchallenge.data.repository.ImpRepositoryRestaurants
import net.jahez.jahezchallenge.data.network.service.ApiService
import net.jahez.jahezchallenge.domain.repository.IRepositoryRestaurants
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    internal fun provideRepository(apiService: ApiService, restaurantsDao: RestaurantsDao): IRepositoryRestaurants{
        return ImpRepositoryRestaurants(apiService,restaurantsDao)
    }
}