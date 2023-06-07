@file:OptIn(ExperimentalComposeUiApi::class)

package ui.compose

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ui.theme.paddings
import ui.theme.sizes
import ui.theme.spacers

@Composable
@Preview
fun ShowRequestFormPopup() {
    RequestForm(onCreateClick = {
        // Do something with the filled in request here
        // Close the dialog

    }, onCancelClick = {
        // Close the dialog

    })
}

@ExperimentalComposeUiApi
@Composable
@Preview
fun RequestForm(
    onNavigationIconClick: () -> Unit = {},
    onCancelClick: () -> Unit = {},
    onCreateClick: () -> Unit = {},
) {
    val baseModifier = Modifier
        .defaultMinSize(minWidth = 580.dp, minHeight = 640.dp)

    Column(
        modifier = baseModifier
            .width(540.dp)
            .height(680.dp)
            .padding(MaterialTheme.paddings.large)
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.surface)
            .shadow(4.dp)
            .verticalScroll(rememberScrollState())
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
        Spacer(Modifier.width(MaterialTheme.sizes.small))
        CreateButton(Modifier, onCreateClick = onCreateClick)
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
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.errorContainer),
    ) {
        Text("X", style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onErrorContainer, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center))
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
        Text("Create", style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onPrimary, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center))
        // TODO: typography became displayMedium.
    }
}
