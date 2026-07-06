package fi.fimurito.mytimer

import android.content.ContentValues
import android.database.sqlite.SQLiteOpenHelper
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import fi.fimurito.mytimer.data.model.Task
import fi.fimurito.mytimer.data.model.TaskLog
import java.time.LocalDateTime
import java.time.ZoneOffset

class DBHandler(context: Context?): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        when (DB_VERSION) {
            1 -> {
                var query = ("CREATE TABLE " + TASKDB_TABLE_NAME + " ("
                +TASKDB_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + TASKDB_REMOTE_ID_COL + " INTEGER, "
                +TASKDB_TITLE_COL + " TEXT, "
                +TASKDB_TAGS_COL + " TEXT, "
                +TASKDB_CODE_COL + " TEXT, "
                +TASKDB_ABBR_COL + " TEXT, "
                +TASKDB_MAXHOURS_COL + " FLOAT, "
                +TASKDB_SETHOURS_COL + " FLOAT, "
                +TASKDB_TASKTYPE_COL + " INTEGER, "
                +TASKDB_CREATED_AT_COL + " DATETIME, "
                +TASKDB_MODIFIED_AT_COL + " LONG, "
                +TASKDB_VALID_FROM_COL + " LONG, "
                +TASKDB_VALID_UNTIL_COL + " LONG, "
                +TASKDB_LAST_SYNC + " LONG"
                +")")
                db.execSQL(query)

                query = (
                        "CREATE TABLE " + LOGDB_TABLE_NAME + " ("
                        + LOGDB_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + LOGDB_TASK_COL + " LONG, "
                        + LOGDB_STARTED_COL + " LONG, "
                        + LOGDB_ENDED_COL + " LONG, "
                        + LOGDB_COMMENT_COL + " TEXT"
                        + ")"
                        )
                db.execSQL(query)

                query = (
                        "CREATE TABLE " + SYNCDB_TABLE_NAME + " ("
                        + SYNCDB_URL + " TEXT,"
                        + SYNCDB_TIMESTAMP_COL + " LONG,"
                        + SYNCDB_STATUS_COL + " INTEGER"
                        + ")"
                        )
                db.execSQL(query)
            }
            else -> {
                println("Unknown Database version requested: $DB_VERSION")
            }
        }
    }

    fun addNewTask(
        task: Task?
    ) {
        if (task != null) {
            val db = this.writableDatabase
            val values = ContentValues()

            values.put(TASKDB_TITLE_COL, task.title)
            values.put(TASKDB_TAGS_COL, task.tags)
            values.put(TASKDB_CODE_COL, task.code)
            values.put(TASKDB_ABBR_COL, task.abbr)
            values.put(TASKDB_MAXHOURS_COL, task.maxHours)
            values.put(TASKDB_SETHOURS_COL, task.setHours)
            values.put(TASKDB_TASKTYPE_COL, task.taskType)
            values.put(TASKDB_VALID_FROM_COL, task.validFrom?.toEpochSecond(ZoneOffset.UTC))
            values.put(TASKDB_VALID_UNTIL_COL, task.validUntil?.toEpochSecond(ZoneOffset.UTC))
            values.put(TASKDB_CREATED_AT_COL, task.creationTime.toEpochSecond(ZoneOffset.UTC))
            values.put(TASKDB_MODIFIED_AT_COL, task.modificationTime.toEpochSecond(ZoneOffset.UTC))
            values.put(TASKDB_LAST_SYNC, task.lastSync?.toEpochSecond(ZoneOffset.UTC))
            db.insert(TASKDB_TABLE_NAME, null, values)
            db.close()
        }
    }



    fun getTasks(): MutableList<Task> {
        val result = mutableListOf<Task>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TASKDB_TABLE_NAME", null)
        cursor.use {
            if (cursor.moveToFirst()) {
                do {
                    val r = Task(
                        cursor.getInt(cursor.getColumnIndexOrThrow(TASKDB_ID_COL)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(TASKDB_REMOTE_ID_COL)),
                        cursor.getString(cursor.getColumnIndexOrThrow(TASKDB_TITLE_COL)),
                        cursor.getString(cursor.getColumnIndexOrThrow(TASKDB_TAGS_COL)),
                        cursor.getString(cursor.getColumnIndexOrThrow(TASKDB_CODE_COL)),
                        cursor.getString(cursor.getColumnIndexOrThrow(TASKDB_ABBR_COL)),
                        cursor.getFloat(cursor.getColumnIndexOrThrow(TASKDB_MAXHOURS_COL)),
                        cursor.getFloat(cursor.getColumnIndexOrThrow(TASKDB_SETHOURS_COL)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(TASKDB_TASKTYPE_COL)),
                        LocalDateTime.ofEpochSecond( cursor.getLong(cursor.getColumnIndexOrThrow(TASKDB_VALID_FROM_COL)), 0, ZoneOffset.UTC),
                        LocalDateTime.ofEpochSecond(cursor.getLong(cursor.getColumnIndexOrThrow(TASKDB_VALID_UNTIL_COL)), 0, ZoneOffset.UTC),
                        LocalDateTime.ofEpochSecond(cursor.getLong(cursor.getColumnIndexOrThrow(TASKDB_LAST_SYNC)), 0, ZoneOffset.UTC),
                        LocalDateTime.ofEpochSecond(cursor.getLong(cursor.getColumnIndexOrThrow(TASKDB_CREATED_AT_COL)), 0, ZoneOffset.UTC),
                        LocalDateTime.ofEpochSecond(cursor.getLong(cursor.getColumnIndexOrThrow(TASKDB_MODIFIED_AT_COL)), 0, ZoneOffset.UTC)
                    )
                    result.add(r)
                } while (cursor.moveToNext())
            }
        }
        return result
    }

    fun addLog(log: TaskLog?) {
        if (log != null) {
            val db = this.writableDatabase
            val values = ContentValues()
            values.put(LOGDB_TASK_COL, log.taskId)
            values.put(LOGDB_STARTED_COL, log.beginDate?.toEpochSecond(ZoneOffset.UTC))
            values.put(LOGDB_ENDED_COL, log.endDate?.toEpochSecond(ZoneOffset.UTC))
            values.put(LOGDB_COMMENT_COL, log.comment)
            db.insert(LOGDB_TABLE_NAME, null, values)
            db.close()
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        println("database upgrade from $oldVersion to $newVersion")
        db.execSQL("DROP TABLE IF EXISTS $TASKDB_TABLE_NAME")
        db.execSQL("DROP TABLE IF EXISTS $LOGDB_TABLE_NAME")
        db.execSQL("DROP TABLE IF EXISTS $SYNCDB_TABLE_NAME")
        onCreate(db)
    }
    companion object {
        private const val DB_NAME = "taskdb"

        private const val DB_VERSION = 1
        private const val TASKDB_TABLE_NAME = "tasks"
        private const val TASKDB_ID_COL = "id"
        private const val TASKDB_REMOTE_ID_COL = "remote_id"
        private const val TASKDB_TITLE_COL = "title"
        private const val TASKDB_TAGS_COL = "tags"
        private const val TASKDB_CODE_COL = "code"
        private const val TASKDB_ABBR_COL = "abbr"
        private const val TASKDB_MAXHOURS_COL = "max_hours"
        private const val TASKDB_SETHOURS_COL = "set_hours"
        private const val TASKDB_TASKTYPE_COL = "tasktype"
        private const val TASKDB_CREATED_AT_COL = "created_at"
        private const val TASKDB_MODIFIED_AT_COL = "modified_at"
        private const val TASKDB_VALID_FROM_COL = "valid_from"
        private const val TASKDB_VALID_UNTIL_COL = "valid_until"
        private const val TASKDB_LAST_SYNC = "last_sync"


        private const val LOGDB_TABLE_NAME = "tasklog"
        private const val LOGDB_ID_COL = "id"
        private const val LOGDB_TASK_COL = "taskid"
        private const val LOGDB_STARTED_COL = "started"
        private const val LOGDB_ENDED_COL = "ended"
        private const val LOGDB_COMMENT_COL = "comment"
        private const val LOGDB_LAST_SYNC = "last_sync"


        private const val SYNCDB_TABLE_NAME = "syncdb"
        private const val SYNCDB_URL = "syncurl"
        private const val SYNCDB_TIMESTAMP_COL = "synctime"
        private const val SYNCDB_STATUS_COL = "status"
    }
}