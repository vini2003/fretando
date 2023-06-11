@file:OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)

package dev.vini2003.fretando.client.ui.compose.bid

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.vini2003.fretando.client.repository.RemoteBidRepository
import dev.vini2003.fretando.client.repository.RemoteRequestRepository
import dev.vini2003.fretando.client.ui.compose.LocalAddPopup
import dev.vini2003.fretando.client.ui.compose.LocalRemovePopup
import dev.vini2003.fretando.client.ui.compose.data.BidFormData
import dev.vini2003.fretando.client.ui.compose.misc.Paginator
import dev.vini2003.fretando.client.ui.compose.request.RequestCard
import dev.vini2003.fretando.client.ui.theme.paddings
import dev.vini2003.fretando.client.ui.theme.spacers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Composable
@Preview
fun BidCardList() {
    val scope = rememberCoroutineScope()

    // Manage page index state
    var pageIndex by remember { mutableStateOf(0) }

    // Generate 48 pages with 20 requests each
    var bidPages by remember {
        mutableStateOf(
            runBlocking {
                RemoteBidRepository.findAll().chunked(20)
            }
        )
    }

    val addPopup = LocalAddPopup.current
    val removePopup = LocalRemovePopup.current

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {
            Paginator(
                total = bidPages.size,
                currentPage = pageIndex,
                onSelectedPage = { selectedPage -> pageIndex = selectedPage },
                onRefresh = {
                    scope.launch {
                        bidPages = RemoteBidRepository.findAll().chunked(20)
                    }
                }
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacers.medium))

            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 320.dp),
                contentPadding = PaddingValues(MaterialTheme.paddings.medium)
            ) {
                if (bidPages.isNotEmpty() && bidPages.size > pageIndex && bidPages[pageIndex].isNotEmpty()) {
                    items(bidPages[pageIndex]) { bid ->
                        BidCard(
                            bid = bid,
                            onEditClick = {
                                addPopup { popup ->
                                    val bidFormData = remember {
                                        mutableStateOf(
                                            BidFormData(bid = bid)
                                        )
                                    }

                                    BidForm(
                                        data = bidFormData,
                                        onCancelClick = { removePopup(popup) },
                                        onConfirmClick = {
                                            scope.launch {
                                                val newBid = bidFormData.value.toBid()

                                                RemoteBidRepository.update(bid.id, newBid)

                                                removePopup(popup)

                                                bidPages = RemoteBidRepository.findAll().chunked(20)
                                            }
                                        },
                                    )
                                }
                            },
                            onDeleteClick = {
                                scope.launch {
                                    val request = bid.request // TODO: Check if working! Used to be "requestId".

                                    request.bids.minusAssign(bid)

                                    RemoteRequestRepository.save(request)

                                    RemoteBidRepository.deleteById(bid.id)

                                    bidPages = RemoteBidRepository.findAll().chunked(20)
                                }
                            },
                            onRequestClick = {
                                scope.launch {
                                    val request = bid.request // TODO: Check if working! Used to be "requestId".

                                    addPopup {
                                        RequestCard(
                                            request = request,
                                            enableBid = false,
                                            enableEdit = false,
                                            enableRemove = false
                                        )
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}