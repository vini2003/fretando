@file:OptIn(ExperimentalComposeUiApi::class)

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import dev.vini2003.fretando.common.`object`.faker.FakeDataGenerator
import ui.compose.RequestCard
import ui.compose.RequestCardList
import ui.compose.RequestForm

@Composable
@Preview
fun App() {
    MaterialTheme {
        Column {
            TopAppBar(
                title = { Text("Requests") },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                }
            )

            Spacer(Modifier.height(16.dp))

            RequestCardList()
        }
    }
}

@ExperimentalComposeUiApi
fun main() = application {
    val windowState = rememberWindowState(size = DpSize(580.dp, 1000.dp))

    Window(onCloseRequest = ::exitApplication, title = "Fretando", state = windowState) {
        App()
    }
}
