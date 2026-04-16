package fi.fimurito.mytimer

import androidx.core.content.ContextCompat.getString
import androidx.room.Room
import fi.fimurito.mytimer.data.Task
import fi.fimurito.mytimer.data.TaskDatabase
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

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
        )
    }
    @org.junit.jupiter.api.Test
    fun getIdTest() {
        val id = task.getId()
        assertNotNull(id)
        assertNotEquals(0L, task.getId())
    }

    @org.junit.jupiter.api.Test
    fun getCodeTest() {
        assertEquals("101", task.code)
    }

    @org.junit.jupiter.api.Test
    fun getAbbrTest() {
        assertEquals("TSK1", task.abbr)
    }

    @org.junit.jupiter.api.Test
    fun getTitleTest() {
        assertEquals("Task #1", task.title)
    }

    @org.junit.jupiter.api.Test
    fun getCreationTimeTest() {
        assertNotNull(task.creationTime)
    }

    @org.junit.jupiter.api.Test
    fun getBeginTimeTest() {
        assertNotNull(task.beginTime)
    }

    @org.junit.jupiter.api.Test
    fun getEndTimeTest() {
        assertNotNull(task.endTime)
    }

    @org.junit.jupiter.api.Test
    fun copyTest() {
        val nn = task
        assertSame(task, nn)
    }

    @org.junit.jupiter.api.Test
    fun toStringTest() {
        assertNotNull(task.toString())
    }

    @org.junit.jupiter.api.Test
    fun hashCodeTest() {
        assertNotNull(task.hashCode())
    }

    @org.junit.jupiter.api.Test
    fun equalsTest() {
        val nn = task
        assertEquals(task, nn)
    }
}