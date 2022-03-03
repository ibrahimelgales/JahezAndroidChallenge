package net.jahez.jahezchallenge.domain.repository

import net.jahez.jahezchallenge.domain.enum.AppLanguageDomain

internal interface IRepositorySettings {
     fun setAppLanguageArabic()
     fun setAppLanguageEnglish()
     fun getAppLanguage(): AppLanguageDomain
}