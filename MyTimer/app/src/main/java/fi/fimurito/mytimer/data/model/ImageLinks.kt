package fi.fimurito.mytimer.data.model

import androidx.room.ColumnInfo
import kotlinx.serialization.Serializable

@Serializable
data class ImageLinks(
    @ColumnInfo(name="smallThumbnail") val smallThumbnail: String? = null,
    @ColumnInfo(name="thumbnail") val thumbnail: String? = null
)
