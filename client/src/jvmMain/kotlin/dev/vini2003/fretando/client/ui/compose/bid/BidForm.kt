package dev.vini2003.fretando.client.ui.compose.bid

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.vini2003.fretando.client.ui.compose.button.CancelButton
import dev.vini2003.fretando.client.ui.compose.button.ConfirmButton
import dev.vini2003.fretando.client.ui.compose.data.BidFormData
import dev.vini2003.fretando.client.ui.compose.text.AmountTextField
import dev.vini2003.fretando.client.ui.theme.paddings
import dev.vini2003.fretando.client.ui.theme.spacers

@OptIn(ExperimentalComposeUiApi::class)
@ExperimentalMaterial3Api
@Composable
@Preview
fun BidForm(
    onCancelClick: () -> Unit = {},
    onConfirmClick: (String) -> Unit = {},
    enableCancel: Boolean = true,
    enableConfirm: Boolean = true,
    data: MutableState<BidFormData> = mutableStateOf(BidFormData())
) {
    val amount = remember { data.value.amount }
    val amountError = remember { data.value.amountError }

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
        AmountTextField(amount, amountError)
        Spacer(Modifier.height(MaterialTheme.spacers.medium))

        if (enableConfirm || enableCancel) {
            ButtonSection(
                onCancelClick = onCancelClick,
                onConfirmClick = {
                    if (data.value.validate()) {
                        onConfirmClick(amount.value)
                    }
                },
                enableBid = enableConfirm,
                enableCancel = enableCancel
            )
        }
    }
}

@Composable
private fun ButtonSection(
    onCancelClick: () -> Unit,
    onConfirmClick: () -> Unit,
    enableBid: Boolean,
    enableCancel: Boolean
) {
    Row {
        if (enableCancel) {
            CancelButton(onCancelClick = onCancelClick)
        }

        if (enableCancel && enableBid) {
            Spacer(Modifier.weight(1f))
        }

        if (enableBid) {
            ConfirmButton(onConfirmClick = onConfirmClick)
        }
    }
}
