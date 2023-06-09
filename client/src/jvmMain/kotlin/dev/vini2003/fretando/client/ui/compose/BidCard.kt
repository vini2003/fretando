package dev.vini2003.fretando.client.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.vini2003.fretando.client.repository.RemoteRequestRepository
import dev.vini2003.fretando.client.ui.theme.paddings
import dev.vini2003.fretando.common.entity.Bid
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BidCard(
    bid: Bid,
    onRemoveClick: () -> Unit = {},
    showRequestButton: Boolean = true,
    showEditButton: Boolean = true,
    showRemoveButton: Boolean = true
) {
    Card(
        modifier = Modifier
            .padding(MaterialTheme.paddings.medium),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(modifier = Modifier.width(300.dp)) {
            val addPopup = LocalAddPopup.current
            val removePopup = LocalRemovePopup.current

            val request = runBlocking {
                RemoteRequestRepository.findById(bid.requestId)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Your bid",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(MaterialTheme.paddings.medium)
                )

                Text(
                    text = "$${"%,.2f".format(bid.amount)}",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(MaterialTheme.paddings.medium)
                )
            }

            val lowestBid = request.bids?.minByOrNull { it.amount }

            if (lowestBid != null && lowestBid != bid) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.errorContainer),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Lowest bid",
                        color = MaterialTheme.colorScheme.onErrorContainer,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(MaterialTheme.paddings.medium)
                    )

                    Text(
                        text = "$${"%,.2f".format(request.bids.minBy { it.amount }.amount)}",
                        color = MaterialTheme.colorScheme.onErrorContainer,
                        fontWeight = FontWeight.Bold,
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

            CargoBlock(
                cargo = request.cargo,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primaryContainer)
            )

            if (showRemoveButton || showEditButton || showRequestButton) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.tertiaryContainer),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (showRequestButton) {
                        Button(
                            onClick = {
                                addPopup { id ->
                                    RequestCard(request = request)
                                }
                            },
                            modifier = Modifier
                                .padding(MaterialTheme.paddings.small),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary)
                        ) {
                            Text("Request", color = MaterialTheme.colorScheme.onTertiary, fontWeight = FontWeight.Bold)
                        }
                    }

                    if (showEditButton) {
                        Button(
                            onClick = {
                                addPopup { id ->
                                    BidForm(requestId = request.id)
                                }
                            },
                            modifier = Modifier
                                .padding(MaterialTheme.paddings.small),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                        ) {
                            Text("Edit", color = MaterialTheme.colorScheme.onSecondary, fontWeight = FontWeight.Bold)
                        }
                    }

                    if (showRemoveButton) {
                        Button(
                            onClick = {
                                onRemoveClick()
                            },
                            modifier = Modifier
                                .padding(MaterialTheme.paddings.small),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.errorContainer)
                        ) {
                            Text(
                                "✕",
                                color = MaterialTheme.colorScheme.onErrorContainer,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}
