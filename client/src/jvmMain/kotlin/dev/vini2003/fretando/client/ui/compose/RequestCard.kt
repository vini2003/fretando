package dev.vini2003.fretando.client.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.vini2003.fretando.client.repository.RemoteRequestRepository
import dev.vini2003.fretando.client.ui.theme.paddings
import dev.vini2003.fretando.common.entity.Bid
import dev.vini2003.fretando.common.entity.Request
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun RequestCard(request: Request) {
    val scope = rememberCoroutineScope()

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
                val addPopup = LocalAddPopup.current
                val removePopup = LocalRemovePopup.current

                Button(
                    onClick = {
                        addPopup { id ->
                            val bidFormData = remember {
                                mutableStateOf(
                                    BidFormData()
                                )
                            }

                            BidForm(
                                request.id,
                                data = bidFormData,
                                onCancelClick = {
                                    removePopup(id)
                                },
                                onBidClick = {
                                    if (bidFormData.value.validate()) {
                                        scope.launch {
                                            val bid = Bid(
                                                request.id,
                                                bidFormData.value.amount.value.toDouble(),
                                            )

                                            request.bids.plusAssign(bid)

                                            RemoteRequestRepository.save(request)

                                            removePopup(id)
                                        }
                                    }
                                })
                        }
                    },
                    modifier = Modifier.padding(MaterialTheme.paddings.small),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onPrimary)
                ) {
                    Text("Bid", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                }

                if (lowestBid != null) {
                    Text(
                        text = "$${"%,.2f".format(lowestBid.amount)}",
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

