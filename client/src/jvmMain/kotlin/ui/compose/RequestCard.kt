package ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import dev.vini2003.fretando.common.`object`.Request
import ui.theme.*

@Composable
fun RequestCard(request: Request) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .defaultMinSize(minHeight = 400.dp, minWidth = 320.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Surface(color = MaterialTheme.colors.lightGray) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.mediumGray)
                        .padding(MaterialTheme.paddings.small)
                ) {
                    // Origin Address
                    Text(
                        text = "Origin",
                        style = MaterialTheme.typography.h6.copy(color = Color.DarkGray, fontWeight = FontWeight.Bold)
                    )
                    Spacer(Modifier.height(MaterialTheme.spacers.verySmall))
                    Text(text = "${request.origin.street}, ${request.origin.number}")
                    Text(text = "${request.origin.city}, ${request.origin.state}")
                    Text(text = "${request.origin.postalCode}, ${request.origin.country}")
                }

                Column(
                    modifier = Modifier.fillMaxWidth().padding(8.dp)
                ) {
                    // Destination Address
                    Text(
                        text = "Destination",
                        style = MaterialTheme.typography.h6.copy(color = Color.DarkGray, fontWeight = FontWeight.Bold)
                    )
                    Spacer(Modifier.height(MaterialTheme.spacers.verySmall))
                    Text(text = "${request.destination.street}, ${request.destination.number}")
                    Text(text = "${request.destination.city}, ${request.destination.state}")
                    Text(text = "${request.destination.postalCode}, ${request.destination.country}")
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.mediumGray)
                        .padding(MaterialTheme.paddings.small)
                ) {
                    // Cargo
                    Text(
                        text = "Cargo",
                        style = MaterialTheme.typography.h6.copy(color = Color.DarkGray, fontWeight = FontWeight.Bold)
                    )
                    Spacer(Modifier.height(MaterialTheme.spacers.verySmall))
                    Text(text = request.cargo.description)
                    Spacer(Modifier.height(MaterialTheme.spacers.small))
                    Text(text = AnnotatedString.Builder().apply {
                        withStyle(
                            style = SpanStyle(fontWeight = FontWeight.Bold)
                        ) {
                            append("${request.cargo.length}")
                        }
                        append(" x ")
                        withStyle(
                            style = SpanStyle(fontWeight = FontWeight.Bold)
                        ) {
                            append("${request.cargo.width}")
                        }
                        append(" x ")
                        withStyle(
                            style = SpanStyle(fontWeight = FontWeight.Bold)
                        ) {
                            append("${request.cargo.height}")
                        }
                    }.toAnnotatedString())
                    Spacer(Modifier.height(MaterialTheme.spacers.small))
                    Text(text = "${request.cargo.weight}")
                }

                // Fill remaining space before the last Row
                Surface(color = MaterialTheme.colors.mediumGray, modifier = Modifier.weight(1f)) {
                    Box(modifier = Modifier.fillMaxSize())
                }

                // Lowest bid
                // You should implement getLowestBid method in your Request class to get the lowest bid.
                val lowestBid = request.bids.minByOrNull { it.currencyAmount }

                Row(
                    modifier = Modifier.fillMaxWidth().background(MaterialTheme.colors.primary),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { /* TODO: Handle bid click */ },
                        modifier = Modifier.padding(MaterialTheme.paddings.small),
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onPrimary)
                    ) {
                        Text("Bid", fontWeight = FontWeight.Bold, color = MaterialTheme.colors.primary)
                    }

                    Text(
                        text = "$${lowestBid?.currencyAmount}",
                        color = MaterialTheme.colors.onPrimary,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(MaterialTheme.paddings.medium)
                    )
                }
            }
        }
    }
}
