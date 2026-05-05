package fi.fimurito.mytimer

object AppConstants {
    const val LOG_TAG = "MyTimer"
    const val DATABASE_FILENAME = "mytimer.db"

    const val NAME_MINUTE_DIVISOR = "minute_divisor_value"
    const val NAME_TASK_DEFAULT_LENGTH = "task_default_length_value"
    const val DEFAULT_MINUTE_DIVISOR = 5L
    const val DEFAULT_TASK_INCREMENT_LENGTH_MINUTES = 15L
    const val DEFAULT_SAVE_TO_CLOUD = false
    var CURRENT_MINUTE_DIVISOR = DEFAULT_MINUTE_DIVISOR
    var CURRENT_TASK_LENGTH = DEFAULT_TASK_INCREMENT_LENGTH_MINUTES
}