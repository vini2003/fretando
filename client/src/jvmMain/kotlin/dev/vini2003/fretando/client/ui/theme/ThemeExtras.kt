package dev.vini2003.fretando.client.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@get:Composable
val Color.lightGray: Color
    get() = Color.LightGray.copy(alpha = 0.1f)

@get:Composable
val Color.mediumGray: Color
    get() = Color.LightGray.copy(alpha = 0.2f)


object Sizes {
    val small = 8.dp
    val medium = 16.dp
    val large = 24.dp
    val extraLarge = 48.dp
}

val MaterialTheme.sizes: Sizes
    get() = Sizes

object Paddings {
    val verySmall = 4.dp
    val small = 8.dp
    val medium = 16.dp
    val large = 24.dp
}

val MaterialTheme.paddings: Paddings
    get() = Paddings

object Spacers {
    val verySmall = 4.dp
    val small = 8.dp
    val medium = 16.dp
    val large = 24.dp
}

val MaterialTheme.spacers: Spacers
    get() = Spacers