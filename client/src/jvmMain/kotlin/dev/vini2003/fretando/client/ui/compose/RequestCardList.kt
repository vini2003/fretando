@file:OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)

package dev.vini2003.fretando.client.ui.compose

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import dev.vini2003.fretando.client.repository.RemoteRequestRepository
import dev.vini2003.fretando.client.ui.theme.paddings
import dev.vini2003.fretando.client.ui.theme.spacers
import dev.vini2003.fretando.common.entity.Address
import dev.vini2003.fretando.common.entity.Cargo
import dev.vini2003.fretando.common.entity.Request
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.math.max
import kotlin.math.min

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Composable
@Preview
fun RequestCardList() {
    val scope = rememberCoroutineScope()

    // Manage page index state
    var pageIndex by remember { mutableStateOf(0) }

    // Generate 48 pages with 20 requests each
    var requestPages by remember {
        mutableStateOf(
            runBlocking {
                RemoteRequestRepository.findAll().chunked(20)
            }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {
            Paginator(
                total = requestPages.size,
                currentPage = pageIndex,
                onSelectedPage = { selectedPage -> pageIndex = selectedPage },
                onRefresh = {
                    scope.launch {
                        requestPages = RemoteRequestRepository.findAll().chunked(20)
                    }
                }
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacers.medium))

            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 320.dp),
                contentPadding = PaddingValues(MaterialTheme.paddings.medium)
            ) {
                if (requestPages.isNotEmpty() && requestPages.size > pageIndex && requestPages[pageIndex].isNotEmpty()) {
                    items(requestPages[pageIndex]) { request ->
                        RequestCard(request = request)
                    }
                }
            }
        }

        val addPopup = LocalAddPopup.current
        val removePopup = LocalRemovePopup.current

        IconButton(
            onClick = {
                addPopup { id ->
                    val requestFormData = remember {
                        mutableStateOf(
                            RequestFormData()
                        )
                    }

                    RequestForm(
                        data = requestFormData,
                        onCancelClick = {
                            removePopup(id)
                        },
                        onCreateClick = {
                            if (requestFormData.value.validate()) {
                                scope.launch {
                                    val request = Request(
                                        Address(
                                            requestFormData.value.originAddressFormData.value.street.value,
                                            requestFormData.value.originAddressFormData.value.number.value,
                                            requestFormData.value.originAddressFormData.value.city.value,
                                            requestFormData.value.originAddressFormData.value.state.value,
                                            requestFormData.value.originAddressFormData.value.postalCode.value,
                                            requestFormData.value.originAddressFormData.value.country.value,
                                            requestFormData.value.originAddressFormData.value.notes.value,
                                        ),
                                        Address(
                                            requestFormData.value.destinationAddressFormData.value.street.value,
                                            requestFormData.value.destinationAddressFormData.value.number.value,
                                            requestFormData.value.destinationAddressFormData.value.city.value,
                                            requestFormData.value.destinationAddressFormData.value.state.value,
                                            requestFormData.value.destinationAddressFormData.value.postalCode.value,
                                            requestFormData.value.destinationAddressFormData.value.country.value,
                                            requestFormData.value.destinationAddressFormData.value.notes.value,
                                        ),
                                        Cargo(
                                            requestFormData.value.cargoFormData.value.length.value.toDouble(),
                                            requestFormData.value.cargoFormData.value.lengthUnit.value.let {
                                                Cargo.DimensionUnit.values().first { lengthUnit -> lengthUnit.asString() == it }
                                            },
                                            requestFormData.value.cargoFormData.value.width.value.toDouble(),
                                            requestFormData.value.cargoFormData.value.widthUnit.value.let {
                                                Cargo.DimensionUnit.values().first { widthUnit -> widthUnit.asString() == it }
                                            },
                                            requestFormData.value.cargoFormData.value.height.value.toDouble(),
                                            requestFormData.value.cargoFormData.value.heightUnit.value.let {
                                                Cargo.DimensionUnit.values().first { heightUnit -> heightUnit.asString() == it }
                                            },
                                            requestFormData.value.cargoFormData.value.weight.value.toDouble(),
                                            requestFormData.value.cargoFormData.value.weightUnit.value.let {
                                                Cargo.WeightUnit.values().first { weightUnit -> weightUnit.asString() == it }
                                            },
                                            requestFormData.value.cargoFormData.value.description.value
                                        )
                                    )

                                    RemoteRequestRepository.save(request)

                                    requestPages = RemoteRequestRepository.findAll().chunked(20)
                                }

                                removePopup(id)
                            }
                        }
                    )
                }
            }, modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(128.dp)
                .padding(MaterialTheme.paddings.large)
                .shadow(4.dp, MaterialTheme.shapes.large)
                .clip(MaterialTheme.shapes.large)
                .background(MaterialTheme.colorScheme.primaryContainer)
        ) {
            Icon(
                Icons.Rounded.Add,
                contentDescription = "Create new request",
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.size(48.dp)
            )
        }
    }
}

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

                    Spacer(modifier = Modifier.width(MaterialTheme.spacers.small))
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
