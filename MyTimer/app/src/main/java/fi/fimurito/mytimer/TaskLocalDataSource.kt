package fi.fimurito.mytimer

import fi.fimurito.mytimer.data.model.Task
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