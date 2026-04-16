package fi.fimurito.mytimer.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAll(): Flow<List<Task>>

    @Query("SELECT * FROM task WHERE id IN (:ids)")
    fun loadAllByIds(ids: IntArray): Flow<List<Task>>

    @Insert
    fun insertAll(vararg tasks: Task)

    @Query("SELECT * FROM task ORDER BY created_at DESC LIMIT 1")
    fun getLastTask(): Task

    @Query("SELECT * FROM task WHERE title LIKE ':name'")
    fun findTask(name: String)

    @Delete
    fun delete(task: Task)
}