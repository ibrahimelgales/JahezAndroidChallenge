package net.jahez.jahezchallenge.presentation.settings

import androidx.compose.ui.platform.LocalContext
import dagger.hilt.android.lifecycle.HiltViewModel
import net.jahez.jahezchallenge.core.utils.BaseAction
import net.jahez.jahezchallenge.core.utils.BaseViewModel
import net.jahez.jahezchallenge.core.utils.BaseViewState
import net.jahez.jahezchallenge.domain.enum.AppLanguageDomain
import net.jahez.jahezchallenge.domain.usecase.UseCaseGetAppSettings
import javax.inject.Inject

@HiltViewModel
internal class SettingsViewModel @Inject constructor(
    private val useCaseGetAppSettings: UseCaseGetAppSettings
) : BaseViewModel<SettingsViewModel.ViewState, SettingsViewModel.Action>(ViewState.EnglishLoaded) {

    internal sealed class ViewState : BaseViewState {
        object Empty : ViewState()
        object Loading : ViewState()
        object ArabicLoaded : ViewState()
        object EnglishLoaded : ViewState()
    }

    internal sealed interface Action : BaseAction {
        object ChooseArabicLanguage : Action
        object ChooseEnglishLanguage : Action
        object ChooseLanguageLoading : Action
    }


    private fun getCurrentLanguage() =
        kotlin.run { sendAction(Action.ChooseLanguageLoading) }.also {
            useCaseGetAppSettings.getAppLanguage().sendActionByAppLanguage()
        }

    fun changeLanguage(recreate : () -> Unit) = kotlin.run { sendAction(Action.ChooseLanguageLoading) }.also {
        when (useCaseGetAppSettings.getAppLanguage()) {
            AppLanguageDomain.English -> useCaseGetAppSettings.setAppLanguageArabic()
            AppLanguageDomain.Arabic -> useCaseGetAppSettings.setAppLanguageEnglish()
        }
        getCurrentLanguage()
        recreate()
    }

    private fun AppLanguageDomain.sendActionByAppLanguage() {
        when (this) {
            AppLanguageDomain.Arabic -> sendAction(Action.ChooseArabicLanguage)
            AppLanguageDomain.English -> sendAction(Action.ChooseEnglishLanguage)
        }
    }

    override fun onReduceState(viewAction: Action) = when (viewAction) {
        is Action.ChooseEnglishLanguage -> ViewState.EnglishLoaded
        is Action.ChooseArabicLanguage -> ViewState.ArabicLoaded
        is Action.ChooseLanguageLoading -> ViewState.Loading
    }
}