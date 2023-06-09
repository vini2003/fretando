package dev.vini2003.fretando.client.ui.compose.popup

import androidx.compose.runtime.Composable

data class PopupComposable(val id: Int = lastId++, val content: @Composable (id: Int) -> Unit) {
    companion object {
        private var lastId = 0
    }
}