package fi.fimurito.mytimer


import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class AppConstantsTest {

    @Test
    fun testDefaultMinuteDivisor() {
        assertNotNull(AppConstants.DEFAULT_MINUTE_DIVISOR)
        assertNotEquals(AppConstants.DEFAULT_MINUTE_DIVISOR, 0)

    }

    @Test
    fun testDefaultTaskLength() {
        assertNotNull(AppConstants.DEFAULT_TASK_INCREMENT_LENGTH_MINUTES)
        assertNotEquals(AppConstants.DEFAULT_TASK_INCREMENT_LENGTH_MINUTES, 0)

    }
}