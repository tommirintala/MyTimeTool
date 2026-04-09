package fi.fimurito.mytimer

import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.util.Date

@Serializable
data class Task(
    val id: Long = Long.MIN_VALUE,
    //val owner: Long = Long.MIN_VALUE,
    val code: String = "",
    val abbr: String = "",
    val title: String = "",
    val creationTime: Date = Date(),
    val beginTime: Date = Date(),
    val endTime: Date = Date(),
)
