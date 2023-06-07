package ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import dev.vini2003.fretando.common.`object`.Address
import dev.vini2003.fretando.common.`object`.Bid
import dev.vini2003.fretando.common.`object`.Cargo
import dev.vini2003.fretando.common.`object`.Request
import ui.theme.*

@Composable
fun RequestCard(request: Request) {
    Card(
        modifier = Modifier
            .padding(MaterialTheme.paddings.medium)
            .defaultMinSize(minHeight = 400.dp, minWidth = 320.dp),
        // TODO: Check if we need CardElevation.
        shape = RoundedCornerShape(10.dp)
    ) {
        Surface(color = Color.LightGray.copy(alpha = 0.1F)) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                AddressBlock(title = "Origin", address = request.origin)
                AddressBlock(title = "Destination", address = request.destination)
                CargoBlock(cargo = request.cargo)
                Spacer(Modifier.weight(1f))
                LowestBidBlock(bids = request.bids)
            }
        }
    }
}

@Composable
fun AddressBlock(title: String, address: Address) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(MaterialTheme.paddings.small)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            ),
            modifier = Modifier
                .clip(MaterialTheme.shapes.large)
                .background(MaterialTheme.colorScheme.tertiaryContainer)
                .padding(MaterialTheme.paddings.small)
        )
        Spacer(Modifier.height(MaterialTheme.spacers.verySmall))
        Text(
            text = "${address.street}, ${address.number}",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = MaterialTheme.paddings.small)
        )
        Text(
            text = "${address.city}, ${address.state}",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = MaterialTheme.paddings.small)
        )
        Text(
            text = "${address.postalCode}, ${address.country}",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = MaterialTheme.paddings.small)
        )
    }
}

@Composable
fun CargoBlock(cargo: Cargo) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(MaterialTheme.paddings.small)
    ) {
        Text(
            text = "Cargo",
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            ),
            modifier = Modifier
                .clip(MaterialTheme.shapes.large)
                .background(MaterialTheme.colorScheme.tertiaryContainer)
                .padding(MaterialTheme.paddings.small)
        )
        Spacer(Modifier.height(MaterialTheme.spacers.verySmall))
        Text(
            text = cargo.description,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = MaterialTheme.paddings.small)
        )
        Spacer(Modifier.height(MaterialTheme.spacers.large))
        Text(
            text = AnnotatedString.Builder().apply {
                withStyle(
                    style = SpanStyle(fontWeight = FontWeight.Bold)
                ) {
                    append("${cargo.length}m")
                }
                append(" x ")
                withStyle(
                    style = SpanStyle(fontWeight = FontWeight.Bold)
                ) {
                    append("${cargo.width}m")
                }
                append(" x ")
                withStyle(
                    style = SpanStyle(fontWeight = FontWeight.Bold)
                ) {
                    append("${cargo.height}m")
                }
            }.toAnnotatedString(),
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            ),
            modifier = Modifier
                .clip(MaterialTheme.shapes.large)
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(MaterialTheme.paddings.small)
        )
        Spacer(Modifier.height(MaterialTheme.spacers.small))
        Text(
            text = "${cargo.weight}kg",
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            ),
            modifier = Modifier
                .clip(MaterialTheme.shapes.large)
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(MaterialTheme.paddings.small)
        )
    }
}

@Composable
fun LowestBidBlock(bids: List<Bid>) {
    val lowestBid = bids.minByOrNull { it.currencyAmount }
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
            // TODO: Check if containerColor is what backgroundColor became.
        ) {
            Text("Bid", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
        }

        Text(
            text = "$${lowestBid?.currencyAmount}",
            color = MaterialTheme.colorScheme.onPrimary,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(MaterialTheme.paddings.medium)
        )
    }
}
