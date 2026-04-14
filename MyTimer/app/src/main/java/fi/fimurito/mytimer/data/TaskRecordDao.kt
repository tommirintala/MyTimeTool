package fi.fimurito.mytimer.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskRecordDao {
    @Query("SELECT * FROM taskrecord")
    fun getAll(): List<TaskRecord>

    @Insert
    fun insertAll(vararg taskrecord: TaskRecord)

    @Delete
    fun delete(taskrecord: TaskRecord)
}