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
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import dev.vini2003.fretando.client.repository.RemoteRequestRepository
import dev.vini2003.fretando.client.ui.compose.application.LocalAddPopup
import dev.vini2003.fretando.client.ui.compose.application.LocalRemovePopup
import dev.vini2003.fretando.client.ui.compose.bid.BidForm
import dev.vini2003.fretando.client.ui.compose.data.BidFormData
import dev.vini2003.fretando.client.ui.compose.data.RequestFormData
import dev.vini2003.fretando.client.ui.compose.error.ErrorCard
import dev.vini2003.fretando.client.ui.compose.misc.Paginator
import dev.vini2003.fretando.client.ui.theme.paddings
import dev.vini2003.fretando.client.ui.theme.spacers
import dev.vini2003.fretando.common.entity.Request
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@ExperimentalComposeApi
@ExperimentalMaterial3Api
@Composable
@Preview
fun RequestCardList() {
    val addPopup = LocalAddPopup.current
    val removePopup = LocalRemovePopup.current

    val scope = rememberCoroutineScope()

    var pageIndex by remember { mutableStateOf(0) }

    var requestPages: List<List<Request>> by remember { mutableStateOf(listOf()) }

    var firstTime by remember { mutableStateOf(true) }

    var isLoading by remember { mutableStateOf(false) }

    if (firstTime) {
        firstTime = false

        scope.launch {
            isLoading = true

            try {
                requestPages = RemoteRequestRepository.findAll().chunked(20)

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
                        total = requestPages.size,
                        currentPage = pageIndex,
                        onSelectedPage = { selectedPage -> pageIndex = selectedPage },
                        onRefresh = {
                            scope.launch {
                                try {
                                    requestPages = RemoteRequestRepository.findAll().chunked(20)
                                    print("???")
                                } catch (exception: Exception) {
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
                        if (requestPages.isNotEmpty() && requestPages.size > pageIndex && requestPages[pageIndex].isNotEmpty()) {
                            items(requestPages[pageIndex]) { request ->
                                RequestCard(
                                    request = request,
                                    onBidClick = {
                                        addPopup { id ->
                                            val bidFormData = remember {
                                                mutableStateOf(
                                                    BidFormData(requestId = mutableStateOf(request.id))
                                                )
                                            }

                                            BidForm(
                                                data = bidFormData,
                                                onCancelClick = { removePopup(id) },
                                                onConfirmClick = {
                                                    if (bidFormData.value.validate()) {
                                                        scope.launch {
                                                            try {
                                                                isLoading = true
                                                                val bid = bidFormData.value.toBid()

                                                                request.bids.plusAssign(bid)

                                                                RemoteRequestRepository.save(request)

                                                                requestPages =
                                                                    RemoteRequestRepository.findAll().chunked(20)

                                                                removePopup(id)

                                                                isLoading = false
                                                            } catch (exception: Exception) {
                                                                isLoading = false

                                                                addPopup { id ->
                                                                    ErrorCard(
                                                                        errorMessage = exception.message
                                                                            ?: "Unknown error",
                                                                        onDismissClick = { removePopup(id) }
                                                                    )
                                                                }
                                                            }
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
                                                            try {
                                                                isLoading = true

                                                                val editedRequest = requestFormData.value.toRequest()
                                                                editedRequest.bids = request.bids

                                                                RemoteRequestRepository.update(
                                                                    request.id,
                                                                    editedRequest
                                                                )

                                                                requestPages =
                                                                    RemoteRequestRepository.findAll().chunked(20)

                                                                removePopup(id)

                                                                isLoading = false
                                                            } catch (exception: Exception) {
                                                                isLoading = false

                                                                addPopup { id ->
                                                                    ErrorCard(
                                                                        errorMessage = exception.message
                                                                            ?: "Unknown error",
                                                                        onDismissClick = { removePopup(id) }
                                                                    )
                                                                }
                                                            }
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
                                            try {
                                                isLoading = true

                                                RemoteRequestRepository.deleteById(request.id)

                                                requestPages = RemoteRequestRepository.findAll().chunked(20)

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
                                            try {
                                                isLoading = true

                                                val request = requestFormData.value.toRequest()

                                                RemoteRequestRepository.save(request)

                                                requestPages = RemoteRequestRepository.findAll().chunked(20)

                                                removePopup(id)

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
                                }
                            )
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(96.dp)
                        .padding(MaterialTheme.paddings.large)
                        .shadow(4.dp, MaterialTheme.shapes.extraLarge)
                        .clip(MaterialTheme.shapes.extraLarge)
                        .background(MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Icon(
                        Icons.Rounded.Add,
                        contentDescription = "Create new request",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                }
            }
        }
    }
}

