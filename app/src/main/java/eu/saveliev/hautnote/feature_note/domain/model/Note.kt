package eu.saveliev.hautnote.feature_note.domain.model

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.room.Entity
import  androidx.room.PrimaryKey
import eu.saveliev.hautnote.ui.theme.*

@Entity
data class Note(
    val icon: Int,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val noteColors = listOf(
            RedOrange,
            LightGreen,
            Violet,
            BabyBlue,
            RedPink
        )
    }
}

class InvalidNoteException(message: String): Exception(message) {

}