package fi.fimurito.mytimer

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class RefreshLatestTasksWorker(
    private val tasksRepository: TasksRepository,
    context: Context,
    params: WorkerParameters
): CoroutineWorker(context, params) {
    override suspend fun doWork(): Result = try {
        tasksRepository.refreshLatestTasks()
        Result.success()
    } catch (error: Throwable) {
        Result.failure()
    }
}