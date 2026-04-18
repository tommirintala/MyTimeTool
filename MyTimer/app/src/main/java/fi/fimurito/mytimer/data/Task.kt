package fi.fimurito.mytimer.data

// import kotlinx.serialization.Serializable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


// @Serializable
@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey private val id: Long? = null,
    @ColumnInfo val remoteId: Long = 0L,
    @ColumnInfo(name="code") val code: String = "",
    @ColumnInfo(name="abbr") val abbr: String = "",
    @ColumnInfo(name="title") val title: String = "Task",
    @ColumnInfo(name="created_at") val creationTime: Date = Date(),
    @ColumnInfo(name="modified_at") val modificationTime: Date = Date(),
    @ColumnInfo(name="beginTime") val beginTime: Date? = null,
    @ColumnInfo(name="endTime") val endTime: Date? = null,
) {
    fun getId(): Long? {
        return id
    }

}