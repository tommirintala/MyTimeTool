package fi.fimurito.mytimer

import androidx.room.Room
import fi.fimurito.mytimer.data.Task
import fi.fimurito.mytimer.data.TaskDatabase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.withContext
import javax.sql.DataSource

class TaskLocalDataSource (private val tasksApi: TasksApi,
private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun fetchLatestTasks(): List<Task> =
        withContext(ioDispatcher) {
            tasksApi.fetchLatestTasks()
        }

    /*
    suspend fun init() {
        val db = Room.databaseBuilder(
            applicationContext,
            TaskDatabase::class.java,
            R.string.app_database_name
        )
    }

     */
/*
    fun tasks(): Flow<Task> {
        return tasksApi.counts().map { it * 10 }
    }

 */
}
/*
class FakeDataSource : DataSource {
    private val flow = MutableSharedFlow<Task>()
    suspend fun emit(value: Task) = flow.emit(value)
    override fun tasks(): Flow<Task> = flow
}

 */