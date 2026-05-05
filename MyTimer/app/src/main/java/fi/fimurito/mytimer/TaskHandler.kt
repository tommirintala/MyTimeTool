package fi.fimurito.mytimer

import fi.fimurito.mytimer.data.model.Task
import java.time.LocalDateTime

class TaskHandler {
    private var currentTaskId: Long = -1L
    private var currentRemoteId: Long = -1L
    private var currentBeginTime =  LocalDateTime.now()
    private var currentEndTime = LocalDateTime.now()
    private var currentCode = "--"
    private var currentTitle = "New Task"

    private fun reset() {
        currentTitle = "New Task"
        currentCode = "--"
        currentBeginTime = LocalDateTime.now()
        currentEndTime = LocalDateTime.now().plusMinutes(AppConstants.DEFAULT_TASK_INCREMENT_LENGTH_MINUTES)
        currentRemoteId = -1L
        currentTaskId = -1L
    }

    fun isEmpty(): Boolean {
        if (currentTaskId != -1L)
            return true
        return false
    }

    private val taskList: List<Task> = emptyList()

    private fun clearListCache() {
        if (taskList.size > 0)
            taskList.drop(taskList.size)
    }
    fun findTaskById(id: Long): List<Task> {
        clearListCache()
        return taskList
    }

    fun findTaskByCode(code: String): List<Task> {
        clearListCache()
        return taskList
    }

    fun findTaskByTitle(title: String): List<Task> {
        clearListCache()
        return taskList
    }

    fun choose(id: Long) : Boolean {
        var found = false
        taskList.forEach { item ->
            if (item.getId() == id) {
                currentTitle = item.title
                currentCode = item.code
                currentTaskId = id
                currentBeginTime = item.beginTime
                currentEndTime = item.endTime
                found = true
            }
        }

        return found
    }
    fun startNow() {
        currentBeginTime = LocalDateTime.now()
        currentEndTime = LocalDateTime.now().plusMinutes(AppConstants.DEFAULT_TASK_INCREMENT_LENGTH_MINUTES)
    }

    fun advanceEnd() {
        currentEndTime.plusMinutes(AppConstants.DEFAULT_TASK_INCREMENT_LENGTH_MINUTES)
    }

    fun getStartTime(): String {
        return currentBeginTime.toString()
    }

    fun getEndTime(): String {
        return currentEndTime.toString()
    }

    fun getTitle(): String {
        return currentTitle
    }

    fun getCode(): String {
        return currentCode
    }

    fun build(): Task {
        val task = Task(
            currentTaskId,
            currentRemoteId,
            code = currentCode,
            beginTime = currentBeginTime,
            endTime = currentEndTime,
            title = currentTitle
        )

        return task
    }
}