@file:OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

// Add these imports at the top of your file
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import ui.compose.Application

// Create a data class for Popup
data class PopupComposable(val id: Int = lastId++, val content: @Composable (id: Int) -> Unit) {
    companion object {
        private var lastId = 0
    }
}


@ExperimentalComposeUiApi
fun main() = application {
    val windowState = rememberWindowState(size = DpSize(580.dp, 1000.dp))

    Window(onCloseRequest = ::exitApplication, title = "Fretando", state = windowState) {
        Application()
    }
}
