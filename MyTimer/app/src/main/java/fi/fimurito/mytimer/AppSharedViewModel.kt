package fi.fimurito.mytimer

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import fi.fimurito.mytimer.data.model.Task
import fi.fimurito.mytimer.data.TaskDao
import fi.fimurito.mytimer.data.AppDatabase
import fi.fimurito.mytimer.data.TaskLogDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class MyUiState (
    val isLoading: Boolean = false,
            val data: String = ""
)
class AppSharedViewModel(application: Application) : AndroidViewModel(application) {
    private var context: Context

    init {
        context = application.applicationContext
        openDB()
    }

    private val _uiState = MutableStateFlow(MyUiState())
    val uiState: StateFlow<MyUiState> = _uiState.asStateFlow()

    fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<AppDatabase> {
        val appContext = context.applicationContext
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
    lateinit var taskDao: TaskDao
    lateinit var taskLogDao: TaskLogDao

    fun openDB() {
        db = getRoomDatabase(getDatabaseBuilder(context))
        taskDao = db.taskDao()
        taskLogDao = db.taskLogDao()
    }

    var taskHandler: TaskHandler = TaskHandler()

    fun fetchData() {
        _uiState.value = MyUiState(isLoading = true)
    }

    fun findTasksByTitle(title: String): Flow<List<Task>> {
        return taskDao.findTask(title)
    }

    fun findTasksById(id: Long): Flow<List<Task>> {
        return taskDao.getTasksById(listOf(id))
    }

    fun chooseTask(id: Long): Boolean {
        return taskHandler.choose(id)
    }

    fun saveTask(): Boolean {
        if (taskHandler.isEmpty())
            return false
        return true
    }

    fun getTaskTitle(): String {
        return taskHandler.getTitle()
    }

    fun getTaskCode(): String {
        return taskHandler.getCode()
    }

    fun advanceTaskEndTime() {
        taskHandler.advanceEnd()
    }

    fun startTaskNow() {
        taskHandler.startNow()
    }

    fun getStartTime(): String {
        return taskHandler.getStartTime()
    }

    fun getEndTime(): String {
        return taskHandler.getEndTime()
    }
}