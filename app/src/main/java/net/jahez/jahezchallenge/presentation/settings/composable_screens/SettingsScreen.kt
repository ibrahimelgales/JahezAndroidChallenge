package net.jahez.jahezchallenge.presentation.settings.composable_screens

import android.content.res.Resources
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import net.jahez.jahezchallenge.R
import net.jahez.jahezchallenge.presentation.common_composable.ProgressComposable
import net.jahez.jahezchallenge.presentation.settings.SettingsViewModel




@Composable
internal fun SettingsScreen(recreate : () -> Unit) {
    val viewModel: SettingsViewModel = hiltViewModel()
    Scaffold(modifier = Modifier.fillMaxSize()) {
        when (viewModel.stateUI.collectAsState().value) {
            is SettingsViewModel.ViewState.Empty, SettingsViewModel.ViewState.Loading ->
                ProgressComposable()
            is SettingsViewModel.ViewState.ArabicLoaded, SettingsViewModel.ViewState.EnglishLoaded ->
                ChangeLanguageButtonComposable {
                    viewModel.changeLanguage(recreate)
                }
        }
    }
}


@Composable
private fun ChangeLanguageButtonComposable(
    onClick: () -> Unit
) {


    IconButton(
        onClick = { onClick() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                Icons.Default.Place,
                contentDescription = "change language",
                modifier = Modifier.size(55.dp)
            )
            Text(stringResource(id = R.string.change_language))
        }
    }
}