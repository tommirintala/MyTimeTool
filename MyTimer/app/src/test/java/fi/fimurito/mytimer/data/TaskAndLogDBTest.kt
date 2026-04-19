package fi.fimurito.mytimer.data

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

import app.cash.turbine.test
import app.cash.turbine.awaitItem
import kotlin.collections.emptyList

import io.kotlintest.shouldBe
import io.kotlintest.shouldNotBe
import java.time.LocalDateTime
// import java.util.Date

private const val FAKE_CONTEXT = "FakeContext"

@RunWith(RobolectricTestRunner::class)
class TaskAndLogDBTest {
    private val context: Context by lazy { RuntimeEnvironment.getApplication() }

    private lateinit var taskDao: TaskDao
    private lateinit var taskLogDao: TaskLogDao

    private lateinit var db: TaskDatabase

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            context,
            TaskDatabase::class.java,
        ).build()
        taskDao = db.taskDao()
        taskLogDao = db.taskLogDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun testTaskAndLog() = runTest {
        val tasks = (0 until 6L).map { id ->
            Task(
                id = id,
                remoteId = id + 1000L,
                title = "Task #$id",
                code = "101-$id",
                abbr = "T${id+1000L}",
            )
        }

        taskDao.getAll().test {
            awaitItem() shouldBe emptyList()

            taskDao.insertAll(tasks[0], tasks[1],tasks[2],tasks[3],tasks[4],tasks[5])
            awaitItem() shouldBe listOf(tasks[0], tasks[1],tasks[2],tasks[3],tasks[4],tasks[5])

            taskDao.getAll().collect { t ->
                t.forEach {
                    val taskLog = TaskLog(
                        taskId = it.getId(),
                        beginDate = LocalDateTime.now(),
                        endDate = LocalDateTime.now().plusMinutes(17),
                        comment = "Task #${it.getId()} comment"
                    )

                    taskLogDao.insertAll(taskLog)
                    awaitItem() shouldNotBe 0
                }


            }

            taskLogDao.getAll().test {
                awaitItem() shouldNotBe emptyList<TaskLog>()
            }

            cancelAndConsumeRemainingEvents() shouldBe emptyList()
        }

    }
}