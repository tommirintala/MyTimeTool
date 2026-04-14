package fi.fimurito.mytimer

object AppConstants {
    const val LOG_TAG = "MyTimer"

    const val NAME_MINUTE_DIVISOR = "minute_divisor_value"
    const val NAME_TASK_DEFAULT_LENGTH = "task_default_length_value"
    const val DEFAULT_MINUTE_DIVISOR = 5
    const val DEFAULT_TASK_MINUTE_LENGTH = 15
    const val DEFAULT_SAVE_TO_CLOUD = false
    var CURRENT_MINUTE_DIVISOR: Int = DEFAULT_MINUTE_DIVISOR
    var CURRENT_TASK_LENGTH: Int = DEFAULT_TASK_MINUTE_LENGTH
}