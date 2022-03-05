package net.jahez.jahezchallenge.core.utils.extension

import android.content.Context
import java.util.*

fun Context.initLocale(currentLanguage: String) {
    val res = resources
    // Change locale settings in the app.
    val dm = res.displayMetrics
    val conf = res.configuration
    conf.setLocale(
        Locale(
            currentLanguage
        )
    ) // API 17+ only.

    // Use conf.locale = new Locale(...) if targeting lower versions
    res.updateConfiguration(conf, dm)
}