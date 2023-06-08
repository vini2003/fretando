@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package dev.vini2003.fretando.client.ui.compose

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.vini2003.fretando.client.ui.theme.paddings

@Composable
@Preview
fun Bid() {
    var currencyAmount by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(MaterialTheme.paddings.small)
    ) {
        Text("Bid", fontSize = 18.sp, color = MaterialTheme.colorScheme.primary)

        OutlinedTextField(
            value = currencyAmount,
            onValueChange = { currencyAmount = it },
            label = { Text("Amount") },
            modifier = Modifier.defaultMinSize(minHeight = 12.dp)
                .width(568.dp),
        )
    }
}
