package fi.fimurito.mytimer.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ImageLInks(
    @ColumnInfo(name="smallThumbnail") val smallThumbnail: String? = null,
    @ColumnInfo(name="thumbnail") val thumbnail: String? = null
)
