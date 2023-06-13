package dev.vini2003.fretando.client.ui.compose.address

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.vini2003.fretando.client.ui.theme.paddings
import dev.vini2003.fretando.common.entity.Address

@Composable
fun AddressBlock(
    title: String,
    address: Address,
    modifier: Modifier = Modifier
) {
    // Check if the address is complete
    if (!address.isComplete) return

    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(MaterialTheme.paddings.small)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            ),
            modifier = Modifier
                .clip(MaterialTheme.shapes.large)
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(MaterialTheme.paddings.small)
        )
        Spacer(Modifier.height(2.dp))
        AddressText(text = "${address.street ?: "N/A"}, ${address.number ?: "N/A"}")
        AddressText(text = "${address.city ?: "N/A"}, ${address.state ?: "N/A"}")
        AddressText(text = "${address.postalCode ?: "N/A"}, ${address.country ?: "N/A"}")
    }
}

/**
 * Renders address text.
 *
 * @param text: The text to be displayed.
 * @param modifier: A modifier for customizing the appearance or behavior of the text.
 */
@Composable
fun AddressText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium.copy(
            color = MaterialTheme.colorScheme.onSecondaryContainer
        ),
        modifier = modifier.padding(horizontal = MaterialTheme.paddings.small)
    )
}
