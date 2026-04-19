package fi.fimurito.mytimer


import fi.fimurito.mytimer.data.Task
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime
import java.util.Date

// import org.junit.jupiter.api.Assertions.*

class TaskTest {
    lateinit var task: Task
    @Before
    fun setup() {
        task = Task(
            id = 1,
            abbr = "TSK1",
            title = "Task #1",
            remoteId = -1L,
            code = "101",
            beginTime = LocalDateTime.now(),
            endTime = LocalDateTime.now().plusMinutes(15),
        )
    }
    @Test
    fun getIdTest() {
        val id = task.getId()
        assertNotNull(id)
        assertNotEquals(0L, task.getId())
    }

    @Test
    fun getCodeTest() {
        assertEquals("101", task.code)
    }

    @Test
    fun getAbbrTest() {
        assertEquals("TSK1", task.abbr)
    }

    @Test
    fun getTitleTest() {
        assertEquals("Task #1", task.title)
    }

    @Test
    fun getCreationTimeTest() {
        assertNotNull(task.creationTime)
    }

    @Test
    fun getBeginTimeTest() {
        assertNotNull(task.beginTime)
    }

    @Test
    fun getEndTimeTest() {
        assertNotNull(task.endTime)
    }

    @Test
    fun copyTest() {
        val nn = task
        assertEquals(task, nn)
    }

    @Test
    fun toStringTest() {
        assertNotNull(task.toString())
    }

    @Test
    fun hashCodeTest() {
        assertNotNull(task.hashCode())
    }

    @Test
    fun equalsTest() {
        val nn = task
        assertEquals(task, nn)
    }
}