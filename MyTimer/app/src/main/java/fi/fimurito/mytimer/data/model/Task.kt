package fi.fimurito.mytimer.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import java.time.LocalDateTime


@Serializable
@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey private val id: Long? = null,
    @ColumnInfo val remoteId: Long = 0L,
    @ColumnInfo(name="etags") val strTags: String = "",
    // @ColumnInfo(name="task_info") val taskInfo: TaskInfo? = null,
    @ColumnInfo(name="code") val strCode: String = "",
    @ColumnInfo(name="abbr") val strAbbreviation: String = "",
    @ColumnInfo(name="title") val strTitle: String = "Task",
    @ColumnInfo(name="max_hours") var maxHours: Float = 0f,
    @ColumnInfo(name="set_hours") var setHours: Float = 0f,

    @ColumnInfo(name="created_at") val creationTime: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name="modified_at") val modificationTime: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name="beginTime") val beginTime: LocalDateTime? = null,
    @ColumnInfo(name="endTime") val endTime: LocalDateTime? = null,
) {
    fun getId(): Long? {
        return id
    }

    fun isAvailable(timestamp: LocalDateTime?): Boolean {
        if (beginTime === null || endTime === null)
            return true
        var result = false
        if (timestamp === null) {
            val ts = LocalDateTime.now()
            result = (ts in beginTime .. endTime)
        } else {
            result =  (timestamp in beginTime..endTime)
        }

        return result
    }
}


@Serializable
@Entity(tableName = "taskinfos")
data class TaskInfo(
    @PrimaryKey val id: Long,
    @ColumnInfo(name="imageLinks") val imageLinks: ImageLinks? = ImageLinks()
)
