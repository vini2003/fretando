@file:OptIn(ExperimentalComposeUiApi::class)

package dev.vini2003.fretando.client.ui.compose

import dev.vini2003.fretando.client.PopupComposable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pages
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RequestPage
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun SidebarContent() {
    val addPopup = LocalAddPopup.current
    val removePopup = LocalRemovePopup.current

    Column {
        SidebarContentItem(0, Icons.Default.Person, "My Profile") {
            addPopup(PopupComposable { id ->
                RequestForm(onCancelClick = {
                    removePopup(id)
                })
            })
        }
        Spacer(Modifier.height(MaterialTheme.spacers.medium))
        SidebarContentItem(3, Icons.Default.Pages, "My Requests") {
            addPopup(PopupComposable(0) {
                Column {
                    Text("My Requests")
                }
            })
        }
        SidebarContentItem(4, Icons.Default.RequestPage, "My Offers") {
            addPopup(PopupComposable(0) {
                Column {
                    Text("My Offers")
                }
            })
        }
        Spacer(Modifier.height(MaterialTheme.spacers.medium))
        SidebarContentItem(1, Icons.Default.Pages, "Requests") { /* doSomething() */ }
    }
}

@Composable
fun SidebarContentItem(index: Int, icon: ImageVector, label: String, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = androidx.compose.ui.Modifier
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
                color = if (index % 2 == 0) Color.White.copy(alpha = 0.9F) else Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}