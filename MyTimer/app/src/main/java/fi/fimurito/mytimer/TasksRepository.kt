package fi.fimurito.mytimer

import androidx.work.ListenableWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class TasksRepository(
    private val taskRemoteDataSource: TaskRemoteDataSource,
    private val taskLocalDataSource: TaskLocalDataSource,
    private val externalScope: CoroutineScope
) {
    private val latestTaskMutex = Mutex()
    private var latestTasks: List<Task> = emptyList()

    /*
    suspend fun getLatestTasks(refresh: Boolean = false): List<TaskHeadline> {
        if (refresh || latestTasks.isEmpty()) {
            val networkResult = taskRemoteDataSource.fetchLatestTasks()
            // Thread-safe write to latestTasks
            latestTaskMutex.withLock {
                this.latestTasks = networkResult
            }
        }

        return latestTaskMutex.withLock { this.latestTasks }
    }
     */

    suspend fun getLatestTasks(refresh: Boolean = false): List<Task> {
        return if (refresh) {
            externalScope.async {
                taskRemoteDataSource.fetchLatestTasks().also {
                    networkResult ->
                    // Thread safe write to latestTasks.
                    latestTaskMutex.withLock {
                        latestTasks = networkResult
                    }
                }
            }.await()
        } else {
            return latestTaskMutex.withLock { this.latestTasks }
        }
    }

    suspend fun refreshLatestTasks(): List<Task> {
        return getLatestTasks(true)
    }
}