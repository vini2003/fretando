package ui.compose

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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import dev.vini2003.fretando.server.entity.Cargo
import ui.theme.paddings
import ui.theme.spacers

@Composable
fun CargoBlock(cargo: dev.vini2003.fretando.server.entity.Cargo, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(MaterialTheme.paddings.small)
    ) {
        Text(
            text = "Cargo",
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            ),
            modifier = Modifier
                .clip(MaterialTheme.shapes.large)
                .background(MaterialTheme.colorScheme.tertiaryContainer)
                .padding(MaterialTheme.paddings.small)
        )
        Spacer(Modifier.height(MaterialTheme.spacers.verySmall))
        Text(
            text = cargo.description,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = MaterialTheme.paddings.small)
        )
        Spacer(Modifier.height(MaterialTheme.spacers.large))
        Text(
            text = AnnotatedString.Builder().apply {
                withStyle(
                    style = SpanStyle(fontWeight = FontWeight.Bold)
                ) {
                    append("${cargo.length}" + cargo.lengthUnit.asString())
                }
                append(" x ")
                withStyle(
                    style = SpanStyle(fontWeight = FontWeight.Bold)
                ) {
                    append("${cargo.width}" + cargo.widthUnit.asString())
                }
                append(" x ")
                withStyle(
                    style = SpanStyle(fontWeight = FontWeight.Bold)
                ) {
                    append("${cargo.height}" + cargo.heightUnit.asString())
                }
            }.toAnnotatedString(),
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            ),
            modifier = Modifier
                .clip(MaterialTheme.shapes.large)
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(MaterialTheme.paddings.small)
        )
        Spacer(Modifier.height(MaterialTheme.spacers.small))
        Text(
            text = "${cargo.weight}" + cargo.weightUnit.asString(),
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            ),
            modifier = Modifier
                .clip(MaterialTheme.shapes.large)
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(MaterialTheme.paddings.small)
        )
    }
}