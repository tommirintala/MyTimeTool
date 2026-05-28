package fi.fimurito.mytimer.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import java.time.LocalDateTime


@Serializable
@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey private val id: Long? = null,
    @ColumnInfo val remoteId: Long = -1L,
    @ColumnInfo(name="title") val title: String = "Task",
    @ColumnInfo(name="etags") val tags: String = "",
    // @ColumnInfo(name="task_info") val taskInfo: TaskInfo? = null,
    @ColumnInfo(name="code") val code: String = "",
    @ColumnInfo(name="abbr") val abbr: String = "",

    @ColumnInfo(name="max_hours") var maxHours: Float = 0f,
    @ColumnInfo(name="set_hours") var setHours: Float = 0f,
    @ColumnInfo(name="task_type") var taskType: TaskType = TaskType.TASKTYPE_SINGLETASK,

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



enum class TaskType(
    val label: String,
    val icon: ImageVector,
    val link: String
) {
    TASKTYPE_SINGLETASK("Single task", Icons.Default.Place, ""),
    TASKTYPE_COURSE("Course", Icons.Default.Person, ""),
    //TASKTYPE_RECURRING_WEEKLY(),
    //TASKTYPE_RECURRING_MONTHLY(),
    //TASKTYPE_RECURRING_DAILY()
}

@Serializable
@Entity(tableName = "taskinfos")
data class TaskInfo(
    @PrimaryKey val id: Long,
    @ColumnInfo(name="imageLinks") val imageLinks: ImageLinks? = ImageLinks()
)
