package net.jahez.jahezchallenge.core.utils.extension

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.LocaleList
import java.util.*


fun Activity.setLocale(languageCode: String) {
    val overrideConfiguration = baseContext.resources.configuration
    val context= createConfigurationContext(overrideConfiguration)
    val resources = context.resources
    val config = resources.configuration
    val locale = Locale(languageCode)
    Locale.setDefault(locale)
    config.setLocale(locale)


    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        overrideConfiguration.setLocales(LocaleList(locale))
    } else{
        resources.updateConfiguration(config, resources.displayMetrics)
    }
}

fun Context.initLocale(currentLanguage : String) {

    val pref = getSharedPreferences(packageName, Context.MODE_PRIVATE)
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