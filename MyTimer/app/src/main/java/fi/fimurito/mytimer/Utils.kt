package fi.fimurito.mytimer

import android.util.Log
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
//import java.time.LocalDate
//import java.time.LocalDateTime
//import java.time.LocalTime

//import kotlin.math.floor


fun divBy(p1: Int, d1: Int): Int {
    val t1: Int = p1 - p1 % d1
    return t1
}
class Utils {
    companion object {
        fun taskTimer(): LocalDateTime {
            //val now = ZonedDateTime.now(ZoneId.systemDefault())
            val now = LocalDateTime.now()

            val nex = now
                .withMinute(divBy(now.minute, AppConstants.CURRENT_MINUTE_DIVISOR))
                .withSecond(0)
                .withNano(0)
            return nex
        }

        fun taskTimer(date: String): LocalDateTime? {
            try {
                val lt = LocalDateTime.parse(date)
                val tt = lt
                    .withMinute(divBy(lt.minute, AppConstants.CURRENT_MINUTE_DIVISOR))
                    .withSecond(0)
                    .withNano(0)
                return tt
            } catch (e: Exception) {
                Log.e(AppConstants.LOG_TAG, "taskTimer() function failed to convert timestamp (${date}) to datevalue: $e")
            }
            return null
        }

        fun taskTimer(st: LocalDateTime): LocalDateTime {
            val lt = st
                .withMinute(divBy(st.minute, AppConstants.CURRENT_MINUTE_DIVISOR))
                .withSecond(0)
                .withNano(0)

            return lt
        }

    }
}