package dev.vini2003.fretando.client.ui.compose.welcome

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.vini2003.fretando.client.ui.theme.spacers

@Composable
fun Welcome() {
    Column(
        modifier = Modifier.width(600.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome!",
            style = MaterialTheme.typography.displayMedium.copy(
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8F),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left
            )
        )
        Spacer(Modifier.height(MaterialTheme.spacers.medium))
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp),
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1F))
        Spacer(Modifier.height(MaterialTheme.spacers.medium))
        Text(
            text = """
                 Fretando is a tool that allows you to create and manage your own cargo requests, and bid on other people's requests.
                    
                 To get started as a cargo owner, head over to the Requests tab and create your first request!
                 You may do that by clicking the "+" button on the bottom right of the screen.
                    
                 To get started as a freighter, head over to the Requests tab and create your first bid!
                 You may do that by clicking the "Bid" button on requests that seem interesting.
                    
                 You can see all the available requests on the "Requests" section.
                 You can see all the available bids on the "Bids" section.
            """.trimIndent(),
            style = MaterialTheme.typography.headlineSmall.copy(
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5F),
                textAlign = TextAlign.Left
            )
        )
    }
}
