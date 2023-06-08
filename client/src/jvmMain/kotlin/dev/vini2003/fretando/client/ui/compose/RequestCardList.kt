@file:OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)

package dev.vini2003.fretando.client.ui.compose

import dev.vini2003.fretando.client.PopupComposable
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import dev.vini2003.fretando.common.util.FakeUtil
import dev.vini2003.fretando.client.ui.theme.paddings
import dev.vini2003.fretando.client.ui.theme.spacers
import kotlin.math.max
import kotlin.math.min

@ExperimentalMaterial3Api
@Composable
@Preview
fun RequestCardList() {
    // Manage page index state
    var pageIndex by remember { mutableStateOf(0) }

    // Generate 48 pages with 20 requests each
    val requestPages by remember {
        mutableStateOf(
            List(96) {
                List(48) {
                    FakeUtil.createFakeRequest()
                }
            }
        )
    }

    Box {
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

        val addPopup = LocalAddPopup.current
        val removePopup = LocalRemovePopup.current

        IconButton(onClick = {
            addPopup(PopupComposable { id ->
                RequestForm(onCancelClick = {
                    removePopup(id)
                }, onCreateClick = {
                    removePopup(id)
                })
            })
        }, modifier = Modifier
            .align(Alignment.BottomEnd)
            .size(128.dp)
            .padding(MaterialTheme.paddings.large)
            .shadow(4.dp, MaterialTheme.shapes.large)
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colorScheme.primaryContainer)
        ) {
            Icon(Icons.Rounded.Add, contentDescription = "Create new request", tint = MaterialTheme.colorScheme.onPrimaryContainer, modifier = Modifier.size(48.dp))
        }
    }
}

@Composable
fun Paginator(
    total: Int,
    currentPage: Int,
    onSelectedPage: (Int) -> Unit,
) {
    val ButtonWidth = 40.dp
    val ArrowWidth = 48.dp // estimate, depends on your layout
    val SpaceWidth = 8.dp // estimate, depends on your layout
    val MinWidth = ButtonWidth + 2 * SpaceWidth // minimum width to show one button

    BoxWithConstraints {
        val maxWidth = with(LocalDensity.current) { constraints.maxWidth.toDp() }

        val maxButtons = min(10, ((maxWidth - 2 * ArrowWidth - 4 * SpaceWidth) / (ButtonWidth + SpaceWidth)).toInt())
        val showArrows10 = maxWidth >= 2 * ArrowWidth + 2 * SpaceWidth + 10 * (ButtonWidth + SpaceWidth)
        val showArrows1 = maxWidth >= 2 * ArrowWidth + 2 * SpaceWidth + 2 * (ButtonWidth + SpaceWidth)

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = MaterialTheme.paddings.small, horizontal = MaterialTheme.paddings.medium).fillMaxWidth()
        ) {
            if (showArrows10) {
                IconButton(onClick = {
                    onSelectedPage(max(min(currentPage - (currentPage % 10), currentPage - 10), 0))
                }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Backward 10 pages", tint = MaterialTheme.colorScheme.onPrimaryContainer)
                }
            }
            if (showArrows1) {
                IconButton(onClick = { if (currentPage - 1 >= 0) onSelectedPage(currentPage - 1) }) {
                    Icon(Icons.Filled.ChevronLeft, contentDescription = "Previous page", tint = MaterialTheme.colorScheme.onPrimaryContainer)
                }
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

                    Spacer(modifier = Modifier.width(MaterialTheme.spacers.small))
                }
            }

            if (showArrows1) {
                IconButton(onClick = { if (currentPage + 1 < total) onSelectedPage(currentPage + 1) }) {
                    Icon(Icons.Filled.ChevronRight, contentDescription = "Next page", tint = MaterialTheme.colorScheme.onPrimaryContainer)
                }
            }
            if (showArrows10) {
                IconButton(onClick = {
                    onSelectedPage(min(currentPage + 10, total - 1))
                }) {
                    Icon(Icons.Filled.ArrowForward, contentDescription = "Forward 10 pages", tint = MaterialTheme.colorScheme.onPrimaryContainer)
                }
            }
        }
    }
}
