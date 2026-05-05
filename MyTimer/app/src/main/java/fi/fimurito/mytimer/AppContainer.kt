package fi.fimurito.mytimer

import fi.fimurito.mytimer.data.LoginContainer
import fi.fimurito.mytimer.data.TaskLogRepository
import fi.fimurito.mytimer.data.TaskRepository
import fi.fimurito.mytimer.data.UserRepository
import fi.fimurito.mytimer.data.model.Task
import fi.fimurito.mytimer.data.model.TaskLog


class TaskContainer(val taskRepository: TaskRepository) {
    val taskData = Task()
    val taskViewModelFactory = TaskViewModelFactory(taskRepository)
}

/*
class TaskLogContainer(val taskLogRepository: TaskLogRepository) {
    //val taskLog = TaskLog()
    //val taskLogViewModelFactory = TaskLogViewModelFactory(taskLogRepository)
}

 */
class AppContainer {
    /*
    private val retrofit = Retrofit.Builder()
        .baseUrl("")
        .build()

     */


    //private val userRemoteDataSource = UserRemoteDataSource(retrofit)
    //private val userLocalDataSource = UserLocalDataSource()

    //private val taskRemoteDataSource = TaskRemoteDataSource()
    private val taskRemoteDataSource = null
    private val taskLocalDataSource = TaskLocalDataSource()
    //private val taskLogRemoteDataSource = TaskLogRemoteDataSource()
    //private val taskLogRemoteDataSource = null
    //private val taskLogLocalDataSource = TaskLogLocalDataSource()

    // userRepository is not private, it will be exposed
    //val userRepository = UserRepository(localDataSource, remoteDataSource)

    //var loginContainer: LoginContainer? = null

    val taskRepository = TaskRepository(taskLocalDataSource, taskRemoteDataSource)
    //val taskLogRepository = TaskLogRepository(taskLogLocalDataSource, taskLogRemoteDataSource)

    var taskContainer: TaskContainer? = null
    //var taskLogContainer: TaskLogContainer? = null
}