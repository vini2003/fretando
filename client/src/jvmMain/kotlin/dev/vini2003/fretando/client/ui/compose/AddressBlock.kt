package dev.vini2003.fretando.client.ui.compose

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
fun AddressBlock(title: String, address: Address, modifier: Modifier = Modifier) {
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
        Text(
            text = "${address.street}, ${address.number}",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSecondaryContainer
            ),
            modifier = Modifier.padding(horizontal = MaterialTheme.paddings.small)
        )
        Text(
            text = "${address.city}, ${address.state}",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSecondaryContainer
            ),
            modifier = Modifier.padding(horizontal = MaterialTheme.paddings.small)
        )
        Text(
            text = "${address.postalCode}, ${address.country}",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSecondaryContainer
            ),
            modifier = Modifier.padding(horizontal = MaterialTheme.paddings.small)
        )
    }
}