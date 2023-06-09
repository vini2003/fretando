@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package dev.vini2003.fretando.client.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.input.key.*
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import dev.vini2003.fretando.client.ui.theme.paddings
import dev.vini2003.fretando.client.ui.theme.sizes
import dev.vini2003.fretando.client.ui.theme.spacers
import dev.vini2003.fretando.client.util.misc.Countries

data class AddressFormData(
    val street: MutableState<String> = mutableStateOf(""),
    val number: MutableState<String> = mutableStateOf(""),
    val city: MutableState<String> = mutableStateOf(""),
    val state: MutableState<String> = mutableStateOf(""),
    val postalCode: MutableState<String> = mutableStateOf(""),
    val country: MutableState<String> = mutableStateOf(""),
    val notes: MutableState<String> = mutableStateOf(""),

    val streetError: MutableState<String?> = mutableStateOf(null),
    val numberError: MutableState<String?> = mutableStateOf(null),
    val cityError: MutableState<String?> = mutableStateOf(null),
    val stateError: MutableState<String?> = mutableStateOf(null),
    val postalCodeError: MutableState<String?> = mutableStateOf(null),
    val countryError: MutableState<String?> = mutableStateOf(null),
) {
    fun validate(): Boolean {
        var valid = true

        if (street.value.isEmpty()) {
            streetError.value = "Street is required"
            valid = false
        } else {
            streetError.value = null
        }

        if (number.value.isEmpty()) {
            numberError.value = "Number is required"
            valid = false
        } else {
            numberError.value = null
        }

        if (city.value.isEmpty()) {
            cityError.value = "City is required"
            valid = false
        } else {
            cityError.value = null
        }

        if (state.value.isEmpty()) {
            stateError.value = "State is required"
            valid = false
        } else {
            stateError.value = null
        }

        if (postalCode.value.isEmpty()) {
            postalCodeError.value = "Postal code is required"
            valid = false
        } else {
            postalCodeError.value = null
        }

        if (country.value.isEmpty()) {
            countryError.value = "Country is required"
            valid = false
        } else {
            countryError.value = null
        }

        return valid
    }
}

@ExperimentalMaterial3Api
@ExperimentalComposeUiApi
@Composable
fun AddressForm(
    label: String = "Address",
    data: MutableState<AddressFormData> = mutableStateOf(AddressFormData())
) {
    val focusRequesters = remember { List(7) { FocusRequester() } }

    val street = remember { data.value.street }
    val number = remember { data.value.number }
    val city = remember { data.value.city }
    val state = remember { data.value.state }
    val postalCode = remember { data.value.postalCode }
    val country = remember { data.value.country }
    val notes = remember { data.value.notes }

    val streetError = remember { data.value.streetError }
    val numberError = remember { data.value.numberError }
    val cityError = remember { data.value.cityError }
    val stateError = remember { data.value.stateError }
    val postalCodeError = remember { data.value.postalCodeError }
    val countryError = remember { data.value.countryError }

    var dropdownMenuExpanded by remember { mutableStateOf(false) }

    var dropdownMenuTextFieldSize by remember { mutableStateOf(Size.Zero) }

    val dropdownMenuIcon = if (dropdownMenuExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(modifier = Modifier.background(MaterialTheme.colorScheme.surface)) {
        Text(
            label,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75F), // TODO: Check this!
            modifier = Modifier.padding(MaterialTheme.paddings.small)
        )

        Divider(
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.0F), // TODO: Check this!
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = MaterialTheme.paddings.small)
        )

        Column(
            modifier = Modifier.padding(MaterialTheme.paddings.small)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacers.small)
            ) {
                CustomTextField(
                    value = street,
                    error = streetError,
                    label = "Street",
                    modifier = Modifier
                        .defaultMinSize(minHeight = MaterialTheme.sizes.medium)
                        .width(440.dp)
                        .focusRequester(focusRequesters[0]),
                    nextFocusRequester = focusRequesters[1]
                )
                CustomTextField(
                    value = number,
                    error = numberError,
                    label = "Number",
                    modifier = Modifier
                        .defaultMinSize(minHeight = MaterialTheme.sizes.medium)
                        .width(120.dp)
                        .focusRequester(focusRequesters[1]),
                    nextFocusRequester = focusRequesters[2]
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.paddings.small)
            ) {
                CustomTextField(
                    value = city,
                    error = cityError,
                    label = "City",
                    modifier = Modifier
                        .defaultMinSize(minHeight = MaterialTheme.sizes.medium)
                        .width(380.dp)
                        .focusRequester(focusRequesters[2]),
                    nextFocusRequester = focusRequesters[3]
                )

                CustomTextField(
                    value = state,
                    error = stateError,
                    label = "State",
                    modifier = Modifier
                        .defaultMinSize(minHeight = MaterialTheme.sizes.medium)
                        .width(180.dp)
                        .focusRequester(focusRequesters[3]),
                    nextFocusRequester = focusRequesters[4]
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.paddings.small)
            ) {
                CustomTextField(
                    value = postalCode,
                    error = postalCodeError,
                    label = "Postal Code",
                    modifier = Modifier
                        .defaultMinSize(minHeight = MaterialTheme.sizes.medium)
                        .width(278.dp)
                        .focusRequester(focusRequesters[4]),
                    nextFocusRequester = focusRequesters[5]
                )

                Box {
                    OutlinedTextField(
                        value = country.value,
                        onValueChange = { country.value = it; dropdownMenuExpanded = true },
                        isError = countryError.value != null,
                        modifier = Modifier
                            .defaultMinSize(minHeight = MaterialTheme.sizes.medium)
                            .onGloballyPositioned { coordinates ->
                                dropdownMenuTextFieldSize = coordinates.size.toSize()
                            }
                            .focusRequester(focusRequesters[5])
                            .onKeyEvent { keyEvent ->
                                if (keyEvent.key == Key.Tab && keyEvent.type == KeyEventType.KeyUp) {
                                    focusRequesters[6].requestFocus()
                                    true
                                } else {
                                    false
                                }
                            },
                        label = {
                            countryError.value?.let {
                                Text(
                                    it,
                                    color = MaterialTheme.colorScheme.error,
                                    style = TextStyle(fontSize = 14.sp)
                                )
                            } ?: Text(text = "Country", fontSize = 14.sp)
                        },
                        trailingIcon = {
                            Icon(
                                dropdownMenuIcon,
                                "Dropdown Arrow",
                                Modifier.clickable(
                                    onClick = { dropdownMenuExpanded = !dropdownMenuExpanded },
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() }
                                )
                            )
                        }
                    )

                    DropdownMenu(
                        focusable = false,
                        expanded = dropdownMenuExpanded,
                        onDismissRequest = { dropdownMenuExpanded = false },
                        modifier = Modifier
                            .defaultMinSize(minHeight = MaterialTheme.sizes.medium)
                            .heightIn(max = 200.dp)
                            .width(with(LocalDensity.current) { dropdownMenuTextFieldSize.width.toDp() })
                    ) {
                        Countries.filter { it.startsWith(country.value, ignoreCase = true) }.forEach { c ->
                            DropdownMenuItem({ Text(c) }, onClick = {
                                country.value = c
                                dropdownMenuExpanded = false
                            })
                        }
                    }
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacers.small)
            ) {
                CustomTextField(
                    value = notes,
                    error = mutableStateOf(null),
                    label = "Notes",
                    modifier = Modifier
                        .defaultMinSize(minHeight = MaterialTheme.sizes.medium)
                        .width(568.dp)
                        .focusRequester(focusRequesters[6]),
                    nextFocusRequester = focusRequesters[0]
                )
            }
        }
    }
}

