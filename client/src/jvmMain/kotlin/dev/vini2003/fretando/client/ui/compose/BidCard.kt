package dev.vini2003.fretando.client.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.vini2003.fretando.common.entity.Bid
import dev.vini2003.fretando.client.ui.theme.paddings

@Composable
fun BidCard(bid: Bid) {
    Card(
        modifier = Modifier
            .padding(MaterialTheme.paddings.medium),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { /* TODO: Handle bid click */ },
                modifier = Modifier.padding(MaterialTheme.paddings.small),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onPrimary)
            ) {
                Text("Bid", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
        }

            Text(
                text = "${bid.amount}",
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(MaterialTheme.paddings.medium)
            )
        }
    }
}
