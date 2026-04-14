package fi.fimurito.mytimer

import android.util.Log
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import kotlin.math.floor


fun divby(p1: Int, d1: Int): Int {
    val t1: Int = p1 - p1 % d1
    return t1
}
class Utils {
    companion object {
        fun taskTimer(): LocalDateTime {
            val now = LocalDateTime.now()
            //now.withMinute(( now.minute / AppConstants.CURRENT_MINUTE_DIVISOR) * AppConstants.CURRENT_MINUTE_DIVISOR)
            val nex = now
                .withMinute(divby(now.minute, AppConstants.CURRENT_MINUTE_DIVISOR))
                .withSecond(0)
                .withNano(0)
            // Log.d(AppConstants.LOG_TAG, "time: ${now.hour}:${now.minute}:${now.second}")
            return nex
        }

        fun taskTimer(date: String): LocalDateTime {
            val lt = LocalDateTime.parse(date)
            val tt = lt
                .withMinute(divby(lt.minute, AppConstants.CURRENT_MINUTE_DIVISOR))
                .withSecond(0)
                .withNano(0)
            return tt
        }

        fun taskTimer(st: LocalDateTime): LocalDateTime {
            val lt = st
                .withMinute(divby(st.minute, AppConstants.CURRENT_MINUTE_DIVISOR))
                .withSecond(0)
                .withNano(0)

            return lt
        }
    }
}