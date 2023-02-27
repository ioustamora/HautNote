package eu.saveliev.hautnote.feature_note.presentation.notes.components

import androidx.compose.foundation.background
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun NoteIcon(
    iconInt: Int = 0,
    iconText: String = "Click me!",
    iconBg: Color = Color.LightGray,
    iconTint: Color = Color.DarkGray
) {

    val iconImage: ImageVector = when(iconInt) {
        1 -> Icons.Default.Home
        2 -> Icons.Default.Star
        3 -> Icons.Default.Build
        4 -> Icons.Default.Call
        5 -> Icons.Default.Person
        6 -> Icons.Default.Share
        7 -> Icons.Default.Search
        8 -> Icons.Default.Info
        9 -> Icons.Default.Lock
        10 -> Icons.Default.Close
        else -> Icons.Default.Edit
    }

    Icon(
        imageVector = iconImage,
        contentDescription = iconText,
        modifier = Modifier.background(iconBg),
        tint = iconTint
    )
}