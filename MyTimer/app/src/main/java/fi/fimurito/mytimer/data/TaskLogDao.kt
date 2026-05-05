package fi.fimurito.mytimer.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fi.fimurito.mytimer.data.model.TaskLog
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskLogDao {
    @Query("SELECT * FROM tasklogs")
    fun getAll(): Flow<List<TaskLog>>

    @Insert
    fun insertAll(vararg taskLog: TaskLog)

    @Update
    fun updateTaskLog(vararg taskLog: TaskLog)

    @Delete
    fun delete(taskLog: TaskLog)

    //@Delete
    //fun deleteAll()
}