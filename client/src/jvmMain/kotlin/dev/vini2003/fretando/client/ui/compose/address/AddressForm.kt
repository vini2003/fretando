@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package dev.vini2003.fretando.client.ui.compose.address

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
import dev.vini2003.fretando.client.ui.compose.data.AddressFormData
import dev.vini2003.fretando.client.ui.compose.text.CustomTextField
import dev.vini2003.fretando.client.ui.theme.paddings
import dev.vini2003.fretando.client.ui.theme.sizes
import dev.vini2003.fretando.client.ui.theme.spacers
import dev.vini2003.fretando.client.util.misc.Countries

@ExperimentalMaterial3Api
@ExperimentalComposeUiApi
@Composable
fun AddressForm(
    label: String = "Address",
    data: MutableState<AddressFormData> = remember { mutableStateOf(AddressFormData()) },
    modifier: Modifier = Modifier,
) {
    val focusRequesters = remember { List(7) { FocusRequester() } }

    var dropdownMenuExpanded by remember { mutableStateOf(false) }
    var dropdownMenuTextFieldSize by remember { mutableStateOf(Size.Zero) }

    val dropdownMenuIcon = if (dropdownMenuExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(
        modifier = modifier.background(MaterialTheme.colorScheme.surface)
    ) {
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
                    value = data.value.street,
                    error = data.value.streetError,
                    label = "Street",
                    modifier = Modifier
                        .defaultMinSize(minHeight = MaterialTheme.sizes.medium)
                        .width(440.dp)
                        .focusRequester(focusRequesters[0]),
                    nextFocusRequester = focusRequesters[1]
                )
                CustomTextField(
                    value = data.value.number,
                    error = data.value.numberError,
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
                    value = data.value.city,
                    error = data.value.cityError,
                    label = "City",
                    modifier = Modifier
                        .defaultMinSize(minHeight = MaterialTheme.sizes.medium)
                        .width(380.dp)
                        .focusRequester(focusRequesters[2]),
                    nextFocusRequester = focusRequesters[3]
                )

                CustomTextField(
                    value = data.value.state,
                    error = data.value.stateError,
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
                    value = data.value.postalCode,
                    error = data.value.postalCodeError,
                    label = "Postal Code",
                    modifier = Modifier
                        .defaultMinSize(minHeight = MaterialTheme.sizes.medium)
                        .width(278.dp)
                        .focusRequester(focusRequesters[4]),
                    nextFocusRequester = focusRequesters[5]
                )

                Box {
                    OutlinedTextField(
                        value = data.value.country.value,
                        onValueChange = { data.value.country.value = it; dropdownMenuExpanded = true },
                        isError = data.value.countryError.value != null,
                        modifier = Modifier
                            .defaultMinSize(minHeight = MaterialTheme.sizes.medium)
                            .onGloballyPositioned { coordinates ->
                                dropdownMenuTextFieldSize = coordinates.size.toSize()
                            }
                            .focusRequester(focusRequesters[5])
                            .onPreviewKeyEvent { keyEvent ->
                                if (keyEvent.key == Key.Tab && keyEvent.type == KeyEventType.KeyDown) {
                                    focusRequesters[6].requestFocus()
                                    true
                                } else {
                                    false
                                }
                            },
                        label = {
                            data.value.countryError.value?.let {
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
                        Countries.filter {
                            it.startsWith(data.value.country.value, ignoreCase = true)
                        }.forEach { c ->
                            DropdownMenuItem({ Text(c) }, onClick = {
                                data.value.country.value = c
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
                    value = data.value.notes,
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

