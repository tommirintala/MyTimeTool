package fi.fimurito.mytimer

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fi.fimurito.mytimer.data.TaskPagingSource
import fi.fimurito.mytimer.data.model.Task
import fi.fimurito.mytimer.data.model.TaskLog
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import kotlin.random.Random


private const val PAGE_SIZE = 4

data class CurrentTaskState(
    val taskId: Int = 0,
    val taskTitle: String = "",
    val isTaskRunning: Boolean = false,
    val errorMessage: String? = null,
    val taskStartedAt: LocalDateTime = LocalDateTime.now(),
    val taskTime: java.time.Duration = java.time.Duration.ZERO,
    val autoStopTime: LocalDateTime = LocalDateTime.now()
)

class MainViewModel() : ViewModel() {

    var context by mutableStateOf<Context?>(null)

    var query = mutableStateOf("")
        private set

    //val repo = db.taskDao()
    //val logrepo = db.taskLogDao()

    //var repo =  mutableStateOf<TaskDao>()
    //var logrepo =  mutableStateOf<TaskLogDao>()

    //val db = mutableStateOf<AppDatabase>(null)

    //lateinit var db: AppDatabase

    //private val repo: TaskRepository = TaskRepository()
    //private lateinit var repo: TaskRepository
    //private lateinit var logrepo: TaskLogRepository
    private lateinit var paginSource: TaskPagingSource
    private val _valid: MutableStateFlow<Boolean?> = MutableStateFlow(null)
    val valid: StateFlow<Boolean?> = _valid

    //init {
    //  isUserDataValid()


    //}

    //fun initialize(context: Context) {
    //    db = getRoomDatabase(getDatabaseBuilder(context))
    //    repo = TaskRepository(db.taskDao())
    //    logrepo = TaskLogRepository(db.taskLogDao())
    //}

    /*
    val taskPager = Pager(PagingConfig(pageSize = PAGE_SIZE)) {
        TaskPagingSource(query.value, repo).also {
            paginSource = it
        }
    }.flow

    fun setQuery(query: String) {
        this.query.value = query
    }

    fun invalidateDataSource() {
        paginSource.invalidate()
    }
*/
    private fun isUserDataValid() = viewModelScope.launch {
        delay(1000)
        _valid.update { Random.nextBoolean() }
    }

    fun trackSplashScreenStarted() {

    }

    var syncNetwork by mutableStateOf(false)
    var syncURL by mutableStateOf("")

    //var useVAMKServer: Boolean = false
    var useVAMKServer by mutableStateOf(false)
    //var currentTask: Long = 0L

    //var taskRunning: Boolean = false
    //var taskRunning by mutableStateOf(false)

    //var currentTaskStart: LocalDateTime = LocalDateTime.MIN
    //var currentTaskStart by mutableStateOf<LocalDateTime>(LocalDateTime.MIN)

    //var autoStopTime by mutableStateOf<LocalDateTime>(LocalDateTime.MIN)

    //var taskList  = remember { mutableListOf(emptyList<Task>()) }
    //var taskList = mutableListOf(emptyList<Task>())
    //var taskList = mutableMapOf<Long, Task>()
    var taskList = mutableMapOf<Int, Task>()

    init {

        //(1L..10L).forEach { item ->
        //    taskList[item] = Task(item, title = "Task #${item}")
        //}

    }

    var taskRangeBegin by mutableStateOf<Long?>(null)
    var taskRangeEnd by mutableStateOf<Long?>(null)

    var taskState by mutableStateOf(CurrentTaskState())
    // var currentTaskTime by mutableStateOf(0L)
    fun startNewTask(id: Int, title: String) {
        println("MainViewModel: start new task ${id} - ${title}")
        val now = LocalDateTime.now()
        taskState = taskState.copy(
            taskId = id,
            taskTitle = title,
            isTaskRunning = true,
            taskStartedAt = now,
            autoStopTime = now.plusMinutes(taskIncrementMinutes),
            taskTime = java.time.Duration.ZERO
        )
    }

    fun endCurrentTask() : TaskLog? {
        if (taskState.isTaskRunning) {
            val taskId = taskState.taskId
            val startTime = taskState.taskStartedAt
            val endTime = LocalDateTime.now()
            val comment = "n/a"

            val duration = java.time.Duration.between(taskState.taskStartedAt, LocalDateTime.now())
            println("Ending task with id: ${taskState.taskId} started at ${taskState.taskStartedAt}")
            println("Current time is ${LocalDateTime.now()}, duration=${duration}")

            // save ->

            println("Save Event: Duration = ${duration.toMinutes()}")
            taskState = taskState.copy(
                isTaskRunning = false,
                taskStartedAt = LocalDateTime.MIN,
                taskTime = duration
            )

            //viewModelScope.launch {
            //    logrepo.insertAll(
            return                     TaskLog(
                    taskId = taskId,
                    beginDate =  startTime,
                    endDate =  endTime,
                    comment = comment
                    )
              //  )
            //}


        } else {
            taskState = taskState.copy(
                isTaskRunning = false,
                taskStartedAt = LocalDateTime.MIN
            )
        }
    return null
    }

    fun incrementTaskAutoEnd() {
        println("MainViewModel: increment autoEnd value")
        taskState = taskState.copy(
            autoStopTime = taskState.autoStopTime.plusMinutes(taskIncrementMinutes)
        )
    }

    fun stopCurrentTask() {
        if (taskState.isTaskRunning) {
            val duration = java.time.Duration.between(taskState.taskStartedAt, LocalDateTime.now())
            taskState = taskState.copy(
                isTaskRunning = false,
                taskTime = duration
            )
        } else {
            taskState = taskState.copy(
                isTaskRunning = false
            )
        }
    }

    fun refreshUI() {

    }

    var taskIncrementMinutes by mutableLongStateOf(15L)
}