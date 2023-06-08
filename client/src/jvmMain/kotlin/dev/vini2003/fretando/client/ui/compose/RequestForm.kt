@file:OptIn(ExperimentalComposeUiApi::class)

package dev.vini2003.fretando.client.ui.compose

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.vini2003.fretando.client.ui.theme.paddings
import dev.vini2003.fretando.client.ui.theme.spacers

@ExperimentalComposeUiApi
@Composable
@Preview
fun RequestForm(
    onCancelClick: () -> Unit = {},
    onCreateClick: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .width(600.dp)
            .height(970.dp)
            .shadow(4.dp, MaterialTheme.shapes.medium)
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.surface)
            .verticalScroll(rememberScrollState())
            .padding(MaterialTheme.paddings.large)
    ) {
        AddressSection()
        Spacer(Modifier.height(MaterialTheme.spacers.medium))
        CargoForm()
        Spacer(Modifier.height(MaterialTheme.spacers.medium))
        ButtonsSection(onCancelClick, onCreateClick)
    }
}

@Composable
@Preview
fun AddressSection() {
    AddressForm("Origin Address")
    Spacer(Modifier.height(MaterialTheme.spacers.medium))
    AddressForm("Destination Address")
}

@Composable
fun ButtonsSection(onCancelClick: () -> Unit, onCreateClick: () -> Unit) {
    Row {
        CancelButton(onCancelClick = onCancelClick)
        Spacer(Modifier.weight(1f))
        CreateButton(Modifier, onCreateClick = onCreateClick)
    }
}

@Composable
fun CancelButton(onCancelClick: () -> Unit) {
    Button(
        onClick = onCancelClick,
        modifier = Modifier
            .height(54.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.errorContainer),
    ) {
        Text("Close", style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onErrorContainer, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center))
        // TODO: typography became displayMedium.
    }
}

@Composable
fun CreateButton(modifier: Modifier, onCreateClick: () -> Unit) {
    Button(
        onClick = onCreateClick,
        modifier = modifier
            .height(54.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
    ) {
        Text("Create", style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onPrimary, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center))
        // TODO: typography became displayMedium.
    }
}
