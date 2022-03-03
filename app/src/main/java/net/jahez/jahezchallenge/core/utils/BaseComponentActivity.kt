package net.jahez.jahezchallenge.core.utils

import android.os.Bundle
import androidx.activity.ComponentActivity
import net.jahez.jahezchallenge.core.utils.extension.initLocale
import net.jahez.jahezchallenge.domain.usecase.UseCaseGetAppSettings
import javax.inject.Inject


abstract class BaseComponentActivity : ComponentActivity() {

    //region var
    @Inject
    internal lateinit var useCaseGetAppSettings: UseCaseGetAppSettings
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLocale(useCaseGetAppSettings.getAppLanguage().language)
    }



}