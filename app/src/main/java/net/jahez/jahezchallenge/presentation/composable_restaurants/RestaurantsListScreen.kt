package net.jahez.jahezchallenge.presentation.composable_restaurants

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
            is HomeViewModel.ViewState.Empty ->
                EmptyRestaurantsComposable{
                    viewModel.getAllRestaurants()
                }
            is HomeViewModel.ViewState.Loading ->
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

@Composable
private fun EmptyRestaurantsComposable(onRefresh : () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Icon(
            imageVector = Icons.Default.Warning,
            contentDescription = null,
            modifier = Modifier.padding(4.dp),
            tint = Color.Yellow
        )
        Text(
            text = stringResource(id = R.string.no_restaurants_found),
            style = MaterialTheme.typography.h4,
            overflow = TextOverflow.Ellipsis,
        )

        IconButton(
            onClick = onRefresh,
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                Icons.Default.Refresh,
                contentDescription = "Refresh",
                modifier = Modifier.size(55.dp)
            )
        }
    }
}