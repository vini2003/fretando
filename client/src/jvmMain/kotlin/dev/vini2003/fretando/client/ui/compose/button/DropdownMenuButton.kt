package dev.vini2003.fretando.client.ui.compose.button

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ItemizedDropdownMenu(
    selectedItem: MutableState<String>,
    items: List<String>,
    dropdownExpanded: MutableState<Boolean>
) {
    Box {
        TextButton(onClick = { dropdownExpanded.value = true }, modifier = Modifier.width(120.dp)) {
            Text(selectedItem.value)
            Icon(Icons.Filled.ArrowDropDown, contentDescription = "Open dropdown")
        }

        DropdownMenu(
            expanded = dropdownExpanded.value,
            onDismissRequest = { dropdownExpanded.value = false },
        ) {
            items.forEach { unit ->
                DropdownMenuItem(
                    text = { Text(text = unit) },
                    onClick = {
                        dropdownExpanded.value = false
                        selectedItem.value = unit
                    }
                )
            }
        }
    }
}