package ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.vini2003.fretando.server.entity.Request
import ui.theme.paddings

@Composable
fun RequestCard(request: dev.vini2003.fretando.server.entity.Request) {
    Card(
        modifier = Modifier
            .padding(MaterialTheme.paddings.medium),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(modifier = Modifier.width(300.dp)) {
            AddressBlock(title = "Origin", address = request.origin, modifier = Modifier.fillMaxWidth())
            AddressBlock(title = "Destination", address = request.destination, modifier = Modifier.fillMaxWidth())
            CargoBlock(cargo = request.cargo, modifier = Modifier.fillMaxWidth())

            val lowestBid = request.bids.minByOrNull { it.amount }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
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

                if (lowestBid != null) {
                    Text(
                        text = "$${lowestBid?.amount}",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(MaterialTheme.paddings.medium)
                    )
                } else {
                    Text(
                        text = "No bids yet!",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(MaterialTheme.paddings.medium)
                    )
                }
            }
        }
    }
}

