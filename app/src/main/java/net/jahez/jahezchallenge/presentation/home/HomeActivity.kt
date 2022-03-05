package net.jahez.jahezchallenge.presentation.home


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import net.jahez.jahezchallenge.core.theme.AppDefaultTheme
import net.jahez.jahezchallenge.core.utils.BaseComponentActivity
import net.jahez.jahezchallenge.presentation.home.composable_screens.RestaurantsListScreen

@AndroidEntryPoint
class HomeActivity : BaseComponentActivity() {

    //region var
    companion object {
        fun createIntent(context: Context) = Intent(context, HomeActivity::class.java)
    }

    //endregion
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppDefaultTheme {
                RestaurantsListScreen()
            }
        }
    }
}