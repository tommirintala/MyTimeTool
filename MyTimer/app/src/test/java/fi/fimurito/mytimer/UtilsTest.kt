package fi.fimurito.mytimer


import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import java.time.ZonedDateTime

class UtilsTest {
    @Test
    fun testTaskTimer() {
        val n = ZonedDateTime.now()
        val t = Utils.taskTimer()
        val m: Int = n.minute - n.minute % AppConstants.CURRENT_MINUTE_DIVISOR

        // Log.d(AppConstants.LOG_TAG, "time: ${t.hour}:${t.minute}:${t.second}")



        assertEquals("year test", n.year, t.year )
        assertEquals("month test", n.monthValue, t.monthValue)
        assertEquals("day of month test", n.dayOfMonth, t.dayOfMonth)
        assertEquals("hour test", n.hour, t.hour)
        assertEquals("minute test: $m = ${t.minute}", m, t.minute)
        assertEquals("second test", 0, t.second)
        assertNotEquals("", t.toString())
    }
}