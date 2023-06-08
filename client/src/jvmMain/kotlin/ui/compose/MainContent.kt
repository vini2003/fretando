package ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable

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