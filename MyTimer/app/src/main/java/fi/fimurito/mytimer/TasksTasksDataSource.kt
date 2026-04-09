package fi.fimurito.mytimer


import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

private const val REFRESH_RATE_HOURS = 4L
private const val FETCH_LATEST_TASKS_TASK = "FetchLatestTasksTask"
private const val TAG_FETCH_LATEST_TASKS = "FetchLatestTasksTaskTag"

class TasksTasksDataSource(
    private val workManager: WorkManager
) {
    fun fetchTasksPeriodically() {
        val fetchTasksRequest = PeriodicWorkRequestBuilder<RefreshLatestTasksWorker>(
            REFRESH_RATE_HOURS, TimeUnit.HOURS
        ).setConstraints(
            Constraints.Builder()
                .setRequiredNetworkType(NetworkType.TEMPORARILY_UNMETERED)
                .setRequiresCharging(true)
                .build()
        )
            .addTag(TAG_FETCH_LATEST_TASKS)

        workManager.enqueueUniquePeriodicWork(
            FETCH_LATEST_TASKS_TASK,
            ExistingPeriodicWorkPolicy.KEEP,
            fetchTasksRequest.build()
        )
    }

    fun cancelFetchingTasksPeriodically() {
        workManager.cancelAllWorkByTag(TAG_FETCH_LATEST_TASKS)
    }
}

