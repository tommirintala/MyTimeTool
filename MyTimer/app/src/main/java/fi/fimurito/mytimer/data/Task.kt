package fi.fimurito.mytimer.data

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import androidx.room.Entity
import kotlinx.serialization.Serializable
import java.util.Date


@Serializable
@Entity
data class Task(
    @PrimaryKey val id: Long = Long.MIN_VALUE,
    @ColumnInfo(name="code") val code: String = "",
    @ColumnInfo(name="abbr") val abbr: String = "",
    @ColumnInfo(name="title") val title: String = "Task",
    @ColumnInfo(name="created_at") val creationTime: Date = Date(),
    @ColumnInfo(name="modified_at") val modificationTime: Date = Date(),
    @ColumnInfo(name="beginTime") val beginTime: Date = Date(),
    @ColumnInfo(name="endTime") val endTime: Date = Date()
)