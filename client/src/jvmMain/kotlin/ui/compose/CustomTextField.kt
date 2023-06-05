package ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.input.key.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
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
            error.value?.let { Text(it, color = MaterialTheme.colors.error, style = TextStyle(fontSize = 13.sp)) }
                ?: Text(label)
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