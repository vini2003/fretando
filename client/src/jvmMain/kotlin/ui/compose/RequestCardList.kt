package ui.compose

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.vini2003.fretando.server.FakeDataGenerator
import ui.theme.paddings
import ui.theme.spacers
import kotlin.math.max
import kotlin.math.min

@Composable
@Preview
fun RequestCardList() {
    // Manage page index state
    var pageIndex by remember { mutableStateOf(0) }

    // Generate 48 pages with 20 requests each
    val requestPages by remember {
        mutableStateOf(
            List(96) {
                List(20) {
                    dev.vini2003.fretando.server.FakeDataGenerator.createFakeRequest()
                }
            }
        )
    }

    Column {
        Paginator(
            total = requestPages.size,
            currentPage = pageIndex,
            onSelectedPage = { selectedPage -> pageIndex = selectedPage },
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacers.medium))

        // Use LazyVerticalGrid to display the list
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 320.dp),
            contentPadding = PaddingValues(MaterialTheme.paddings.medium)
        ) {
            items(requestPages[pageIndex]) { request ->
                RequestCard(request = request)
            }
        }
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
        modifier = Modifier.padding(vertical = MaterialTheme.paddings.small, horizontal = MaterialTheme.paddings.medium).fillMaxWidth()
    ) {
        IconButton(onClick = {
            // previous code: if (currentPage - 10 >= 0) onSelectedPage(currentPage - 10)
            // make it so that it goes back to the nearest % 10 == 0, or  if (currentPage - 10 >= 0) onSelectedPage(currentPage - (currentPage % 10))
            onSelectedPage(max(min(currentPage - (currentPage % 10), currentPage - 10), 0))
        }) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Backward 10 pages", tint = MaterialTheme.colorScheme.onPrimaryContainer)
        }
        IconButton(onClick = { if (currentPage - 1 >= 0) onSelectedPage(currentPage - 1) }) {
            Icon(Icons.Filled.ChevronLeft, contentDescription = "Previous page", tint = MaterialTheme.colorScheme.onPrimaryContainer)
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val startIndexIn10PageBlocks = currentPage / 10 * 10
            val endIndexIn10PageBlocks = min(startIndexIn10PageBlocks + 10, total)

            for (i in startIndexIn10PageBlocks until endIndexIn10PageBlocks) {
                val pageNumber = i + 1

                Box(
                    modifier = Modifier
                        .defaultMinSize(minHeight = 40.dp, minWidth = 40.dp)
                        .clip(MaterialTheme.shapes.large)
                        .background(if (currentPage == i) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.secondaryContainer)
                        .clickable { onSelectedPage(i) }
                ) {
                    Text(
                        text = pageNumber.toString(),
                        textAlign = TextAlign.Center,
                        fontWeight = if (currentPage == i) FontWeight.Bold else FontWeight.Normal,
                        color = if (currentPage == i) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                Spacer(modifier = Modifier.width(MaterialTheme.spacers.small))
            }
        }

        IconButton(onClick = { if (currentPage + 1 < total) onSelectedPage(currentPage + 1) }) {
            Icon(Icons.Filled.ChevronRight, contentDescription = "Next page", tint = MaterialTheme.colorScheme.onPrimaryContainer)
        }
        IconButton(onClick = {
            onSelectedPage(min(currentPage + 10, total - 1))
        }) {
            Icon(Icons.Filled.ArrowForward, contentDescription = "Forward 10 pages", tint = MaterialTheme.colorScheme.onPrimaryContainer)
        }
    }
}


