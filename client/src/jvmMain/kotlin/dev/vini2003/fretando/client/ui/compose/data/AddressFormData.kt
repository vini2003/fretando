package dev.vini2003.fretando.client.ui.compose.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class AddressFormData(
    val street: MutableState<String> = mutableStateOf(""),
    val number: MutableState<String> = mutableStateOf(""),
    val city: MutableState<String> = mutableStateOf(""),
    val state: MutableState<String> = mutableStateOf(""),
    val postalCode: MutableState<String> = mutableStateOf(""),
    val country: MutableState<String> = mutableStateOf(""),
    val notes: MutableState<String> = mutableStateOf(""),

    val streetError: MutableState<String?> = mutableStateOf(null),
    val numberError: MutableState<String?> = mutableStateOf(null),
    val cityError: MutableState<String?> = mutableStateOf(null),
    val stateError: MutableState<String?> = mutableStateOf(null),
    val postalCodeError: MutableState<String?> = mutableStateOf(null),
    val countryError: MutableState<String?> = mutableStateOf(null),
) {
    fun validate(): Boolean {
        var valid = true

        if (street.value.isEmpty()) {
            streetError.value = "Street is required"
            valid = false
        } else {
            streetError.value = null
        }

        if (number.value.isEmpty()) {
            numberError.value = "Number is required"
            valid = false
        } else {
            numberError.value = null
        }

        if (city.value.isEmpty()) {
            cityError.value = "City is required"
            valid = false
        } else {
            cityError.value = null
        }

        if (state.value.isEmpty()) {
            stateError.value = "State is required"
            valid = false
        } else {
            stateError.value = null
        }

        if (postalCode.value.isEmpty()) {
            postalCodeError.value = "Postal code is required"
            valid = false
        } else {
            postalCodeError.value = null
        }

        if (country.value.isEmpty()) {
            countryError.value = "Country is required"
            valid = false
        } else {
            countryError.value = null
        }

        return valid
    }
}