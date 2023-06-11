@file:OptIn(ExperimentalComposeUiApi::class)

package dev.vini2003.fretando.client.ui.compose.request

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import dev.vini2003.fretando.client.ui.compose.address.AddressForm
import dev.vini2003.fretando.client.ui.compose.button.CancelButton
import dev.vini2003.fretando.client.ui.compose.button.CreateButton
import dev.vini2003.fretando.client.ui.compose.cargo.CargoForm
import dev.vini2003.fretando.client.ui.compose.data.AddressFormData
import dev.vini2003.fretando.client.ui.compose.data.RequestFormData
import dev.vini2003.fretando.client.ui.theme.paddings
import dev.vini2003.fretando.client.ui.theme.spacers

@ExperimentalMaterial3Api
@ExperimentalComposeUiApi
@Composable
@Preview
fun RequestForm(
    onCancelClick: () -> Unit = {},
    onCreateClick: () -> Unit = {},
    onConfirmClick: () -> Unit = {},
    enableCancel: Boolean = true,
    enableCreate: Boolean = true,
    enableConfirm: Boolean = false,
    data: MutableState<RequestFormData> = remember { mutableStateOf(RequestFormData()) },
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(600.dp)
            .shadow(4.dp, MaterialTheme.shapes.medium)
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.surface)
            .verticalScroll(rememberScrollState())
            .padding(MaterialTheme.paddings.large)
    ) {
        AddressSection(data.value.originAddressFormData, data.value.destinationAddressFormData)
        Spacer(Modifier.height(MaterialTheme.spacers.medium))
        CargoForm(data.value.cargoFormData)
        Spacer(Modifier.height(MaterialTheme.spacers.medium))

        if (enableCancel || enableCreate) {
            ButtonSection(
                onCancelClick = onCancelClick,
                onCreateClick = onCreateClick,
                onConfirmClick = onConfirmClick,
                enableCreate = enableCreate,
                enableCancel = enableCancel,
                enableConfirm = enableConfirm
            )
        }
    }
}

@ExperimentalMaterial3Api
@ExperimentalComposeUiApi
@Composable
@Preview
private fun AddressSection(
    originAddressFormData: MutableState<AddressFormData>,
    destinationAddressFormData: MutableState<AddressFormData>,
) {
    AddressForm(label = "Origin Address", originAddressFormData)
    Spacer(Modifier.height(MaterialTheme.spacers.medium))
    AddressForm(label = "Destination Address", destinationAddressFormData)
}

@Composable
private fun ButtonSection(
    onCancelClick: () -> Unit,
    onCreateClick: () -> Unit,
    onConfirmClick: () -> Unit,
    enableCreate: Boolean,
    enableCancel: Boolean,
    enableConfirm: Boolean
) {
    Row {
        if (enableCancel) {
            CancelButton(onCancelClick = onCancelClick)
        }

        if (enableCancel && (enableCreate || enableConfirm)) {
            Spacer(Modifier.weight(1f))
        }

        if (enableCreate) {
            CreateButton(onCreateClick = onCreateClick)
        } else if (enableConfirm) {
            CreateButton(onCreateClick = onConfirmClick)
        }
    }
}

