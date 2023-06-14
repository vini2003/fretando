package dev.vini2003.fretando.client.ui.compose.cargo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.vini2003.fretando.client.ui.theme.paddings
import dev.vini2003.fretando.client.ui.theme.spacers
import dev.vini2003.fretando.common.entity.Cargo

@Composable
fun CargoBlock(
    cargo: Cargo,
    modifier: Modifier = Modifier
) {
    if (!cargo.isComplete) return

    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(MaterialTheme.paddings.small)
    ) {
        Text(
            text = "Cargo",
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            ),
            modifier = Modifier
                .clip(MaterialTheme.shapes.large)
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(MaterialTheme.paddings.small)
        )
        Spacer(Modifier.height(MaterialTheme.spacers.verySmall))
        Text(
            text = cargo.description ?: "N/A",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = MaterialTheme.paddings.small)
        )

        Spacer(Modifier.height(MaterialTheme.spacers.large))

        Row(
            modifier = Modifier
                .clip(MaterialTheme.shapes.large)
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(horizontal = MaterialTheme.paddings.small),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Weight: ",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            )
            Spacer(Modifier.weight(1f))
            Text(
                text = "%,.2f".format(cargo.weight) + (cargo.weightUnit?.asString() ?: "N/A"),
                style = MaterialTheme.typography.titleSmall.copy(
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            )
        }

        Spacer(Modifier.height(4.dp))

        Row(
            modifier = Modifier
                .clip(MaterialTheme.shapes.large)
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(horizontal = MaterialTheme.paddings.small),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Dimensions: ",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            )
            Spacer(Modifier.weight(1f))
            Text(
                text = "%,.2f".format(cargo.length) + (cargo.lengthUnit?.asString() ?: "N/A")  + " ✕ " +
                        "%,.2f".format(cargo.width) + (cargo.widthUnit?.asString() ?: "N/A") + " ✕ " +
                        "%,.2f".format(cargo.height) + (cargo.heightUnit?.asString() ?: "N/A"),
                style = MaterialTheme.typography.titleSmall.copy(
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                ),
            )
        }
    }
}