package fi.fimurito.mytimer.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskRecordDao {
    @Query("SELECT * FROM taskrecord")
    fun getAll(): Flow<List<TaskRecord>>

    @Insert
    fun insertAll(vararg taskRecord: TaskRecord)

    @Update
    fun updateTaskRecord(vararg taskRecord: TaskRecord)

    @Delete
    fun delete(taskRecord: TaskRecord)

    //@Delete
    //fun deleteAll()
}