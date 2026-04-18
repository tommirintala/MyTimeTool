package fi.fimurito.mytimer.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [TaskLog::class, Task::class], version = 1, exportSchema = true)
@TypeConverters(MyTypeConverters::class)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskLogDao() : TaskLogDao
    abstract fun taskDao(): TaskDao
}