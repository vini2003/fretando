@file:OptIn(ExperimentalComposeUiApi::class)

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import dev.vini2003.fretando.common.`object`.faker.FakeDataGenerator
import ui.compose.RequestCard
import ui.compose.RequestCardList
import ui.compose.RequestForm

@Composable
fun MyApp() {
    MaterialTheme {
        TopAppBar(
            title = { Text("Fretando") },
            navigationIcon = {
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(Icons.Default.Menu, contentDescription = "Menu")
                }
            }
        )

        Row {
            // Sidebar
            Box(
                modifier = Modifier
                    .offset(y = 56.dp)
                    .width(200.dp)
                    .fillMaxHeight()
                    .background(MaterialTheme.colors.primaryVariant) // change to your desired purple color
            ) {
                SidebarContent()
            }

            // The rest of your app
            Box(
                modifier = Modifier
                    .offset(y = 56.dp)
            ) {
                MainContent()
            }
        }
    }
}

@Composable
fun SidebarContent() {
    // Define what the sidebar looks like
    Column(modifier = Modifier.padding(8.dp)) {
        Text("Item 1", color = Color.White)
        Text("Item 2", color = Color.White)
        // More items...
    }
}

@Composable
fun MainContent() {
    // Your main app content here...
    RequestCardList()
}


@Composable
@Preview
fun App() {
    MaterialTheme {
        MyApp()
    }
}

@ExperimentalComposeUiApi
fun main() = application {
    val windowState = rememberWindowState(size = DpSize(580.dp, 1000.dp))

    Window(onCloseRequest = ::exitApplication, title = "Fretando", state = windowState) {
        App()
    }
}
