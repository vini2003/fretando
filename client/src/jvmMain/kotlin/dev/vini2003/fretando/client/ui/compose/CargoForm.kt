
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class CargoFormData(
    val length: MutableState<String> = mutableStateOf(""),
    val lengthError: MutableState<String?> = mutableStateOf(null),
    val lengthUnit: MutableState<String> = mutableStateOf(Cargo.DimensionUnit.METERS.asString()),
    val width: MutableState<String> = mutableStateOf(""),
    val widthError: MutableState<String?> = mutableStateOf(null),
    val widthUnit: MutableState<String> = mutableStateOf(Cargo.DimensionUnit.METERS.asString()),
    val height: MutableState<String> = mutableStateOf(""),
    val heightError: MutableState<String?> = mutableStateOf(null),
    val heightUnit: MutableState<String> = mutableStateOf(Cargo.DimensionUnit.METERS.asString()),
    val weight: MutableState<String> = mutableStateOf(""),
    val weightError: MutableState<String?> = mutableStateOf(null),
    val weightUnit: MutableState<String> = mutableStateOf(Cargo.WeightUnit.KILOGRAMS.asString()),
    val description: MutableState<String> = mutableStateOf(""),
    val descriptionError: MutableState<String?> = mutableStateOf(null),
) {
    fun validate(): Boolean {
        var valid = true

        if (length.value.isEmpty()) {
            lengthError.value = "Length is required"
            valid = false
        } else {
            lengthError.value = null
        }

        if (!Cargo.DimensionUnit.values().map { it.asString() }.contains(lengthUnit.value)) {
            lengthError.value = "Length unit is invalid"
            valid = false
        } else {
            lengthError.value = null
        }

        if (width.value.isEmpty()) {
            widthError.value = "Width is required"
            valid = false
        } else {
            widthError.value = null
        }

        if (!Cargo.DimensionUnit.values().map { it.asString() }.contains(widthUnit.value)) {
            widthError.value = "Width unit is invalid"
            valid = false
        } else {
            widthError.value = null
        }

        if (height.value.isEmpty()) {
            heightError.value = "Height is required"
            valid = false
        } else {
            heightError.value = null
        }

        if (!Cargo.DimensionUnit.values().map { it.asString() }.contains(heightUnit.value)) {
            heightError.value = "Height unit is invalid"
            valid = false
        } else {
            heightError.value = null
        }

        if (weight.value.isEmpty()) {
            weightError.value = "Weight is required"
            valid = false
        } else {
            weightError.value = null
        }

        if (!Cargo.WeightUnit.values().map { it.asString() }.contains(weightUnit.value)) {
            weightError.value = "Weight unit is invalid"
            valid = false
        } else {
            weightError.value = null
        }

        if (description.value.isEmpty()) {
            descriptionError.value = "Description is required"
            valid = false
        } else {
            descriptionError.value = null
        }

        return valid
    }
}

@ExperimentalComposeUiApi
@Composable
fun CargoForm(
    data: MutableState<CargoFormData> = mutableStateOf(CargoFormData()),
) {
    val scope = rememberCoroutineScope()

    // Create FocusRequesters for each TextField
    val focusRequesters = remember { List(5) { FocusRequester() } }

    // Your existing state variables
    val length = remember { data.value.length }
    val lengthError = remember { data.value.lengthError }
    val lengthUnit = remember { data.value.lengthUnit }

    val width = remember { data.value.width }
    val widthError = remember { data.value.widthError }
    val widthUnit = remember { data.value.widthUnit }

    val height = remember { data.value.height }
    val heightError = remember { data.value.heightError }
    val heightUnit = remember { data.value.heightUnit }

    val weight = remember { data.value.weight }
    val weightError = remember { data.value.weightError }
    val weightUnit = remember { data.value.weightUnit }

    val description = remember { data.value.description }
    val descriptionError = remember { data.value.descriptionError }

    val unitsDimensions = Cargo.DimensionUnit.values().map { it.asString() }

    val lengthUnitDropdownExpanded = remember { mutableStateOf(false) }
    val widthUnitDropdownExpanded = remember { mutableStateOf(false) }
    val heightUnitDropdownExpanded = remember { mutableStateOf(false) }

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
                        isValueAllowed = { newValue ->
                            if (newValue.matches(Regex("^\\d*(,\\d*)*(\\.\\d{0,2})?$"))) {
                                true
                            } else {
                                lengthError.value = "Invalid"

                                scope.launch {
                                    delay(2000)
                                    lengthError.value = null
                                }

                                false
                            }
                        },
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
                        isValueAllowed = { newValue ->
                            if (newValue.matches(Regex("^\\d*(,\\d*)*(\\.\\d{0,2})?$"))) {
                                true
                            } else {
                                widthError.value = "Invalid"

                                scope.launch {
                                    delay(2000)
                                    widthError.value = null
                                }

                                false
                            }
                        },
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
                        isValueAllowed = { newValue ->
                            if (newValue.matches(Regex("^\\d*(,\\d*)*(\\.\\d{0,2})?$"))) {
                                true
                            } else {
                                heightError.value = "Invalid"

                                scope.launch {
                                    delay(2000)
                                    heightError.value = null
                                }

                                false
                            }
                        },
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
                        isValueAllowed = { newValue ->
                            if (newValue.matches(Regex("^\\d*(,\\d*)*(\\.\\d{0,2})?$"))) {
                                true
                            } else {
                                weightError.value = "Invalid"

                                scope.launch {
                                    delay(2000)
                                    weightError.value = null
                                }

                                false
                            }
                        },
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
