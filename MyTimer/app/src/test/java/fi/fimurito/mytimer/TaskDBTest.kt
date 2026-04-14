package fi.fimurito.mytimer

import androidx.core.content.ContextCompat.getString
import androidx.room.Room
import fi.fimurito.mytimer.data.TaskDatabase
import fi.fimurito.mytimer.data.Task
import org.junit.Test

import kotlin.collections.emptyList

import android.content.Context
import fi.fimurito.mytimer.data.TaskDao
import org.bouncycastle.util.test.SimpleTest.runTest
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment



private const val FAKE_CONTEXT = "FakeContext"

@RunWith(RobolectricTestRunner::class)
class TaskDBTest {


    private val context: Context by lazy { RuntimeEnvironment.getApplication() }

    private lateinit var subject: TaskDao
    private lateinit var db: TaskDatabase

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            context,
            TaskDatabase::class.java,
        ).build()
        subject = db.taskDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun `verify inserting data flow`() = runTest {
        val items = (0 until 4).map { id ->
            Task(
                id = $id,
                title = "title_$id",
                code = "code_$id",
                abbr = "abbr_$id",
            )
        }

        subject.getAll().test {
            awaitItem() shouldBe emptyList()

            subject.insertAll(items[0])
            awaitItem() shouldBe listOf(items[0])

            subject.insertAll(items[1])
            awaitItem() shouldBe listOf(items[0], items[1])

            subject.insertAll(items[2], items[3])
            awaitItem() shouldBe listOf(items[0], items[1],
                items[2], items[3])

            cancelAndConsumeRemainingEvents() shouldBe emptyList()
        }
    }
}