package fi.fimurito.mytimer

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import fi.fimurito.mytimer.data.AppDatabase
import fi.fimurito.mytimer.data.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

private const val PAGE_SIZE = 4
class MainViewModel: ViewModel() {
    var query = mutableStateOf("")
        private set

    fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<AppDatabase> {
        val appContext = context.applicationContext
        val dbFile = appContext.getDatabasePath(getString(R.string.app_database_name))
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
    private lateinit var paginSource : TaskPagingSource
    private val _valid : MutableStateFlow<Boolean?> = MutableStateFlow(null)
    val valid: StateFlow<Boolean?> = _valid

    init {
        isUserDataValid()

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
        _valid.update { Random().nextBoolean() }
    }

    fun trackSplashScreenStarted() {

    }
}