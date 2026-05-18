package fi.fimurito.mytimer.data.model

import androidx.room.ColumnInfo
import kotlinx.serialization.Serializable


@Serializable
data class TaskInfo(
    @ColumnInfo(name="imageLinks") val imageLinks: ImageLinks? = ImageLinks()
)
