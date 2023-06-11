package dev.vini2003.fretando.client.ui.compose.text

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Composable
@Preview
fun AmountTextField(
    amount: MutableState<String>,
    amountError: MutableState<String?>
) {
    val scope = rememberCoroutineScope()

    CustomTextField(
        value = amount,
        isValueAllowed = { newValue ->
            if (newValue.matches(Regex("^\\d*(,\\d*)*(\\.\\d{0,2})?$"))) {
                true
            } else {
                amountError.value = "Amount must consist only of numbers, commas and at most 2 decimal places"

                scope.launch {
                    delay(2000)
                    amountError.value = null
                }

                false
            }
        },
        error = amountError,
        label = "Amount"
    )
}