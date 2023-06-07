@file:OptIn(ExperimentalComposeUiApi::class)

package ui.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ui.theme.paddings
import ui.theme.sizes
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
        AddressSection()
        Spacer(Modifier.height(MaterialTheme.spacers.medium))
        Cargo()
        Spacer(Modifier.height(MaterialTheme.spacers.medium))
        ButtonsSection(onCancelClick, onCreateClick)
    }
}

@Composable
fun AddressSection() {
    AddressForm("Origin Address")
    Spacer(Modifier.height(MaterialTheme.spacers.medium))
    AddressForm("Destination Address")
}

@Composable
fun ButtonsSection(onCancelClick: () -> Unit, onCreateClick: () -> Unit) {
    Row {
        CancelButton(onCancelClick = onCancelClick)
        Spacer(Modifier.width(MaterialTheme.sizes.small))
        CreateButton(Modifier.weight(1.0f), onCreateClick = onCreateClick)
    }
}

@Composable
fun CancelButton(onCancelClick: () -> Unit) {
    Button(
        onClick = onCancelClick,
        modifier = Modifier
            .padding(horizontal = MaterialTheme.paddings.medium)
            .width(54.dp)
            .height(54.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
    ) {
        Text("X", style = MaterialTheme.typography.displayMedium.copy(color = Color.White, fontWeight = FontWeight.Bold))
        // TODO: typography became displayMedium.
    }
}

@Composable
fun CreateButton(modifier: Modifier, onCreateClick: () -> Unit) {
    Button(
        onClick = onCreateClick,
        modifier = modifier
            .padding(horizontal = MaterialTheme.paddings.medium)
            .height(54.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
    ) {
        Text("Create", style = MaterialTheme.typography.displayMedium.copy(color = Color.White, fontWeight = FontWeight.Bold))
        // TODO: typography became displayMedium.
    }
}
