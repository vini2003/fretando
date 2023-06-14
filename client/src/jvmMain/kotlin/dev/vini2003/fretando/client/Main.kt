package dev.vini2003.fretando.client

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import dev.vini2003.fretando.client.ui.compose.application.Application
import javax.imageio.ImageIO

object Resources {}

@ExperimentalMaterial3Api
@ExperimentalComposeUiApi
fun main() = application {
    val icon = ImageIO.read(Resources::class.java.classLoader.getResource("icon.png"))

    val windowState = rememberWindowState(size = DpSize(580.dp, 1000.dp))

    Window(onCloseRequest = ::exitApplication, title = "Fretando", state = windowState) {
        this.window.iconImage = icon

        Application()
    }
}
