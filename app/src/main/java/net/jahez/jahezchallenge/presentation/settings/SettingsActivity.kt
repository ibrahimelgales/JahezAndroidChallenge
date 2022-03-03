package net.jahez.jahezchallenge.presentation.settings


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import net.jahez.jahezchallenge.core.theme.AppDefaultTheme
import net.jahez.jahezchallenge.core.utils.BaseComponentActivity
import net.jahez.jahezchallenge.presentation.home.HomeActivity
import net.jahez.jahezchallenge.presentation.settings.composable_screens.SettingsScreen

@AndroidEntryPoint
class SettingsActivity : BaseComponentActivity() {

    //region var
    companion object {
        fun createIntent(context: Context) = Intent(context, SettingsActivity::class.java)
    }
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppDefaultTheme {
                SettingsScreen(
                    recreate = {
                        HomeActivity.createIntent(this).apply {
                            flags = intent.flags or Intent.FLAG_ACTIVITY_NO_HISTORY
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(this)
                        }
                        finish()
                    })
            }
        }
    }
}