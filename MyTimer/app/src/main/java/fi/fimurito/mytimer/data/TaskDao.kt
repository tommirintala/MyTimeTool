package fi.fimurito.mytimer.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fi.fimurito.mytimer.data.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks")
    fun getAll(): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE id IN (:ids)")
    fun loadAllByIds(ids: IntArray): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE id IN (:ids)")
    fun getTasksById(ids: List<Long>): Flow<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg tasks: Task)

    @Query("SELECT * FROM tasks ORDER BY created_at DESC LIMIT 1")
    fun getLastTask(): Task

    @Query("SELECT * FROM tasks WHERE id = :ids")
    fun getTask(ids: Long): Task

    @Query("SELECT * FROM tasks WHERE title LIKE :name")
    fun findTask(name: String): Flow<List<Task>>

    @Delete
    suspend fun delete(task: Task): Int
}