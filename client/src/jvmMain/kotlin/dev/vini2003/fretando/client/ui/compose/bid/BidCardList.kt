@file:OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)

package dev.vini2003.fretando.client.ui.compose.bid

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.vini2003.fretando.client.repository.RemoteBidRepository
import dev.vini2003.fretando.client.repository.RemoteRequestRepository
import dev.vini2003.fretando.client.ui.compose.application.LocalAddPopup
import dev.vini2003.fretando.client.ui.compose.application.LocalRemovePopup
import dev.vini2003.fretando.client.ui.compose.data.BidFormData
import dev.vini2003.fretando.client.ui.compose.error.ErrorCard
import dev.vini2003.fretando.client.ui.compose.misc.Paginator
import dev.vini2003.fretando.client.ui.compose.request.RequestCard
import dev.vini2003.fretando.client.ui.theme.paddings
import dev.vini2003.fretando.client.ui.theme.spacers
import dev.vini2003.fretando.common.entity.Bid
import kotlinx.coroutines.launch

@ExperimentalComposeApi
@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Composable
@Preview
fun BidCardList() {
    val addPopup = LocalAddPopup.current
    val removePopup = LocalRemovePopup.current

    val scope = rememberCoroutineScope()

    var pageIndex by remember { mutableStateOf(0) }

    var bidPages: List<List<Bid>> by remember { mutableStateOf(listOf()) }

    var firstTime by remember { mutableStateOf(true) }

    var isLoading by remember { mutableStateOf(false) }

    if (firstTime) {
        firstTime = false

        scope.launch {
            isLoading = true

            try {
                bidPages = RemoteBidRepository.findAll().chunked(20)

                isLoading = false
            } catch (exception: Exception) {
                isLoading = false

                addPopup { id ->
                    ErrorCard(
                        errorMessage = exception.message ?: "Unknown error",
                        onDismissClick = { removePopup(id) }
                    )
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (isLoading) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
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
                                try {
                                    isLoading = true

                                    bidPages = RemoteBidRepository.findAll().chunked(20)

                                    isLoading = false
                                } catch (exception: Exception) {
                                    isLoading = false

                                    addPopup { id ->
                                        ErrorCard(
                                            errorMessage = exception.message ?: "Unknown error",
                                            onDismissClick = { removePopup(id) }
                                        )
                                    }
                                }
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
                                                        try {
                                                            isLoading = true

                                                            val newBid = bidFormData.value.toBid()

                                                            RemoteBidRepository.update(bid.id, newBid)

                                                            removePopup(popup)

                                                            bidPages = RemoteBidRepository.findAll().chunked(20)

                                                            isLoading = false
                                                        } catch (exception: Exception) {
                                                            isLoading = false

                                                            addPopup { id ->
                                                                ErrorCard(
                                                                    errorMessage = exception.message ?: "Unknown error",
                                                                    onDismissClick = { removePopup(id) }
                                                                )
                                                            }
                                                        }
                                                    }
                                                },
                                            )
                                        }
                                    },
                                    onDeleteClick = {
                                        scope.launch {
                                            try {
                                                isLoading = true

                                                val request = bid.requestId?.let {
                                                    RemoteRequestRepository.findById(it)
                                                } ?: return@launch

                                                request.bids.minusAssign(bid)

                                                RemoteRequestRepository.save(request)

                                                RemoteBidRepository.deleteById(bid.id)

                                                bidPages = RemoteBidRepository.findAll().chunked(20)

                                                isLoading = false
                                            } catch (exception: Exception) {
                                                isLoading = false

                                                addPopup { id ->
                                                    ErrorCard(
                                                        errorMessage = exception.message ?: "Unknown error",
                                                        onDismissClick = { removePopup(id) }
                                                    )
                                                }
                                            }
                                        }
                                    },
                                    onRequestClick = {
                                        scope.launch {
                                            try {
                                                isLoading = true

                                                val request = bid.requestId?.let {
                                                    RemoteRequestRepository.findById(it)
                                                } ?: return@launch

                                                addPopup {
                                                    RequestCard(
                                                        request = request,
                                                        enableBid = false,
                                                        enableEdit = false,
                                                        enableRemove = false
                                                    )
                                                }

                                                isLoading = false
                                            } catch (exception: Exception) {
                                                isLoading = false

                                                addPopup { id ->
                                                    ErrorCard(
                                                        errorMessage = exception.message ?: "Unknown error",
                                                        onDismissClick = { removePopup(id) }
                                                    )
                                                }
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
    }
}