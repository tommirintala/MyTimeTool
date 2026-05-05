package fi.fimurito.mytimer

//import androidx.core.content.ContextCompat.getString

import app.cash.turbine.test
import kotlin.collections.emptyList


// import org.junit.Test
//import org.junit.Rule
import org.junit.Test
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
//import org.junit.Assert.assertEquals
//import org.junit.jupiter.api.Test


import android.content.Context
import androidx.room.Room

import kotlinx.coroutines.test.runTest
//import org.bouncycastle.util.test.SimpleTest.runTest

//import org.mockito.Mock
//import org.mockito.junit.MockitoJUnitRunner
//import org.mockito.kotlin.doReturn
//import org.mockito.kotlin.mock

import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

import io.kotlintest.shouldBe


import fi.fimurito.mytimer.data.AppDatabase
import fi.fimurito.mytimer.data.model.Task
import fi.fimurito.mytimer.data.TaskDao
import java.time.LocalDateTime


private const val FAKE_CONTEXT = "FakeContext"

@RunWith(RobolectricTestRunner::class)
class TaskDBTest {


    private val context: Context by lazy { RuntimeEnvironment.getApplication() }

    private lateinit var subject: TaskDao
    private lateinit var db: AppDatabase

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java,
        ).build()
        subject = db.taskDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun `TaskDBTest testing basic DB operations`() = runTest {
        val items = (0L until 4L).map { id ->
            Task(
                id = id,
                remoteId = id + 100L,
                title = "title_$id",
                code = "code_$id",
                abbr = "abbr_$id",
                beginTime = LocalDateTime.now(),
                endTime = LocalDateTime.now().plusMinutes(AppConstants.CURRENT_MINUTE_DIVISOR.toLong()),
            )
        }

        subject.getAll().test {
            awaitItem() shouldBe emptyList()

            subject.insertAll(items[0])
            awaitItem() shouldBe listOf(items[0])

            subject.insertAll(items[1])
            awaitItem() shouldBe listOf(
                items[0],
                items[1])

            subject.insertAll(items[2], items[3])
            awaitItem() shouldBe listOf(
                items[0],
                items[1],
                items[2],
                items[3])

            cancelAndConsumeRemainingEvents() shouldBe emptyList()
        }
    }
}