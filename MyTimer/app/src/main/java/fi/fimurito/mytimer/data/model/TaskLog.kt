package fi.fimurito.mytimer.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import fi.fimurito.mytimer.data.MyTypeConverters
import java.time.Duration
import java.time.LocalDateTime

// @Serializable
@Entity(tableName = "tasklogs")
@TypeConverters(MyTypeConverters::class)
class TaskLog(
    @PrimaryKey val id: Int? = null,
    @ColumnInfo val taskId: Int? = null,
    @ColumnInfo val beginDate: LocalDateTime?,
    @ColumnInfo val endDate: LocalDateTime?,
    @ColumnInfo val comment: String? = null,
    @ColumnInfo(name = "created_at") val createTime: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = "modified_at") val modificationTime: LocalDateTime = LocalDateTime.now()
){
    fun getDuration(): Long {
        if (beginDate !== null && endDate !== null) {
            val duration = Duration.between(beginDate, endDate)
            return duration.toMinutes()
        } else {
            return 0L
        }
    }
}