package fi.fimurito.mytimer.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
@Entity
class TaskRecord(
    @PrimaryKey val id: Long,
    @ColumnInfo val taskId: Long,
    @ColumnInfo val startDate: Date,
    @ColumnInfo val endDate: Date,
    @ColumnInfo val comment: String) {
}