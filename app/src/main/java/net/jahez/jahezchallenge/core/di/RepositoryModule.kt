package net.jahez.jahezchallenge.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.jahez.jahezchallenge.data.database.RestaurantsDao
import net.jahez.jahezchallenge.data.local.ILocalSettingsPreference
import net.jahez.jahezchallenge.data.local.ImpLocalSettingsPreferences
import net.jahez.jahezchallenge.data.repository.ImpRepositoryRestaurants
import net.jahez.jahezchallenge.data.network.service.ApiService
import net.jahez.jahezchallenge.data.repository.ImpRepositorySettings
import net.jahez.jahezchallenge.domain.repository.IRepositoryRestaurants
import net.jahez.jahezchallenge.domain.repository.IRepositorySettings
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    internal fun provideRepository(apiService: ApiService, restaurantsDao: RestaurantsDao): IRepositoryRestaurants{
        return ImpRepositoryRestaurants(apiService,restaurantsDao)
    }

    @Singleton
    @Provides
    internal fun provideRepositorySettings(
       localSettingsPreferences: ILocalSettingsPreference
    ): IRepositorySettings = ImpRepositorySettings(localSettingsPreferences)
}