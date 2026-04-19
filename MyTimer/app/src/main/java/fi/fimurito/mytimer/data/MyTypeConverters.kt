package fi.fimurito.mytimer.data

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.util.Date

class MyTypeConverters {
    /*
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

    @TypeConverter
    fun localDateTimeToTimestamp(date: LocalDateTime?): Long? {
        return date?.toEpochSecond(ZoneOffset.UTC)
    }
*/
    @TypeConverter
    fun fromTimestamp(value: Long?): LocalDateTime? {
        val dd = value?.let {
            Date(it).toInstant()
        }
        val ret = dd?.let {
            LocalDateTime.ofInstant(dd, ZoneId.systemDefault())
        }
        return ret
    }

    @TypeConverter
    fun localDateTimeToTimestamp(date: LocalDateTime?): Long? {
        return date?.toEpochSecond(ZoneOffset.UTC)
    }


}