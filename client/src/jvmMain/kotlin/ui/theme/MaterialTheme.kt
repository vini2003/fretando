package ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@get:Composable
val Colors.lightGray: Color
    get() = Color.LightGray.copy(alpha = 0.1f)

@get:Composable
val Colors.mediumGray: Color
    get() = Color.LightGray.copy(alpha = 0.2f)

object Sizes {
    val small = 8.dp
    val medium = 16.dp
    val large = 24.dp
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