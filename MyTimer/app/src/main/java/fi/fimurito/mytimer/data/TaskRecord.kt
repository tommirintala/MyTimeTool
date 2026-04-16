package fi.fimurito.mytimer.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
@Entity
class TaskRecord(
    @PrimaryKey val id: Long? = null,
    @ColumnInfo val taskId: Long? = null,
    @ColumnInfo val beginDate: Date? = null,
    @ColumnInfo val endDate: Date? = null,
    @ColumnInfo val comment: String? = null,
    @ColumnInfo(name = "created_at") val createTime: Date = Date(),
    @ColumnInfo(name = "modified_at") val modificationTime: Date = Date()
){
}