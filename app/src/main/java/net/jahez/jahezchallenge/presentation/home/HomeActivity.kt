package net.jahez.jahezchallenge.presentation.home


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.asLiveData
import com.google.accompanist.insets.ProvideWindowInsets
import dagger.hilt.android.AndroidEntryPoint
import net.jahez.jahezchallenge.core.theme.AppDefaultTheme
import net.jahez.jahezchallenge.core.utils.BaseComponentActivity
import net.jahez.jahezchallenge.presentation.home.composable_screens.RestaurantsListScreen
import net.jahez.jahezchallenge.presentation.settings.SettingsActivity

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
            ProvideWindowInsets{
                AppDefaultTheme {
                    RestaurantsListScreen()
                }
            }

        }
    }
}