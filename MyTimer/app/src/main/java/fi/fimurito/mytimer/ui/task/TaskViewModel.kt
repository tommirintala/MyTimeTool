package fi.fimurito.mytimer.ui.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fi.fimurito.mytimer.TasksRepository

class TaskViewModel(private val tasksRepository: TasksRepository): ViewModel() {
    private val _taskForm = MutableLiveData<TaskFormState>()
    val taskFormState: LiveData<TaskFormState> = _taskForm

    private val _taskResult = MutableLiveData<TaskResult>()

    val taskResult: LiveData<TaskResult> = _taskResult

    fun start(taskId: Long) {
        val result = tasksRepository.switch(taskId)

        if (result is Result.Success) {
            _taskResult.value = TaskResult(success = CurrentTaskUserView(taskId = taskId, taskTitle = result.data.title))
        } else {
            _taskResult.value = TaskResult(error = "Task switch failed")
        }
    }
}