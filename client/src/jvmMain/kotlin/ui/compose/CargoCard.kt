package ui.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.vini2003.fretando.common.`object`.Cargo
import ui.theme.paddings

@Composable
fun CargoCard(cargo: Cargo) {
    Card(
        modifier = Modifier
            .padding(MaterialTheme.paddings.medium),
        shape = RoundedCornerShape(10.dp)
    ) {
        CargoBlock(cargo = cargo)
    }
}
