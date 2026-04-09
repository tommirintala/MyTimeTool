package fi.fimurito.mytimer


interface TasksApi {
    fun fetchLatestTasks(): List<Task>
}