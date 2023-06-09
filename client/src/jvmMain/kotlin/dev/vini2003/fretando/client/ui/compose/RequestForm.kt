@file:OptIn(ExperimentalComposeUiApi::class)

package dev.vini2003.fretando.client.ui.compose

import CargoForm
import CargoFormData
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.vini2003.fretando.client.ui.theme.paddings
import dev.vini2003.fretando.client.ui.theme.spacers

data class RequestFormData(
    val originAddressFormData: MutableState<AddressFormData> = mutableStateOf(AddressFormData()),
    val destinationAddressFormData: MutableState<AddressFormData> = mutableStateOf(AddressFormData()),
    val cargoFormData: MutableState<CargoFormData> = mutableStateOf(CargoFormData()),
) {
    fun validate(): Boolean {
        return originAddressFormData.value.validate() && destinationAddressFormData.value.validate() && cargoFormData.value.validate()
    }
}

@ExperimentalMaterial3Api
@ExperimentalComposeUiApi
@Composable
@Preview
fun RequestForm(
    onCancelClick: () -> Unit = {},
    onCreateClick: () -> Unit = {},
    data: MutableState<RequestFormData> = remember { mutableStateOf(RequestFormData()) }
) {
    val originAddressFormData = remember { data.value.originAddressFormData }
    val destinationAddressFormData = remember { data.value.destinationAddressFormData }
    val cargoFormData = remember { data.value.cargoFormData }

    Column(
        modifier = Modifier
            .width(600.dp)
            .shadow(4.dp, MaterialTheme.shapes.medium)
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.surface)
            .verticalScroll(rememberScrollState())
            .padding(MaterialTheme.paddings.large)
    ) {
        AddressSection(originAddressFormData, destinationAddressFormData)
        Spacer(Modifier.height(MaterialTheme.spacers.medium))
        CargoForm(cargoFormData)
        Spacer(Modifier.height(MaterialTheme.spacers.medium))
        ButtonsSection(onCancelClick, onCreateClick)
    }
}

@ExperimentalMaterial3Api
@ExperimentalComposeUiApi
@Composable
@Preview
fun AddressSection(
    originAddressFormData: MutableState<AddressFormData>,
    destinationAddressFormData: MutableState<AddressFormData>,
) {
    AddressForm(label = "Origin Address", originAddressFormData)
    Spacer(Modifier.height(MaterialTheme.spacers.medium))
    AddressForm(label = "Destination Address", destinationAddressFormData)
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
    }
}
