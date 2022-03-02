package net.jahez.jahezchallenge.presentation.composable_dialog

import android.util.Log
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.DialogProperties
import net.jahez.jahezchallenge.R

@Composable
fun ShowDialog(message: String) {
    val openDialog = remember { mutableStateOf(true) }
    if (openDialog.value) {
        AlertDialog(
            properties = DialogProperties(
                dismissOnClickOutside = false,
                dismissOnBackPress = false,
            ),
            onDismissRequest = {
                openDialog.value = false
                Log.e("TAG", "ShowDialog: 1")
            },
            title = {
                Text(text = stringResource(R.string.problem_occurred))
            },
            text = {
                Text(message)
            },
            confirmButton = {
                TextButton(onClick = { openDialog.value = false }) {
                    Text(stringResource(R.string.ok))
                }
            }
        )
    }
}