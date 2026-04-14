package fi.fimurito.mytimer.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TaskRecord::class, Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskRecordDao() : TaskRecordDao
    abstract fun taskDao(): TaskDao
}