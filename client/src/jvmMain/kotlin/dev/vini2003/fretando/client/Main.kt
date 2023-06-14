package dev.vini2003.fretando.client

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import dev.vini2003.fretando.client.ui.compose.application.Application
import java.awt.Toolkit
import javax.imageio.ImageIO

object Main

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
fun main() = application {
    val icon = ImageIO.read(Main::class.java.classLoader.getResource("icon.png"))
    val screenSize = Toolkit.getDefaultToolkit().screenSize
    val windowState = rememberWindowState(width = screenSize.width.dp, height = screenSize.height.dp, placement = WindowPlacement.Maximized)

    Window(
        onCloseRequest = ::exitApplication,
        title = "Fretando",
        state = windowState
    ) {
        this.window.iconImage = icon
        this.window.extendedState = java.awt.Frame.MAXIMIZED_BOTH

        Application()
    }
}
