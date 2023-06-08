import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.vini2003.fretando.client.ui.compose.CustomTextField
import dev.vini2003.fretando.client.ui.theme.paddings
import dev.vini2003.fretando.client.ui.theme.sizes
import dev.vini2003.fretando.client.ui.theme.spacers
import dev.vini2003.fretando.common.entity.Cargo

@ExperimentalComposeUiApi
@Composable
fun CargoForm() {
    // Create FocusRequesters for each TextField
    val focusRequesters = remember { List(5) { FocusRequester() } }

    // Your existing state variables
    val length = remember { mutableStateOf("") }
    val lengthError = remember { mutableStateOf<String?>(null) }
    val lengthUnit = remember { mutableStateOf(Cargo.LengthUnit.METERS.asString()) }

    val width = remember { mutableStateOf("") }
    val widthError = remember { mutableStateOf<String?>(null) }
    val widthUnit = remember { mutableStateOf(Cargo.LengthUnit.METERS.asString()) }

    val height = remember { mutableStateOf("") }
    val heightError = remember { mutableStateOf<String?>(null) }
    val heightUnit = remember { mutableStateOf(Cargo.LengthUnit.METERS.asString()) }

    val weight = remember { mutableStateOf("") }
    val weightError = remember { mutableStateOf<String?>(null) }
    val weightUnit = remember { mutableStateOf(Cargo.WeightUnit.KILOGRAMS.asString()) }

    val description = remember { mutableStateOf("") }
    val descriptionError = remember { mutableStateOf<String?>(null) }

    // Dimensions units dropdown
    val unitsDimensions = Cargo.LengthUnit.values().map { it.asString() }

    val lengthUnitDropdownExpanded = remember { mutableStateOf(false) }
    val widthUnitDropdownExpanded = remember { mutableStateOf(false) }
    val heightUnitDropdownExpanded = remember { mutableStateOf(false) }

    // Weight units dropdown
    val unitsWeight = Cargo.WeightUnit.values().map { it.asString() }

    val weightDropdownExpanded = remember { mutableStateOf(false) }

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
                Column {
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
                    // Added dropdown for Length Unit
                    DropdownMenuButton(selectedUnit = lengthUnit, units = unitsDimensions, dropdownExpanded = lengthUnitDropdownExpanded)
                }

                Column {
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
                    // Added dropdown for Width Unit
                    DropdownMenuButton(selectedUnit = widthUnit, units = unitsDimensions, dropdownExpanded = widthUnitDropdownExpanded)
                }

                Column {
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
                    // Added dropdown for Height Unit
                    DropdownMenuButton(selectedUnit = heightUnit, units = unitsDimensions, dropdownExpanded = heightUnitDropdownExpanded)
                }

                Spacer(modifier = Modifier.width(56.dp))

                Column {
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
                    // Added dropdown for Weight Unit
                    DropdownMenuButton(selectedUnit = weightUnit, units = unitsWeight, dropdownExpanded = weightDropdownExpanded)
                }
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

@Composable
fun DropdownMenuButton(selectedUnit: MutableState<String>, units: List<String>, dropdownExpanded: MutableState<Boolean>) {
    Box {
        TextButton(onClick = { dropdownExpanded.value = true }, modifier = Modifier.width(120.dp)) {
            Text(selectedUnit.value)
            Icon(Icons.Filled.ArrowDropDown, contentDescription = "Open dropdown")
        }

        DropdownMenu(
            expanded = dropdownExpanded.value,
            onDismissRequest = { dropdownExpanded.value = false },
        ) {
            units.forEach { unit ->
                DropdownMenuItem(
                    text = { Text(text = unit) },
                    onClick = {
                        dropdownExpanded.value = false
                        selectedUnit.value = unit
                    }
                )
            }
        }
    }
}
