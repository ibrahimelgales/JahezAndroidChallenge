package net.jahez.jahezchallenge.presentation.home.composable_screens

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import net.jahez.jahezchallenge.R
import net.jahez.jahezchallenge.domain.model.RestaurantFilterParams
import net.jahez.jahezchallenge.presentation.common_composable.ShowDialog
import net.jahez.jahezchallenge.presentation.home.HomeViewModel
import net.jahez.jahezchallenge.presentation.settings.SettingsActivity

@Preview
@Composable
internal fun RestaurantsListScreen() {
    val context = LocalContext.current
    val viewModel: HomeViewModel = hiltViewModel()
    val scaffoldState = rememberScaffoldState()
    Scaffold(scaffoldState = scaffoldState, modifier = Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize()) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = null,
                modifier = Modifier
                    .padding(16.dp)
                    .size(55.dp)
                    .clickable {
                        context.apply {
                            startActivity(SettingsActivity.createIntent(this))
                        }
                    }
            )
            var filterVisibility by remember { mutableStateOf(false) }
            AnimatedVisibility(
                visible = filterVisibility,
                enter = fadeIn(
                    // Overwrites the initial value of alpha to 0.4f for fade in, 0 by default
                    initialAlpha = 0.4f
                ),
                exit = fadeOut(
                    // Overwrites the default animation with tween
                    animationSpec = tween(durationMillis = 250)
                )
            ) {
                val offerChecked = remember { mutableStateOf(false) }
                val nearbyChecked = remember { mutableStateOf(false) }
                val highestRatingChecked = remember { mutableStateOf(false) }

                Column(
                    Modifier
                        .padding(16.dp)
                        .wrapContentSize()
                ) {
                    Text(stringResource(id = R.string.filter_by))
                    Row(
                        modifier = Modifier.horizontalScroll(rememberScrollState())
                    ) {
                        ColouredCheckbox(label = R.string.has_offer, isChecked = offerChecked)
                        ColouredCheckbox(
                            label = R.string.nearby_restaurants,
                            isChecked = nearbyChecked
                        )
                        ColouredCheckbox(
                            label = R.string.highest_rating,
                            isChecked = highestRatingChecked
                        )
                    }
                    Button(onClick = {
                        val hasOffer = offerChecked.value
                        val nearbyCheck = nearbyChecked.value
                        val highestRatingCheck = highestRatingChecked.value
                        viewModel.applyFilter(
                            RestaurantFilterParams().apply {
                                shouldHasOffer = if (hasOffer) true else null
                                nearbyRestaurants = if (nearbyCheck) true else null
                                highestRating = if (highestRatingCheck) true else null
                            }
                        )
                    }) {
                        Text(text = stringResource(id = R.string.apply_filter))
                    }
                }
            }
            when (val state = viewModel.stateUI.collectAsState().value) {
                is HomeViewModel.ViewState.Empty ->
                    EmptyRestaurantsComposable {
                        viewModel.getAllRestaurants()
                    }
                is HomeViewModel.ViewState.Loading ->
                    ProgressComposable()
                is HomeViewModel.ViewState.Error ->
                    DialogComposable(state)
                is HomeViewModel.ViewState.Loaded ->
                    DrawListOfRestaurantsComposable(state).also {
                        filterVisibility = true
                    }
            }
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
private fun EmptyRestaurantsComposable(onRefresh: () -> Unit) {
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

@Composable
fun ColouredCheckbox(@StringRes label: Int, isChecked: MutableState<Boolean>) {
    Row(modifier = Modifier.padding(end = 8.dp), verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = isChecked.value,
            onCheckedChange = { isChecked.value = it },
            enabled = true,
            colors = CheckboxDefaults.colors(Color.Yellow)
        )
        Text(text = stringResource(id = label))
    }

}