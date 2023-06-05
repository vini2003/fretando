package ui.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ui.theme.paddings
import ui.theme.spacers

@ExperimentalComposeUiApi
@Composable
fun RequestForm(
    onNavigationIconClick: () -> Unit = {},
    onCancelClick: () -> Unit = {},
    onCreateClick: () -> Unit = {},
) {
    val baseModifier = Modifier
        .fillMaxWidth()
        .widthIn(min = 580.dp)

    Column(modifier = baseModifier.verticalScroll(rememberScrollState())) {
        AddressForm("Origin Address")
        Spacer(Modifier.height(MaterialTheme.spacers.medium))
        AddressForm("Destination Address")
        Spacer(Modifier.height(MaterialTheme.spacers.medium))
        Cargo()
        Spacer(Modifier.height(MaterialTheme.spacers.medium))

        Row {
            Button(
                onClick = onCancelClick,
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.paddings.medium)
                    .width(54.dp)
                    .height(54.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.error),
            ) {
                Text("X", style = MaterialTheme.typography.button.copy(color = Color.White, fontWeight = FontWeight.Bold))
            }

            Spacer(Modifier.width(8.dp))

            Button(
                onClick = onCreateClick,
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.paddings.medium)
                    .weight(1.0f)
                    .height(54.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
            ) {
                Text("Create", style = MaterialTheme.typography.button.copy(color = Color.White, fontWeight = FontWeight.Bold))
            }
        }
    }
}
