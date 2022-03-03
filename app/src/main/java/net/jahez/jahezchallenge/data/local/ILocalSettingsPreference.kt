package net.jahez.jahezchallenge.data.local

import net.jahez.jahezchallenge.data.local.enum.AppLanguage
import net.jahez.jahezchallenge.domain.enum.AppLanguageDomain

internal interface ILocalSettingsPreference {
    fun setAppLanguage(value: AppLanguageDomain)
    fun getAppLanguage(): AppLanguage
}