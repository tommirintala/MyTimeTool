package fi.fimurito.mytimer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fi.fimurito.mytimer.data.TaskRepository
import fi.fimurito.mytimer.ui.task.TaskViewModel

class TaskViewModelFactory: ViewModelProvider.Factory {
    /*
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            /*
            return TaskViewModel(
                taskRepository = TaskRepository(
                    dataSource = TaskDataSource()
                )
            ) as T
             */
            return TaskViewModel(

            )
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

     */
}
