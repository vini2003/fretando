package ui.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

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

    Column(modifier = baseModifier) {
        TopAppBar(
            title = { Text("Create Request") },
            navigationIcon = {
                IconButton(onClick = onNavigationIconClick) {
                    Icon(Icons.Default.Menu, contentDescription = "Menu")
                }
            }
        )

        AddressForm("Origin Address")
        Spacer(Modifier.height(16.dp))
        AddressForm("Destination Address")
        Spacer(Modifier.height(16.dp))
        Cargo()
        Spacer(Modifier.height(16.dp))

        Row {
            Button(
                onClick = onCancelClick,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
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
                    .padding(horizontal = 8.dp)
                    .weight(1.0f)
                    .height(54.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
            ) {
                Text("Create", style = MaterialTheme.typography.button.copy(color = Color.White, fontWeight = FontWeight.Bold))
            }
        }
    }
}
