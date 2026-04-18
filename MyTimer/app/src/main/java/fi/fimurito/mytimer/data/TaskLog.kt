package fi.fimurito.mytimer.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.Date

// @Serializable
@Entity(tableName = "tasklogs")
@TypeConverters(MyTypeConverters::class)
class TaskLog(
    @PrimaryKey val id: Long? = null,
    @ColumnInfo val taskId: Long? = null,
    @ColumnInfo val beginDate: Date?,
    @ColumnInfo val endDate: Date?,
    @ColumnInfo val comment: String? = null,
    @ColumnInfo(name = "created_at") val createTime: Date = Date(),
    @ColumnInfo(name = "modified_at") val modificationTime: Date = Date()
){
}