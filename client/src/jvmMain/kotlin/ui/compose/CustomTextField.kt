@file:OptIn(ExperimentalMaterial3Api::class)

package ui.compose

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.input.key.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@ExperimentalComposeUiApi
@Composable
fun CustomTextField(
    value: MutableState<String>,
    error: MutableState<String?>,
    label: String,
    modifier: Modifier,
    nextFocusRequester: FocusRequester
) {
    OutlinedTextField(
        value = value.value,
        onValueChange = { value.value = it },
        isError = error.value != null,
        label = {
            error.value?.let { Text(it, color = MaterialTheme.colorScheme.error, style = TextStyle(fontSize = 14.sp)) }
                ?: Text(label, style = TextStyle(fontSize = 14.sp))
        },
        modifier = modifier
            .onKeyEvent { keyEvent ->
                if (keyEvent.key == Key.Tab) {
                    if (keyEvent.type == KeyEventType.KeyUp) {
                        nextFocusRequester.requestFocus()
                    }

                    true
                } else {
                    false
                }
            },
    )
}