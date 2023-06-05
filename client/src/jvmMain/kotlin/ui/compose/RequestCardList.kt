package ui.compose

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import dev.vini2003.fretando.common.`object`.Request
import dev.vini2003.fretando.common.`object`.faker.FakeDataGenerator

@Composable
fun RequestCardList() {
    // Create a list of 20 dummy Request objects
    val requestList = List(20) {
        FakeDataGenerator.createFakeRequest()
    }

    // Use LazyColumn to display the list
    LazyColumn {
        items(requestList) { request ->
            RequestCard(request = request)
        }
    }
}
