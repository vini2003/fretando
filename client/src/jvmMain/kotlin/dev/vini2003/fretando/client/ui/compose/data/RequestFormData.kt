package dev.vini2003.fretando.client.ui.compose.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import dev.vini2003.fretando.common.entity.Request

data class RequestFormData(
    val originAddressFormData: MutableState<AddressFormData> = mutableStateOf(AddressFormData()),
    val destinationAddressFormData: MutableState<AddressFormData> = mutableStateOf(AddressFormData()),
    val cargoFormData: MutableState<CargoFormData> = mutableStateOf(CargoFormData()),
) {
    constructor(request: Request) : this() {
        originAddressFormData.value = AddressFormData(request.origin)
        destinationAddressFormData.value = AddressFormData(request.destination)
        cargoFormData.value = CargoFormData(request.cargo)
    }

    fun toRequest(): Request {
        return Request(
            originAddressFormData.value.toAddress(),
            destinationAddressFormData.value.toAddress(),
            cargoFormData.value.toCargo()
        )
    }

    fun validate(): Boolean {
        return originAddressFormData.value.validate() and destinationAddressFormData.value.validate() and cargoFormData.value.validate()
    }
}