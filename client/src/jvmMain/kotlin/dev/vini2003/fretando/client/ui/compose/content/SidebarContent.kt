@file:OptIn(ExperimentalComposeUiApi::class)

package dev.vini2003.fretando.client.ui.compose.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pages
import androidx.compose.material.icons.filled.RequestPage
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import dev.vini2003.fretando.client.ui.compose.bid.BidCardList
import dev.vini2003.fretando.client.ui.compose.request.RequestCardList
import dev.vini2003.fretando.client.ui.theme.paddings

@ExperimentalMaterial3Api
@ExperimentalComposeUiApi
@Composable
fun SidebarContent(mainContentComposable: MutableState<@Composable () -> Unit>) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.background)
            .padding(top = MaterialTheme.paddings.medium, end = MaterialTheme.paddings.medium)
    ) {
        SidebarContentItem(3, Icons.Default.Pages, "Requests") {
            mainContentComposable.value = { RequestCardList() }
        }
        SidebarContentItem(4, Icons.Default.RequestPage, "Bids") {
            mainContentComposable.value = { BidCardList() }
        }
    }
}
