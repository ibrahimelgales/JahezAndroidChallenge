package net.jahez.jahezchallenge.data.repository

import net.jahez.jahezchallenge.data.local.ILocalSettingsPreference
import net.jahez.jahezchallenge.data.local.mapper.mapToDomain
import net.jahez.jahezchallenge.domain.enum.AppLanguageDomain
import net.jahez.jahezchallenge.domain.repository.IRepositorySettings
import javax.inject.Inject


internal class ImpRepositorySettings @Inject constructor(
    private val localSettingsPreferences: ILocalSettingsPreference,
) : IRepositorySettings {


    override fun setAppLanguageArabic() =
        localSettingsPreferences.setAppLanguage(AppLanguageDomain.Arabic)

    override fun setAppLanguageEnglish() =
        localSettingsPreferences.setAppLanguage(AppLanguageDomain.English)

    override fun getAppLanguage() = localSettingsPreferences.getAppLanguage().mapToDomain()

}
