@file:OptIn(ExperimentalMaterial3Api::class)

package dev.vini2003.fretando.client.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.vini2003.fretando.client.PopupComposable
import dev.vini2003.fretando.client.ui.compose.content.MainContent
import dev.vini2003.fretando.client.ui.compose.content.SidebarContent
import dev.vini2003.fretando.client.ui.compose.request.RequestCardList
import dev.vini2003.fretando.client.ui.theme.AppTheme
import dev.vini2003.fretando.client.ui.theme.paddings

val LocalPopupList = staticCompositionLocalOf<List<PopupComposable>> { emptyList() }
val LocalAddPopup = staticCompositionLocalOf<(@Composable (id: Int) -> Unit) -> Unit> { {} }
val LocalRemovePopup = staticCompositionLocalOf<(Int) -> Unit> { {} }

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Application() {
    AppTheme {
        var popupList by remember { mutableStateOf(listOf<PopupComposable>()) }

        fun addPopup(popup: @Composable (id: Int) -> Unit) {
            popupList = popupList + PopupComposable(content = popup)
        }

        fun removePopup(id: Int) {
            popupList = popupList.filterNot { it.id == id }
        }

        CompositionLocalProvider(
            LocalPopupList provides popupList,
            LocalAddPopup provides ::addPopup,
            LocalRemovePopup provides ::removePopup
        ) {
            Column(
                modifier = Modifier.background(MaterialTheme.colorScheme.background),
            ) {
                TopAppBar(
                    title = {
                        Text(
                            "Fretando",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = MaterialTheme.paddings.small)
                        )
                    },
                    colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = MaterialTheme.colorScheme.background),
                    navigationIcon = {
                        IconButton(onClick = { /* doSomething() */ }, modifier = Modifier.padding(start = 12.dp)) {
                            Icon(
                                Icons.Default.Home,
                                contentDescription = "Menu",
                                modifier = Modifier
                                    .width(48.dp)
                                    .height(48.dp)
                                    .clip(MaterialTheme.shapes.large)
                                    .background(MaterialTheme.colorScheme.secondaryContainer)
                                    .padding(MaterialTheme.paddings.small)
                            )
                        }
                    }
                )

                Row {
                    val selectedMainContent = remember { mutableStateOf<(@Composable () -> Unit)>({ RequestCardList() }) }

                    // Sidebar
                    Surface(
                        modifier = Modifier
                            .width(200.dp)
                            .fillMaxHeight()
                            .background(MaterialTheme.colorScheme.background) // change to your desired purple color
                            .padding(start = MaterialTheme.paddings.medium)
                    ) {
                        SidebarContent(selectedMainContent)
                    }

                    // The rest of your app
                    MainContent(selectedMainContent)
                }
            }

            // Display them in the middle of the screen, based on their size.
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                // Draw a background to hide what's below if there is a popup
                if (popupList.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.5f))
                            .clickable(
                                onClick = {
                                    // Remove the last popup when clicking on the background
                                    if (popupList.isNotEmpty()) {
                                        removePopup(popupList.last().id)
                                    }
                                },
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            )
                    )
                }

                // Iterate over each PopupComposable in the list
                popupList.forEach { popup ->
                    // wrap in a box and center it
                    Box(
                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.Center)
                    ) {
                        // Draw the popup
                        popup.content(popup.id)
                    }
                }
            }

        }
    }
}