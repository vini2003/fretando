package dev.vini2003.fretando.client.ui.compose.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class BidFormData(
    val amount: MutableState<String> = mutableStateOf(""),
    val amountError: MutableState<String?> = mutableStateOf(null),
) {
    fun validate(): Boolean {
        var valid = true

        if (amount.value.isEmpty()) {
            amountError.value = "Amount is required"
            valid = false
        } else {
            amountError.value = null
        }

        if (!amount.value.matches(Regex("^\\d*(,\\d*)*(\\.\\d{0,2})?$"))) {
            amountError.value = "Amount is invalid"
            valid = false
        } else {
            amountError.value = null
        }

        return valid
    }
}