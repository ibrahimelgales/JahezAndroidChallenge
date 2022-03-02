package net.jahez.jahezchallenge.presentation.composable_restaurants

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import net.jahez.jahezchallenge.R
import net.jahez.jahezchallenge.presentation.HomeViewModel
import net.jahez.jahezchallenge.presentation.composable_dialog.ShowDialog

@Preview
@Composable
internal fun RestaurantsListScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    Scaffold(modifier = Modifier.fillMaxSize()) {
        when (val state = viewModel.stateUI.collectAsState().value) {
            is HomeViewModel.ViewState.Empty, is HomeViewModel.ViewState.Loading ->
                ProgressComposable()
            is HomeViewModel.ViewState.Error ->
                DialogComposable(state)
            is HomeViewModel.ViewState.Loaded ->
                DrawListOfRestaurantsComposable(state)
        }
    }
}

@Composable
private fun DrawListOfRestaurantsComposable(state: HomeViewModel.ViewState.Loaded) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(state.data) { restaurantItem ->
            RestaurantListItem(
                item = restaurantItem,
                onItemClick = {

                }
            )
        }
    }
}

@Composable
private fun DialogComposable(state: HomeViewModel.ViewState.Error) {
    ShowDialog(
        message = state.appException?.getMessage() ?: stringResource(
            R.string.something_went_wrong
        )
    )
}

@Composable
private fun ProgressComposable() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}