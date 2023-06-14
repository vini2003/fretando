@file:OptIn(ExperimentalMaterial3Api::class)

package dev.vini2003.fretando.client.ui.compose.application

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.vini2003.fretando.client.ui.compose.config.ConfigForm
import dev.vini2003.fretando.client.ui.compose.content.MainContent
import dev.vini2003.fretando.client.ui.compose.content.SidebarContent
import dev.vini2003.fretando.client.ui.compose.popup.PopupComposable
import dev.vini2003.fretando.client.ui.compose.welcome.Welcome
import dev.vini2003.fretando.client.ui.theme.AppTheme
import dev.vini2003.fretando.client.ui.theme.paddings

val LocalPopupList = staticCompositionLocalOf<List<PopupComposable>> { emptyList() }
val LocalAddPopup = staticCompositionLocalOf<(@Composable (id: Int) -> Unit) -> Unit> { {} }
val LocalRemovePopup = staticCompositionLocalOf<(Int) -> Unit> { {} }

@ExperimentalComposeApi
@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
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

        var firstTime by remember { mutableStateOf(true) }

        if (firstTime) {
            firstTime = false

            addPopup {
                Welcome()
            }
        }

        CompositionLocalProvider(
            LocalPopupList provides popupList,
            LocalAddPopup provides ::addPopup,
            LocalRemovePopup provides ::removePopup
        ) {
            Column(
                modifier = Modifier.background(MaterialTheme.colorScheme.background)
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
                        IconButton(onClick = {
                            addPopup {
                                ConfigForm()
                            }
                        }, modifier = Modifier.padding(start = 12.dp)) {
                            Icon(
                                Icons.Default.Settings,
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
                    val selectedMainContent =
                        remember { mutableStateOf<(@Composable () -> Unit)>({ }) }

                    Surface(
                        modifier = Modifier
                            .width(200.dp)
                            .fillMaxHeight()
                            .background(MaterialTheme.colorScheme.background)
                            .padding(start = MaterialTheme.paddings.medium)
                    ) {
                        SidebarContent(selectedMainContent)
                    }

                    MainContent(selectedMainContent)
                }
            }

            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                if (popupList.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.5f))
                            .clickable(
                                onClick = {
                                    if (popupList.isNotEmpty()) {
                                        removePopup(popupList.last().id)
                                    }
                                },
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            )
                    )
                }

                popupList.forEach { popup ->
                    Box(
                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.Center)
                    ) {
                        popup.content(popup.id)
                    }
                }
            }

        }
    }
}