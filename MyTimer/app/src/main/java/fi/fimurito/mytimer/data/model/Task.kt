package fi.fimurito.mytimer.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset


@Serializable
@Entity(tableName = "tasks")
data class Task(
    //@PrimaryKey private val id: Long? = null,
    @PrimaryKey private val id: Int = 0,
    @ColumnInfo val remoteId: Int = -1,
    @ColumnInfo(name="title") val title: String = "Task",
    @ColumnInfo(name="etags") val tags: String = "",
    // @ColumnInfo(name="task_info") val taskInfo: TaskInfo? = null,
    @ColumnInfo(name="code") val code: String = "",
    @ColumnInfo(name="abbr") val abbr: String = "",

    @ColumnInfo(name="max_hours") var maxHours: Float = 0f,
    @ColumnInfo(name="set_hours") var setHours: Float = 0f,
    @ColumnInfo(name="task_type") var taskType: Int = 1,
    //@ColumnInfo(name="task_type") var taskType: TaskType = TaskType.TASKTYPE_SINGLETASK,
    @ColumnInfo(name="validFrom") val validFrom: LocalDateTime? = null,
    @ColumnInfo(name="validUntil") val validUntil: LocalDateTime? = null,
    @ColumnInfo(name="lastSync") val lastSync: LocalDateTime? = null,
    @ColumnInfo(name="created_at") val creationTime: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name="modified_at") val modificationTime: LocalDateTime = LocalDateTime.now()

    ) {
    fun getId(): Int {
        return id
    }
/*
    fun isAvailable(timestamp: LocalDateTime?): Boolean {
        if (validFrom == 0L || validUntil == 0L)
            return true
        var result = false
        if (timestamp === null) {
            val ts = LocalDateTime.now()
            result = (ts in validFrom .. validUntil)
        } else {
            result =  (timestamp in validFrom..validUntil)
        }

        return result
    }
 */
    /**
     * Check if task is available for specific time value
     */
    /*
    fun isAvailable(timestamp: LocalDateTime?): Boolean {
        if (timestamp != null) {

            return (timestamp in validFrom .. validUntil)
        }
        //val ts = timestamp?.toEpochSecond(ZoneOffset.UTC) ?: LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
        return timestamp in validFrom .. validUntil
    }
    */
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
