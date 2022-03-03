package net.jahez.jahezchallenge.domain.usecase

import net.jahez.jahezchallenge.domain.repository.IRepositorySettings
import javax.inject.Inject

internal class UseCaseGetAppSettings @Inject constructor(private val iRepositorySettings: IRepositorySettings) {

    fun getAppLanguage() = iRepositorySettings.getAppLanguage()
    fun setAppLanguageArabic() = iRepositorySettings.setAppLanguageArabic()
    fun setAppLanguageEnglish() = iRepositorySettings.setAppLanguageEnglish()
}