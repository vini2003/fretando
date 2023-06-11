package dev.vini2003.fretando.client.ui.compose.cargo

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.vini2003.fretando.client.ui.theme.paddings
import dev.vini2003.fretando.common.entity.Cargo

@Composable
fun CargoCard(
    cargo: Cargo,
    modifier: Modifier = Modifier
) {
    if (!cargo.isComplete) return

    Card(
        modifier = modifier
            .padding(MaterialTheme.paddings.medium),
        shape = RoundedCornerShape(10.dp)
    ) {
        CargoBlock(cargo = cargo)
    }
}
