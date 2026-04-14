package fi.fimurito.mytimer

import android.content.Context
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

private const val FAKE_STRING = "Hello world!"

class MainActivityTest {
    @Test
    fun onCreate() {
        val mockContext = mock<Context> {
            on { getString(R.string.app_name)} doReturn FAKE_STRING
        }

        val obj = MainActivity()

        assertNotNull(obj)
    }

}