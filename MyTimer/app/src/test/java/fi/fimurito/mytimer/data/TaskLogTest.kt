package fi.fimurito.mytimer.data

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class TaskLogTest {
    @Test
    fun getDuration() {
        val now = LocalDateTime.now()
        val tests = (1..102)
        tests.forEach { s ->
            val t = TaskLog(
                comment = "Test task",
                beginDate = now,
                endDate = now.plusMinutes(s.toLong())
            )

            assertEquals(s, t.getDuration())
        }

    }

}