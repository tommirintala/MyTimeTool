package fi.fimurito.mytimer

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import fi.fimurito.mytimer.data.AppDatabase
import fi.fimurito.mytimer.data.TaskPagingSource
import fi.fimurito.mytimer.data.TaskRepository
import fi.fimurito.mytimer.data.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import kotlin.random.Random

private const val PAGE_SIZE = 4

class MainViewModel : ViewModel() {
    var query = mutableStateOf("")
        private set

    fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<AppDatabase> {
        val appContext = context.applicationContext
        //val dbFile = appContext.getDatabasePath(getString(R.string.app_database_name))
        val dbFile = appContext.getDatabasePath(AppConstants.DATABASE_FILENAME)
        return Room.databaseBuilder<AppDatabase>(
            context = appContext,
            name = dbFile.absolutePath
        )
    }

    fun getRoomDatabase(
        builder: RoomDatabase.Builder<AppDatabase>
    ): AppDatabase {
        return builder
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }

    lateinit var db: AppDatabase

    //private val repo: TaskRepository = TaskRepository()
    private lateinit var repo: TaskRepository
    private lateinit var paginSource: TaskPagingSource
    private val _valid: MutableStateFlow<Boolean?> = MutableStateFlow(null)
    val valid: StateFlow<Boolean?> = _valid

    //init {
    //  isUserDataValid()


    //}

    fun initialize(context: Context) {
        db = getRoomDatabase(getDatabaseBuilder(context))
        repo = TaskRepository(db.taskDao())
    }

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

    private fun isUserDataValid() = viewModelScope.launch {
        delay(1000)
        _valid.update { Random.nextBoolean() }
    }

    fun trackSplashScreenStarted() {

    }

    var syncNetwork by mutableStateOf(false)

    //var useVAMKServer: Boolean = false
    var useVAMKServer by mutableStateOf(false)
    var currentTask: Long = 0L

    //var taskRunning: Boolean = false
    var taskRunning by mutableStateOf(false)

    //var currentTaskStart: LocalDateTime = LocalDateTime.MIN
    var currentTaskStart by mutableStateOf<LocalDateTime>(LocalDateTime.MIN)
    var currentTaskTime by mutableStateOf(0L)
    var autoStopTime by mutableStateOf<LocalDateTime>(LocalDateTime.MIN)

    //var taskList  = remember { mutableListOf(emptyList<Task>()) }
    //var taskList = mutableListOf(emptyList<Task>())
    var taskList = mutableMapOf<Long, Task>()

    init {
        (1L..10L).forEach { item ->
            taskList[item] = Task(item, title = "Task #${item}")
        }

    }

    var taskRangeBegin by mutableStateOf<Long?>(null)
    var taskRangeEnd by mutableStateOf<Long?>(null)
}