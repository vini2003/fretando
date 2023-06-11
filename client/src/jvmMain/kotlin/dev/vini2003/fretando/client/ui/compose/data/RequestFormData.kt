package dev.vini2003.fretando.client.ui.compose.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class RequestFormData(
    val originAddressFormData: MutableState<AddressFormData> = mutableStateOf(AddressFormData()),
    val destinationAddressFormData: MutableState<AddressFormData> = mutableStateOf(AddressFormData()),
    val cargoFormData: MutableState<CargoFormData> = mutableStateOf(CargoFormData()),
) {
    fun validate(): Boolean {
        return originAddressFormData.value.validate() && destinationAddressFormData.value.validate() && cargoFormData.value.validate()
    }
}