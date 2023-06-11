@file:OptIn(ExperimentalMaterial3Api::class)

package dev.vini2003.fretando.client.ui.compose.text

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
@ExperimentalMaterial3Api
@Composable
fun CustomTextField(
    value: MutableState<String>,
    error: MutableState<String?>,
    label: String,
    modifier: Modifier = Modifier,
    nextFocusRequester: FocusRequester? = null,
    isValueAllowed: (String) -> Boolean = { true },
) {
    OutlinedTextField(
        value = value.value,
        onValueChange = {
            if (isValueAllowed(it)) {
                value.value = it
            }
        },
        isError = error.value != null,
        label = {
            error.value?.let { Text(it, color = MaterialTheme.colorScheme.error, style = TextStyle(fontSize = 14.sp)) }
                ?: Text(label, style = TextStyle(fontSize = 14.sp))
        },
        modifier = modifier
            .onPreviewKeyEvent { keyEvent ->
                if (nextFocusRequester != null && keyEvent.key == Key.Tab && keyEvent.type == KeyEventType.KeyDown) {
                    nextFocusRequester.requestFocus()
                    true // consume the event
                } else {
                    false // do not consume the event
                }
            }
    )
}