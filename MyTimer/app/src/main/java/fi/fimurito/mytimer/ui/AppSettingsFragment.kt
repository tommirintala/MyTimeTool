package fi.fimurito.mytimer.ui

import android.os.Bundle
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.preference.PreferenceFragmentCompat
import fi.fimurito.mytimer.R


class AppSettingsFragment: PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }
}