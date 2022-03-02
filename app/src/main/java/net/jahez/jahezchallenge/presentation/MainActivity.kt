package net.jahez.jahezchallenge.presentation


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import net.jahez.jahezchallenge.core.theme.AppDefaultTheme
import net.jahez.jahezchallenge.presentation.composable_restaurants.RestaurantsListScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppDefaultTheme {
                RestaurantsListScreen()
            }
        }
    }
}