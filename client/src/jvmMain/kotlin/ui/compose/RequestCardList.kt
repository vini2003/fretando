package ui.compose

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.vini2003.fretando.common.`object`.faker.FakeDataGenerator
import ui.theme.paddings
import ui.theme.spacers

@Composable
@Preview
fun RequestCardList() {
    // Manage page index state
    var pageIndex by remember { mutableStateOf(0) }

    // Generate 48 pages with 20 requests each
    val requestPages = List(48) {
        List(20) {
            FakeDataGenerator.createFakeRequest()
        }
    }

    // Use LazyVerticalGrid to display the list
    Box {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 320.dp),
            contentPadding = PaddingValues(MaterialTheme.paddings.medium)
        ) {
            items(requestPages[pageIndex]) { request ->
                RequestCard(request = request)
            }
        }

        Paginator(
            total = requestPages.size,
            currentPage = pageIndex,
            onSelectedPage = { selectedPage -> pageIndex = selectedPage },
        )
    }
}

@Composable
fun Paginator(
    total: Int,
    currentPage: Int,
    onSelectedPage: (Int) -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(MaterialTheme.paddings.medium).offset(y = (-60).dp).fillMaxWidth()
    ) {
        for (i in 0 until total) {
            val pageNumber = i + 1

            Text(
                text = pageNumber.toString(),
                textAlign = TextAlign.Center,
                fontWeight = if (currentPage == i) FontWeight.Bold else FontWeight.Normal,
                color = if (currentPage == i) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.onTertiaryContainer,
                modifier = Modifier
                    .defaultMinSize(minHeight = 40.dp, minWidth = 40.dp)
                    .clip(MaterialTheme.shapes.large)
                    .background(if (currentPage == i) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.tertiaryContainer)
                    .padding(MaterialTheme.paddings.small)
                    .clickable { onSelectedPage(i) }
            )
            Spacer(modifier = Modifier.width(MaterialTheme.spacers.small))
        }
    }
}