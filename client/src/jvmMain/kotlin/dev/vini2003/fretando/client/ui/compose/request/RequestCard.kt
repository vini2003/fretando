package dev.vini2003.fretando.client.ui.compose.request

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.vini2003.fretando.client.ui.compose.address.AddressBlock
import dev.vini2003.fretando.client.ui.compose.button.BidButton
import dev.vini2003.fretando.client.ui.compose.button.DeleteButton
import dev.vini2003.fretando.client.ui.compose.button.EditButton
import dev.vini2003.fretando.client.ui.compose.cargo.CargoBlock
import dev.vini2003.fretando.client.ui.theme.paddings
import dev.vini2003.fretando.common.entity.Request

@OptIn(ExperimentalComposeUiApi::class)
@ExperimentalMaterial3Api
@Composable
fun RequestCard(
    request: Request,
    onBidClick: () -> Unit = {},
    onEditClick: () -> Unit = {},
    onRemoveClick: () -> Unit = {},
    enableBid: Boolean = true,
    enableEdit: Boolean = true,
    enableRemove: Boolean = true
) {
    val scope = rememberCoroutineScope()

    Card(
        modifier = Modifier
            .padding(MaterialTheme.paddings.medium),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(modifier = Modifier.width(300.dp)) {
            val lowestBid = request.bids.minByOrNull { it.amount }

            if (lowestBid != null) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Lowest bid",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(MaterialTheme.paddings.medium)
                    )

                    Text(
                        text = "$${"%,.2f".format(lowestBid.amount)}",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(MaterialTheme.paddings.medium)
                    )
                }
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "No bids yet!",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(MaterialTheme.paddings.medium)
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.secondaryContainer),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                AddressBlock(
                    title = "Origin",
                    address = request.origin,
                    modifier = Modifier
                        .weight(1f) // To make sure they share the available space
                        .background(MaterialTheme.colorScheme.primaryContainer)
                )

                Spacer(Modifier.width(16.dp)) // Adding a space between the two blocks for better readability

                AddressBlock(
                    title = "Destination",
                    address = request.destination,
                    modifier = Modifier
                        .weight(1f) // To make sure they share the available space
                        .background(MaterialTheme.colorScheme.primaryContainer)
                )
            }

            CargoBlock(cargo = request.cargo, modifier = Modifier.fillMaxWidth())

            if (enableRemove || enableEdit || enableBid) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.tertiaryContainer),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (enableBid) {
                        BidButton(onBidClick)
                    }

                    if (enableEdit) {
                        EditButton(onEditClick)
                    }

                    if (enableRemove) {
                        DeleteButton(onRemoveClick)
                    }
                }
            }
        }
    }
}

