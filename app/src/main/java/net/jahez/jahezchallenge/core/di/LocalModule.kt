package net.jahez.jahezchallenge.core.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.jahez.jahezchallenge.data.local.ILocalSettingsPreference
import net.jahez.jahezchallenge.data.local.ImpLocalSettingsPreferences
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideLocalSettingsSharedPreferences(
        @ApplicationContext app: Context
    ) = app.getSharedPreferences(
        "JAHEZ_APP_LOCAL_SETTINGS_SHARED_PREFERENCES",
        Context.MODE_PRIVATE
    )

    @Singleton
    @Provides
    internal fun provideImpLocalSettingsDataStore(
        settingsPreferences: SharedPreferences
    ): ILocalSettingsPreference = ImpLocalSettingsPreferences(
        settingsPreferences
    )
}