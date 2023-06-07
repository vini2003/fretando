@file:OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Pages
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RequestPage
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import ui.compose.RequestCardList
import ui.theme.AppTheme
import ui.theme.paddings
import ui.theme.spacers

@Composable
@Preview
fun MyApp() {
    AppTheme {
        Column (
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
        ) {
            TopAppBar(
                title = { Text("Fretando", fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = MaterialTheme.paddings.small)) },
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
                // Sidebar
                Surface(
                    modifier = Modifier
                        .width(200.dp)
                        .fillMaxHeight()
                        .background(MaterialTheme.colorScheme.surface) // change to your desired purple color
                        .padding(start = MaterialTheme.paddings.medium)
                ) {
                    SidebarContent()
                }

                // The rest of your app
                MainContent()
            }
        }
    }
}

@Composable
fun SidebarContent() {
    // Define what the sidebar looks like
    Column {
        SidebarContentItem(0, Icons.Default.Person, "My Profile") { /* doSomething() */ }
        Spacer(Modifier.height(MaterialTheme.spacers.medium))
        SidebarContentItem(3, Icons.Default.Pages, "My Requests") { /* doSomething() */ }
        SidebarContentItem(4, Icons.Default.RequestPage, "My Offers") { /* doSomething() */ }
        Spacer(Modifier.height(MaterialTheme.spacers.medium))
        SidebarContentItem(1, Icons.Default.Pages, "Requests") { /* doSomething() */ }
        SidebarContentItem(2, Icons.Default.RequestPage, "Offers") { /* doSomething() */ }
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
                color = if (index % 2 == 0) Color.White.copy(alpha = 0.9F) else Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}


@Composable
fun MainContent() {
    // Your main app content here...
    Column {
        RequestCardList()
        // AddressCard(FakeDataGenerator.createFakeAddress())
        // BidCard(FakeDataGenerator.createFakeBid(UUID.randomUUID()))
        // CargoCard(FakeDataGenerator.createFakeCargo())
        // RequestCard(FakeDataGenerator.createFakeRequest())

        //    ShowRequestFormPopup()
    }
}

@ExperimentalComposeUiApi
fun main() = application {
    val windowState = rememberWindowState(size = DpSize(580.dp, 1000.dp))

    Window(onCloseRequest = ::exitApplication, title = "Fretando", state = windowState) {
        MyApp()
    }
}
