package fi.fimurito.mytimer

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class TaskLocalDataSource (private val tasksApi: TasksApi,
private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun fetchLatestTasks(): List<Task> =
        withContext(ioDispatcher) {
            tasksApi.fetchLatestTasks()
        }
}