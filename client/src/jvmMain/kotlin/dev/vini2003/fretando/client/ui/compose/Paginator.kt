package dev.vini2003.fretando.client.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import dev.vini2003.fretando.client.ui.theme.paddings
import dev.vini2003.fretando.client.ui.theme.spacers
import kotlin.math.max
import kotlin.math.min

@Composable
fun Paginator(
    total: Int,
    currentPage: Int,
    onSelectedPage: (Int) -> Unit,
    onRefresh: () -> Unit, // listener for the refresh button
) {
    val ButtonWidth = 40.dp
    val ArrowWidth = 48.dp // estimate, depends on your layout
    val RefreshWidth = 48.dp // estimate, depends on your layout
    val SpaceWidth = 8.dp // estimate, depends on your layout

    BoxWithConstraints {
        val maxWidth = with(LocalDensity.current) { constraints.maxWidth.toDp() }

        // Added RefreshWidth + SpaceWidth to the calculation to adjust for the refresh button
        // TODO: Investigate div by zero!
        val maxButtons = min(
            10,
            ((maxWidth - 2 * ArrowWidth - 2 * RefreshWidth - 5 * SpaceWidth) / (ButtonWidth + SpaceWidth)).toInt()
        )
        val showArrows10 = maxWidth >= 2 * ArrowWidth + 2 * SpaceWidth + 10 * (ButtonWidth + SpaceWidth)
        val showArrows1 = maxWidth >= 2 * ArrowWidth + 2 * SpaceWidth + 2 * (ButtonWidth + SpaceWidth)

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(
                vertical = MaterialTheme.paddings.small,
                horizontal = MaterialTheme.paddings.medium
            ).fillMaxWidth()
        ) {
            if (showArrows10) {
                IconButton(onClick = {
                    onSelectedPage(max(min(currentPage - (currentPage % 10), currentPage - 10), 0))
                }) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "Backward 10 pages",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
            if (showArrows1) {
                IconButton(onClick = { if (currentPage - 1 >= 0) onSelectedPage(currentPage - 1) }) {
                    Icon(
                        Icons.Filled.ChevronLeft,
                        contentDescription = "Previous page",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }

            // Refresh button
            IconButton(onClick = onRefresh) {
                Icon(
                    Icons.Filled.Refresh,
                    contentDescription = "Refresh",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                val startIndexIn10PageBlocks = currentPage / maxButtons * maxButtons
                val endIndexIn10PageBlocks = min(startIndexIn10PageBlocks + maxButtons, total)

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

                    Spacer(modifier = androidx.compose.ui.Modifier.width(MaterialTheme.spacers.small))
                }
            }

            if (showArrows1) {
                IconButton(onClick = { if (currentPage + 1 < total) onSelectedPage(currentPage + 1) }) {
                    Icon(
                        Icons.Filled.ChevronRight,
                        contentDescription = "Next page",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
            if (showArrows10) {
                IconButton(onClick = {
                    onSelectedPage(min(currentPage + 10, total - 1))
                }) {
                    Icon(
                        Icons.Filled.ArrowForward,
                        contentDescription = "Forward 10 pages",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        }
    }
}