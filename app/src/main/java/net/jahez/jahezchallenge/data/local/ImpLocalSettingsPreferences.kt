package net.jahez.jahezchallenge.data.local

import android.content.SharedPreferences
import net.jahez.jahezchallenge.data.local.enum.AppLanguage
import net.jahez.jahezchallenge.data.local.mapper.mapToData
import net.jahez.jahezchallenge.domain.enum.AppLanguageDomain


internal class ImpLocalSettingsPreferences(private val settingsPreferences: SharedPreferences) :
    ILocalSettingsPreference {
    companion object {
        private const val KEY_DEFAULT_APP_LANGUAGE = "KEY_DEFAULT_APP_LANGUAGE"
        private val DEFAULT_APP_LANGUAGE = AppLanguage.English
    }

    override fun setAppLanguage(value: AppLanguageDomain) =
        settingsPreferences.edit().putString(KEY_DEFAULT_APP_LANGUAGE, value.language).apply()

    override fun getAppLanguage() =
        try {
            (settingsPreferences.getString(KEY_DEFAULT_APP_LANGUAGE, DEFAULT_APP_LANGUAGE.language)
                ?: DEFAULT_APP_LANGUAGE.language).mapToData() ?: DEFAULT_APP_LANGUAGE
        } catch (e: Exception) {
            e.printStackTrace()
            DEFAULT_APP_LANGUAGE
        }
}
