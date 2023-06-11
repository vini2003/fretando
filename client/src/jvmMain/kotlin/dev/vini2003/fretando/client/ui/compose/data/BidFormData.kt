package dev.vini2003.fretando.client.ui.compose.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import dev.vini2003.fretando.common.entity.Bid
import dev.vini2003.fretando.common.entity.Request

data class BidFormData(
    val request: MutableState<Request?> = mutableStateOf(null),
    val amount: MutableState<String> = mutableStateOf(""),
    val amountError: MutableState<String?> = mutableStateOf(null),
) {
    constructor(bid: Bid) : this() {
        request.value = bid.request
        amount.value = bid.amount.toString()
    }

    fun toBid(): Bid {
        return Bid(
            request.value,
            amount.value.toDouble()
        )
    }

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