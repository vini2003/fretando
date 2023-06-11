package dev.vini2003.fretando.client.ui.compose.button

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import dev.vini2003.fretando.client.ui.theme.paddings

@Composable
fun BidButton(onBidClick: () -> Unit) {
    Button(
        onClick = onBidClick,
        modifier = Modifier.padding(MaterialTheme.paddings.small),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary)
    ) {
        Text("Bid", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onTertiary)
    }
}