package fi.fimurito.mytimer

import androidx.room.Room
import fi.fimurito.mytimer.data.Task
import fi.fimurito.mytimer.data.TaskDatabase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

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
}