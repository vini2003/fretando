package dev.vini2003.fretando.client.ui.compose.error

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import dev.vini2003.fretando.client.ui.theme.paddings
import dev.vini2003.fretando.client.ui.theme.spacers

@ExperimentalMaterial3Api
@ExperimentalComposeApi
@Composable
fun ErrorCard(
    errorMessage: String,
    onDismissClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(MaterialTheme.paddings.medium),
        shape = MaterialTheme.shapes.medium,
        onClick = onDismissClick
    ) {
        Column(
            modifier = Modifier
                .padding(MaterialTheme.paddings.medium)
        ) {
            Text(
                text = "Error",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                ),
                modifier = Modifier
                    .clip(MaterialTheme.shapes.large)
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .padding(MaterialTheme.paddings.small)
            )
            Text(
                text = "An error has occurred.\nPlease try again later, or contact the developer.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                ),
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.paddings.small, vertical = MaterialTheme.paddings.medium)
            )
            Text(
                text = errorMessage,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onErrorContainer,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .clip(MaterialTheme.shapes.extraLarge)
                    .background(MaterialTheme.colorScheme.errorContainer)
                    .padding(horizontal = MaterialTheme.paddings.medium, vertical = MaterialTheme.paddings.medium)
            )
            Spacer(Modifier.height(MaterialTheme.spacers.medium))
            Button(
                onClick = onDismissClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text("Dismiss")
            }
        }
    }
}
