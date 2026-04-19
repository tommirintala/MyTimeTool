package fi.fimurito.mytimer.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.time.LocalDateTime
import java.time.ZoneId



import kotlin.time.DurationUnit
import kotlin.time.toDuration

// @Serializable
@Entity(tableName = "tasklogs")
@TypeConverters(MyTypeConverters::class)
class TaskLog(
    @PrimaryKey val id: Long? = null,
    @ColumnInfo val taskId: Long? = null,
    @ColumnInfo val beginDate: LocalDateTime?,
    @ColumnInfo val endDate: LocalDateTime?,
    @ColumnInfo val comment: String? = null,
    @ColumnInfo(name = "created_at") val createTime: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = "modified_at") val modificationTime: LocalDateTime = LocalDateTime.now()
){
    fun getDuration(): Long {
        if (beginDate !== null && endDate !== null) {
            val duration = java.time.Duration.between(beginDate, endDate)
            return duration.toMinutes()
        } else {
            return 0L
        }
    }
}