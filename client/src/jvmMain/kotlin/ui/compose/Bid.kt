package ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Bid() {
    var currencyAmount by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Text("Bid", fontSize = 18.sp, color = MaterialTheme.colors.primary)

        Currency()

        OutlinedTextField(
            value = currencyAmount,
            onValueChange = { currencyAmount = it },
            label = { Text("Currency Amount") },
            modifier = Modifier.defaultMinSize(minHeight = 12.dp)
                .width(568.dp),
        )
    }
}
