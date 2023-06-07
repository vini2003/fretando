@file:OptIn(ExperimentalMaterial3Api::class)

package ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import java.util.*

@Composable
fun Currency(label: String = "Currency") {
    var currency by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Box {
        OutlinedTextField(
            value = currency,
            onValueChange = { currency = it; expanded = true },
            label = { Text(label) },
            trailingIcon = {
                Icon(
                    icon,
                    "Dropdown Arrow",
                    Modifier.clickable { expanded = !expanded }
                )
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            Currency.getAvailableCurrencies().filter { it.currencyCode.startsWith(currency, ignoreCase = true) }.forEach { c ->
                DropdownMenuItem({ Text(c.currencyCode) }, onClick = {
                    currency = c.currencyCode
                    expanded = false
                })
            }
        }
    }
}
