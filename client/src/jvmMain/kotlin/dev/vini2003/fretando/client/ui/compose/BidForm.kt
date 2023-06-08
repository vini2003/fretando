package dev.vini2003.fretando.client.ui.compose

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import dev.vini2003.fretando.client.ui.theme.paddings
import dev.vini2003.fretando.client.ui.theme.spacers

@OptIn(ExperimentalComposeUiApi::class)
@ExperimentalMaterial3Api
@Composable
@Preview
fun BidForm(
    requestId: Long,
    onCancelClick: () -> Unit = {},
    onBidClick: (String) -> Unit = {},
) {
    val amount = remember { mutableStateOf("") }
    val amountError = remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .width(300.dp)
            .height(240.dp)
            .shadow(4.dp, MaterialTheme.shapes.medium)
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.surface)
            .verticalScroll(rememberScrollState())
            .padding(MaterialTheme.paddings.large)
    ) {
        Text(
            "Bid",
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75F),
            modifier = Modifier.padding(MaterialTheme.paddings.small)
        )

        Spacer(Modifier.height(MaterialTheme.spacers.medium))
        AmountForm(amount, amountError)
        Spacer(Modifier.height(MaterialTheme.spacers.medium))
        BidButtonsSection(onCancelClick) {
            if (amountError.value == null) {
                onBidClick(amount.value)
            }
        }
    }
}

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Composable
@Preview
fun AmountForm(amount: MutableState<String>, amountError: MutableState<String?>) {
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

@Composable
fun BidButtonsSection(onCancelClick: () -> Unit, onBidClick: () -> Unit) {
    Row {
        CancelButton(onCancelClick = onCancelClick)
        Spacer(Modifier.weight(1f))
        BidButton(Modifier, onBidClick = onBidClick)
    }
}

@Composable
fun BidButton(modifier: Modifier, onBidClick: () -> Unit) {
    Button(
        onClick = onBidClick,
        modifier = modifier
            .height(54.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
    ) {
        Text(
            "Bid",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        )
    }
}
