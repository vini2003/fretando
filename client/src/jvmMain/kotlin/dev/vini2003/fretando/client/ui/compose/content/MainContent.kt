package dev.vini2003.fretando.client.ui.compose.content

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@ExperimentalMaterial3Api
@Composable
fun MainContent(selectedMainContent: MutableState<@Composable () -> Unit>) {
    // Your main app content here...
    Column {
        selectedMainContent.value()
    }
}