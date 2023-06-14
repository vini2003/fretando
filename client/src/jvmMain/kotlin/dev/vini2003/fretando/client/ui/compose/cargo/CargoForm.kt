package dev.vini2003.fretando.client.ui.compose.cargo
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.vini2003.fretando.client.ui.compose.button.ItemizedDropdownMenu
import dev.vini2003.fretando.client.ui.compose.data.CargoFormData
import dev.vini2003.fretando.client.ui.compose.text.CustomTextField
import dev.vini2003.fretando.client.ui.theme.paddings
import dev.vini2003.fretando.client.ui.theme.sizes
import dev.vini2003.fretando.client.ui.theme.spacers
import dev.vini2003.fretando.common.entity.Cargo
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalComposeUiApi
@Composable
fun CargoForm(
    data: MutableState<CargoFormData> = remember { mutableStateOf(CargoFormData()) },
    firstFocusRequester: FocusRequester? = null,
    nextFocusRequester: FocusRequester? = null,
    modifier: Modifier = Modifier,
) {
    val scope = rememberCoroutineScope()

    val focusRequesters = remember { MutableList(5) { FocusRequester() } }
    if (firstFocusRequester != null) {
        focusRequesters[0] = firstFocusRequester
    }

    val unitsDimensions = Cargo.DimensionUnit.values().map { it.asString() }

    val lengthUnitDropdownExpanded = remember { mutableStateOf(false) }
    val widthUnitDropdownExpanded = remember { mutableStateOf(false) }
    val heightUnitDropdownExpanded = remember { mutableStateOf(false) }

    val unitsWeight = Cargo.WeightUnit.values().map { it.asString() }

    val weightDropdownExpanded = remember { mutableStateOf(false) }

    Column(
        modifier = modifier.background(MaterialTheme.colorScheme.surface)
    ) {
        Text(
            "Cargo",
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75F),
            modifier = Modifier.padding(MaterialTheme.paddings.small)
        )

        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Column {
                    CustomTextField(
                        value = data.value.length,
                        error = data.value.lengthError,
                        isValueAllowed = { newValue ->
                            if (newValue.matches(Regex("^\\d*(,\\d*)*(\\.\\d{0,2})?$"))) {
                                try {
                                    val value = newValue.toDouble()

                                    if (value > 0.0) {
                                        data.value.lengthError.value = null
                                    } else {
                                        data.value.lengthError.value = "Invalid"
                                    }
                                } catch (exception: NumberFormatException) {
                                    data.value.lengthError.value = "Invalid"
                                }

                                true
                            } else {
                                data.value.lengthError.value = "Invalid"

                                scope.launch {
                                    delay(2000)
                                    data.value.lengthError.value = null
                                }

                                false
                            }
                        },
                        label = "Length",
                        modifier = Modifier
                            .defaultMinSize(minHeight = MaterialTheme.sizes.medium)
                            .width(120.dp)
                            .focusRequester(focusRequesters[0]),
                        previousFocusRequester = focusRequesters[4],
                        nextFocusRequester = focusRequesters[1]
                    )

                    ItemizedDropdownMenu(selectedItem = data.value.lengthUnit, items = unitsDimensions, dropdownExpanded = lengthUnitDropdownExpanded)
                }

                Column {
                    CustomTextField(
                        value = data.value.width,
                        error = data.value.widthError,
                        isValueAllowed = { newValue ->
                            if (newValue.matches(Regex("^\\d*(,\\d*)*(\\.\\d{0,2})?$"))) {
                                true
                            } else {
                                data.value.widthError.value = "Invalid"

                                scope.launch {
                                    delay(2000)
                                    data.value.widthError.value = null
                                }

                                false
                            }
                        },
                        label = "Width",
                        modifier = Modifier
                            .defaultMinSize(minHeight = MaterialTheme.sizes.medium)
                            .width(120.dp)
                            .focusRequester(focusRequesters[1]),
                        previousFocusRequester = focusRequesters[0],
                        nextFocusRequester = focusRequesters[2]
                    )

                    ItemizedDropdownMenu(selectedItem = data.value.widthUnit, items = unitsDimensions, dropdownExpanded = widthUnitDropdownExpanded)
                }

                Column {
                    CustomTextField(
                        value = data.value.height,
                        error = data.value.heightError,
                        isValueAllowed = { newValue ->
                            if (newValue.matches(Regex("^\\d*(,\\d*)*(\\.\\d{0,2})?$"))) {
                                true
                            } else {
                                data.value.heightError.value = "Invalid"

                                scope.launch {
                                    delay(2000)
                                    data.value.heightError.value = null
                                }

                                false
                            }
                        },
                        label = "Height",
                        modifier = Modifier
                            .defaultMinSize(minHeight = MaterialTheme.sizes.medium)
                            .width(120.dp)
                            .focusRequester(focusRequesters[2]),
                        previousFocusRequester = focusRequesters[1],
                        nextFocusRequester = focusRequesters[3]
                    )

                    ItemizedDropdownMenu(selectedItem = data.value.heightUnit, items = unitsDimensions, dropdownExpanded = heightUnitDropdownExpanded)
                }

                Spacer(modifier = Modifier.width(56.dp))

                Column {
                    CustomTextField(
                        value = data.value.weight,
                        error = data.value.weightError,
                        isValueAllowed = { newValue ->
                            if (newValue.matches(Regex("^\\d*(,\\d*)*(\\.\\d{0,2})?$"))) {
                                true
                            } else {
                                data.value.weightError.value = "Invalid"

                                scope.launch {
                                    delay(2000)
                                    data.value.weightError.value = null
                                }

                                false
                            }
                        },
                        label = "Weight",
                        modifier = Modifier
                            .defaultMinSize(minHeight = MaterialTheme.sizes.medium)
                            .width(120.dp)
                            .focusRequester(focusRequesters[3]),
                        previousFocusRequester = focusRequesters[2],
                        nextFocusRequester = focusRequesters[4]
                    )

                    ItemizedDropdownMenu(selectedItem = data.value.weightUnit, items = unitsWeight, dropdownExpanded = weightDropdownExpanded)
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacers.small)) {
                CustomTextField(
                    value = data.value.description,
                    error = data.value.descriptionError,
                    label = "Description",
                    modifier = Modifier
                        .defaultMinSize(minHeight = MaterialTheme.sizes.medium)
                        .width(568.dp)
                        .focusRequester(focusRequesters[4]),
                    previousFocusRequester = focusRequesters[3],
                    nextFocusRequester = nextFocusRequester ?: focusRequesters[0]
                )
            }
        }
    }
}

