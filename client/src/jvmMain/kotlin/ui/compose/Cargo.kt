package ui.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@ExperimentalComposeUiApi
@Composable
fun Cargo() {
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

    Column {
        Text(
            "Cargo",
            fontSize = 18.sp,
            color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
            modifier = Modifier.padding(8.dp)
        )
        Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(horizontal = 8.dp))

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
                        .defaultMinSize(minHeight = 16.dp)
                        .width(120.dp)
                        .focusRequester(focusRequesters[0]),
                    nextFocusRequester = focusRequesters[1]
                )

                CustomTextField(
                    value = width,
                    error = widthError,
                    label = "Width",
                    modifier = Modifier
                        .defaultMinSize(minHeight = 16.dp)
                        .width(120.dp)
                        .focusRequester(focusRequesters[1]),
                    nextFocusRequester = focusRequesters[2]
                )

                CustomTextField(
                    value = height,
                    error = heightError,
                    label = "Height",
                    modifier = Modifier
                        .defaultMinSize(minHeight = 16.dp)
                        .width(120.dp)
                        .focusRequester(focusRequesters[2]),
                    nextFocusRequester = focusRequesters[3]
                )

                CustomTextField(
                    value = weight,
                    error = weightError,
                    label = "Weight",
                    modifier = Modifier
                        .defaultMinSize(minHeight = 16.dp)
                        .width(120.dp)
                        .focusRequester(focusRequesters[3]),
                    nextFocusRequester = focusRequesters[4]
                )


            }

            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                CustomTextField(
                    value = description,
                    error = descriptionError,
                    label = "Description",
                    modifier = Modifier
                        .defaultMinSize(minHeight = 16.dp)
                        .width(568.dp)
                        .focusRequester(focusRequesters[4]),
                    nextFocusRequester = focusRequesters[0]
                )
            }
        }
    }


}
