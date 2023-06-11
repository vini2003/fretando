@file:OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)

package dev.vini2003.fretando.client.ui.compose.request

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import dev.vini2003.fretando.client.repository.RemoteRequestRepository
import dev.vini2003.fretando.client.ui.compose.LocalAddPopup
import dev.vini2003.fretando.client.ui.compose.LocalRemovePopup
import dev.vini2003.fretando.client.ui.compose.bid.BidForm
import dev.vini2003.fretando.client.ui.compose.data.BidFormData
import dev.vini2003.fretando.client.ui.compose.data.RequestFormData
import dev.vini2003.fretando.client.ui.compose.misc.Paginator
import dev.vini2003.fretando.client.ui.theme.paddings
import dev.vini2003.fretando.client.ui.theme.spacers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

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

    val addPopup = LocalAddPopup.current
    val removePopup = LocalRemovePopup.current

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
                        RequestCard(
                            request = request,
                            onBidClick = {
                                addPopup { id ->
                                    val bidFormData = remember {
                                        mutableStateOf(
                                            BidFormData(request = mutableStateOf(request))
                                        )
                                    }

                                    BidForm(
                                        data = bidFormData,
                                        onCancelClick = { removePopup(id) },
                                        onConfirmClick = {
                                            if (bidFormData.value.validate()) {
                                                scope.launch {
                                                    val bid = bidFormData.value.toBid()

                                                    request.bids.plusAssign(bid)

                                                    RemoteRequestRepository.save(request)

                                                    requestPages = RemoteRequestRepository.findAll().chunked(20)

                                                    removePopup(id)
                                                }
                                            }
                                        })
                                }
                            },
                            onEditClick = {
                                addPopup { id ->
                                    val requestFormData = remember {
                                        mutableStateOf(
                                            RequestFormData(request = request)
                                        )
                                    }

                                    RequestForm(
                                        data = requestFormData,
                                        onCancelClick = {
                                            removePopup(id)
                                        },
                                        onConfirmClick = {
                                            if (requestFormData.value.validate()) {
                                                scope.launch {
                                                    val editedRequest = requestFormData.value.toRequest()
                                                    editedRequest.bids = request.bids

                                                    RemoteRequestRepository.update(request.id, editedRequest)

                                                    requestPages = RemoteRequestRepository.findAll().chunked(20)

                                                    removePopup(id)
                                                }
                                            }
                                        },
                                        enableCreate = false,
                                        enableConfirm = true
                                    )
                                }
                            },
                            onRemoveClick = {
                                scope.launch {
                                    RemoteRequestRepository.deleteById(request.id)

                                    requestPages = RemoteRequestRepository.findAll().chunked(20)
                                }
                            },
                        )
                    }
                }
            }
        }

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
                                    val request = requestFormData.value.toRequest()

                                    RemoteRequestRepository.save(request)

                                    requestPages = RemoteRequestRepository.findAll().chunked(20)

                                    removePopup(id)
                                }
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

