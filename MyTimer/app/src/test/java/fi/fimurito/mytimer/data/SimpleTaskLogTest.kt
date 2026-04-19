package fi.fimurito.mytimer.data

import android.content.Context
import androidx.room.Room

import fi.fimurito.mytimer.Utils
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class SimpleTaskLogTest {
    private val context: Context by lazy { RuntimeEnvironment.getApplication() }
    private lateinit var taskDao: TaskDao
    private lateinit var taskLogDao: TaskLogDao
    private lateinit var db: TaskDatabase
    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            context,
            TaskDatabase::class.java
        ).build()
        taskDao = db.taskDao()
        taskLogDao = db.taskLogDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun `test simple task logging`() = runTest {
        val tasks = (0 until 4L).map { id ->
            Task(
                id = id,
                remoteId = 1000L+id,
                title = "Task #${id}",
                code = "100${id}",
                abbr = "T${id}",
            )
        }

        taskDao.insertAll(tasks[0], tasks[1], tasks[2], tasks[3])

        taskDao.getAll().collect { tasklist ->
            tasklist.forEach {
                val log = TaskLog(
                    taskId = it.getId(),
                    beginDate = Utils.taskTimer("2012-05-01 18:03"),
                    endDate = Utils.taskTimer("2012-05-01 19:11"),
                    comment = "Task comment for task #${it.getId()}"
                )
            }

        }
    }
}