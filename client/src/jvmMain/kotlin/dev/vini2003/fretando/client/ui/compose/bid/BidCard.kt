package dev.vini2003.fretando.client.ui.compose.bid

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.vini2003.fretando.client.repository.RemoteRequestRepository
import dev.vini2003.fretando.client.ui.compose.address.AddressBlock
import dev.vini2003.fretando.client.ui.compose.application.LocalAddPopup
import dev.vini2003.fretando.client.ui.compose.application.LocalRemovePopup
import dev.vini2003.fretando.client.ui.compose.button.DeleteButton
import dev.vini2003.fretando.client.ui.compose.button.EditButton
import dev.vini2003.fretando.client.ui.compose.button.RequestButton
import dev.vini2003.fretando.client.ui.compose.cargo.CargoBlock
import dev.vini2003.fretando.client.ui.compose.error.ErrorCard
import dev.vini2003.fretando.client.ui.theme.paddings
import dev.vini2003.fretando.common.entity.Bid
import dev.vini2003.fretando.common.entity.Request
import kotlinx.coroutines.launch

@ExperimentalComposeApi
@ExperimentalMaterial3Api
@Composable
fun BidCard(
    bid: Bid,
    onRequestClick: () -> Unit = {},
    onEditClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {},
    enableRequest: Boolean = true,
    enableEdit: Boolean = true,
    enableDelete: Boolean = true
) {
    val addPopup = LocalAddPopup.current
    val removePopup = LocalRemovePopup.current

    val scope = rememberCoroutineScope()

    var request by remember { mutableStateOf<Request?>(null) }

    var firstTime by remember { mutableStateOf(true) }

    if (firstTime) {
        firstTime = false

        scope.launch {
            try {
                request = RemoteRequestRepository.findById(bid.requestId)
            } catch (exception: Exception) {
                addPopup { id ->
                    ErrorCard(
                        errorMessage = exception.message ?: "Unknown error",
                        onDismissClick = { removePopup(id) }
                    )
                }
            }
        }
    }

    if (request == null || !request!!.isComplete) return

    Card(
        modifier = Modifier
            .padding(MaterialTheme.paddings.medium),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(modifier = Modifier.width(300.dp)) {
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

            val lowestBid = request!!.bids?.minByOrNull { it.amount }

            if (lowestBid != null && lowestBid != bid) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.error),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Lowest bid",
                        color = MaterialTheme.colorScheme.onError,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(MaterialTheme.paddings.medium)
                    )

                    Text(
                        text = "$${"%,.2f".format(request!!.bids.minBy { it.amount }.amount)}",
                        color = MaterialTheme.colorScheme.onError,
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
                    address = request!!.origin,
                    modifier = Modifier
                        .weight(1f)
                        .background(MaterialTheme.colorScheme.primaryContainer)
                )

                Spacer(Modifier.width(16.dp))

                AddressBlock(
                    title = "Destination",
                    address = request!!.destination,
                    modifier = Modifier
                        .weight(1f)
                        .background(MaterialTheme.colorScheme.primaryContainer)
                )
            }

            CargoBlock(
                cargo = request!!.cargo,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primaryContainer)
            )

            if (enableDelete || enableEdit || enableRequest) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.tertiaryContainer),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (enableRequest) {
                        RequestButton(onRequestClick)
                    }

                    if (enableEdit) {
                        EditButton(onEditClick)
                    }

                    if (enableDelete) {
                        DeleteButton(onDeleteClick)
                    }
                }
            }
        }
    }
}

