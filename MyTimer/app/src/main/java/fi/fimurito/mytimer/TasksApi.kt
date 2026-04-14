package fi.fimurito.mytimer

import fi.fimurito.mytimer.data.Task


interface TasksApi {
    fun fetchLatestTasks(): List<Task>
}