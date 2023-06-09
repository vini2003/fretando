@file:OptIn(ExperimentalComposeUiApi::class)

package dev.vini2003.fretando.client.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pages
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RequestPage
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.vini2003.fretando.client.ui.theme.paddings
import dev.vini2003.fretando.client.ui.theme.spacers

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
        SidebarContentItem(0, Icons.Default.Person, "My Profile") {
            // TODO!
        }
        Spacer(Modifier.height(MaterialTheme.spacers.medium))
        SidebarContentItem(3, Icons.Default.Pages, "My Requests") {
            mainContentComposable.value = { RequestCardList() }
        }
        SidebarContentItem(4, Icons.Default.RequestPage, "My Bids") {
            mainContentComposable.value = { BidCardList() }
        }
        Spacer(Modifier.height(MaterialTheme.spacers.medium))
        SidebarContentItem(1, Icons.Default.Pages, "Requests") {
            // TODO!
        }
    }
}

@Composable
fun SidebarContentItem(index: Int, icon: ImageVector, label: String, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(64.dp)
            .fillMaxWidth()
            .padding(MaterialTheme.paddings.verySmall)
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .clickable(onClick = onClick)
            .padding(start = MaterialTheme.paddings.medium)
    ) {
        Icon(
            icon,
            contentDescription = "Arrow icon",
            tint = if (index % 2 == 0) Color.White.copy(alpha = 0.9F) else Color.White
        )
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 8.dp).wrapContentSize(Alignment.CenterStart),
            style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}