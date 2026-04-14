package fi.fimurito.mytimer.data

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.provider.SyncStateContract
import android.util.Log
import fi.fimurito.mytimer.AppConstants
import fi.fimurito.mytimer.R
import androidx.core.content.edit

private const val PREF_FILE_NAME :String = "UserPreferences"
private const val PREF_MINUTE_DIVISOR : String = "minute_divisor_value"
private const val PREF_TASK_DEFAULT_LENGTH:String = "task_default_length"
private const val PREF_SAVE_CLOUD = "pref_save_data_to_cloud"

class Prefs(val context: Context) {

    val sharedPrefs : SharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, android.content.Context.MODE_PRIVATE)

    fun readAll() {
        getCurrentMinuteDivisor()
        getCurrentTaskLength()
        getSaveToCloud()
    }

    fun getCurrentMinuteDivisor(): Int {
        AppConstants.CURRENT_MINUTE_DIVISOR = sharedPrefs.getInt(PREF_MINUTE_DIVISOR, AppConstants.DEFAULT_MINUTE_DIVISOR)
        return AppConstants.CURRENT_MINUTE_DIVISOR
    }

    fun setCurrentMinuteDivisor(div: Int): Boolean {
        if (div <= 0)
            return false
        sharedPrefs.edit {
            putInt(PREF_MINUTE_DIVISOR, div)
        }
        AppConstants.CURRENT_MINUTE_DIVISOR = div
        Log.d(AppConstants.LOG_TAG, "config: minute divisor = $div")
        return true
    }

    fun getCurrentTaskLength(): Int {
        AppConstants.CURRENT_TASK_LENGTH = sharedPrefs.getInt(
            PREF_TASK_DEFAULT_LENGTH,
            AppConstants.DEFAULT_TASK_MINUTE_LENGTH)
        return AppConstants.CURRENT_TASK_LENGTH
    }

    fun setCurrentTaskLength(len: Int): Boolean {
        if (len <= 0)
            return false

        sharedPrefs.edit {
            putInt(PREF_TASK_DEFAULT_LENGTH, len)
        }
        Log.d(AppConstants.LOG_TAG, "config: task length = $len")
        return true
    }

    fun getSaveToCloud(): Boolean {
        return sharedPrefs.getBoolean(PREF_SAVE_CLOUD, AppConstants.DEFAULT_SAVE_TO_CLOUD)
    }

    fun setSaveToCloud(saveToCloud: Boolean): Boolean {
        sharedPrefs.edit {
            putBoolean(PREF_SAVE_CLOUD, saveToCloud)
        }
        return saveToCloud
    }
}
