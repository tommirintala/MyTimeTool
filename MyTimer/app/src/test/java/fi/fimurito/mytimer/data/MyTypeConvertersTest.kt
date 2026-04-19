package fi.fimurito.mytimer.data

import androidx.room.TypeConverter
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.util.Date

class MyTypeConvertersTest {
    @Test
    fun fromTimestamp() {
        val testClass = MyTypeConverters()
        val testAry = mapOf<Long?, LocalDateTime>(
            null to LocalDateTime.now(),
            0L to LocalDateTime.now(),
            Date("2025-12-31 00:00:00").time to LocalDateTime.of(2025, 12, 31, 0, 0, 0)
        )

        testAry.forEach { (input, expected) ->
            assertEquals(expected, testClass.fromTimestamp(input))
        }
    }

    @Test
    fun localDateTimeToTimestamp() {
        val testClass = MyTypeConverters()
        val testAry = mapOf<LocalDateTime?, Long?>(
            null to null,
        )

        testAry.forEach { (input, expected) ->
            assertEquals(expected, testClass.localDateTimeToTimestamp(input))
        }
    }

}