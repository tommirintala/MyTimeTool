package fi.fimurito.mytimer


import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.mockito.MockedConstruction
import java.lang.reflect.Constructor

class AppConstantsTest {

    @Test
    fun testDefaultMinuteDivisor() {
        assertNotNull(AppConstants.DEFAULT_MINUTE_DIVISOR)
        assertNotEquals(AppConstants.DEFAULT_MINUTE_DIVISOR, 0)

    }

    @Test
    fun testDefaultTaskLength() {
        assertNotNull(AppConstants.DEFAULT_TASK_MINUTE_LENGTH)
        assertNotEquals(AppConstants.DEFAULT_TASK_MINUTE_LENGTH, 0)

    }
}