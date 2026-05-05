package fi.fimurito.mytimer

import fi.fimurito.mytimer.data.model.Task


interface TasksApi {
    fun fetchLatestTasks(): List<Task>
    fun counts(): Int
}