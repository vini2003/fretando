package ui.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import dev.vini2003.fretando.common.`object`.faker.FakeDataGenerator
import ui.theme.paddings

@Composable
fun RequestCardList() {
    // Create a list of 20 dummy Request objects
    val requestList = List(48) {
        FakeDataGenerator.createFakeRequest()
    }

    // Use LazyVerticalGrid to display the list
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 320.dp),
        contentPadding = PaddingValues(MaterialTheme.paddings.medium)
    ) {
        items(requestList) { request ->
            RequestCard(request = request)
        }
    }
}
