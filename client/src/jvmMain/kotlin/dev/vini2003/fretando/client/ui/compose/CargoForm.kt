package dev.vini2003.fretando.client.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.vini2003.fretando.client.ui.theme.paddings
import dev.vini2003.fretando.client.ui.theme.sizes
import dev.vini2003.fretando.client.ui.theme.spacers

@ExperimentalComposeUiApi
@Composable
fun CargoForm() {
    // Create FocusRequesters for each TextField
    val focusRequesters = remember { List(5) { FocusRequester() } }

    // Your existing state variables
    val length = remember { mutableStateOf("") }
    val lengthError = remember { mutableStateOf<String?>(null) }

    val width = remember { mutableStateOf("") }
    val widthError = remember { mutableStateOf<String?>(null) }

    val height = remember { mutableStateOf("") }
    val heightError = remember { mutableStateOf<String?>(null) }

    val weight = remember { mutableStateOf("") }
    val weightError = remember { mutableStateOf<String?>(null) }

    val description = remember { mutableStateOf("") }
    val descriptionError = remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier.background(MaterialTheme.colorScheme.surface)
    ) {
        Text(
            "Cargo",
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75F), // TODO: Check this!
            modifier = Modifier.padding(MaterialTheme.paddings.small)
        )

        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                CustomTextField(
                    value = length,
                    error = lengthError,
                    label = "Length",
                    modifier = Modifier
                        .defaultMinSize(minHeight = MaterialTheme.sizes.medium)
                        .width(120.dp)
                        .focusRequester(focusRequesters[0]),
                    nextFocusRequester = focusRequesters[1]
                )

                CustomTextField(
                    value = width,
                    error = widthError,
                    label = "Width",
                    modifier = Modifier
                        .defaultMinSize(minHeight = MaterialTheme.sizes.medium)
                        .width(120.dp)
                        .focusRequester(focusRequesters[1]),
                    nextFocusRequester = focusRequesters[2]
                )

                CustomTextField(
                    value = height,
                    error = heightError,
                    label = "Height",
                    modifier = Modifier
                        .defaultMinSize(minHeight = MaterialTheme.sizes.medium)
                        .width(120.dp)
                        .focusRequester(focusRequesters[2]),
                    nextFocusRequester = focusRequesters[3]
                )

                CustomTextField(
                    value = weight,
                    error = weightError,
                    label = "Weight",
                    modifier = Modifier
                        .defaultMinSize(minHeight = MaterialTheme.sizes.medium)
                        .width(120.dp)
                        .focusRequester(focusRequesters[3]),
                    nextFocusRequester = focusRequesters[4]
                )


            }

            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacers.small)) {
                CustomTextField(
                    value = description,
                    error = descriptionError,
                    label = "Description",
                    modifier = Modifier
                        .defaultMinSize(minHeight = MaterialTheme.sizes.medium)
                        .width(568.dp)
                        .focusRequester(focusRequesters[4]),
                    nextFocusRequester = focusRequesters[0]
                )
            }
        }
    }


}
