package net.jahez.jahezchallenge.data.local.mapper

import android.util.Log
import net.jahez.jahezchallenge.data.local.enum.AppLanguage
import net.jahez.jahezchallenge.domain.enum.AppLanguageDomain

internal fun AppLanguage.mapToDomain() = when (this) {
    AppLanguage.Arabic -> AppLanguageDomain.Arabic
    AppLanguage.English -> AppLanguageDomain.English
}

internal fun String.mapToData() = AppLanguage.values().find { it.language.equals(this, true) }