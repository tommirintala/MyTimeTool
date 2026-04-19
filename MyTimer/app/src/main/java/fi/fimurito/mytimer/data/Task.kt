package fi.fimurito.mytimer.data

// import kotlinx.serialization.Serializable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
// import java.util.Date


// @Serializable
@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey private val id: Long? = null,
    @ColumnInfo val remoteId: Long = 0L,
    @ColumnInfo(name="code") val code: String = "",
    @ColumnInfo(name="abbr") val abbr: String = "",
    @ColumnInfo(name="title") val title: String = "Task",
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