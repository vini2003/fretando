package dev.vini2003.fretando.client.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable

@ExperimentalMaterial3Api
@Composable
fun MainContent() {
    // Your main app content here...
    Column {
        RequestCardList()
        // AddressCard(FakeUtil.createFakeAddress())
        // BidCard(FakeUtil.createFakeBid())
        // CargoCard(FakeUtil.createFakeCargo())
        // RequestCard(FakeUtil.createFakeRequest())

    }
}