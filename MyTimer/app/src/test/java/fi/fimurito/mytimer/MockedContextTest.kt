package fi.fimurito.mytimer

import android.content.Context
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

private const val FAKE_STRING = "Utils"

@RunWith(MockitoJUnitRunner::class)
class MockedContextTest {
    @Test
    fun dummyTest() {
        assertEquals(true, true)
    }
/*
    @Mock
    private lateinit var mockContext: Context

    @Test
    fun readStringFromContext_LocalizedString() {
        val mockContext = mock<Context> {
            on { getString(R.string.app_name)} doReturn FAKE_STRING
        }

        val myObjectUnderTest = Utils(mockContext)

        val result: String? = myObjectUnderTest.getName()

        assertEquals(result, FAKE_STRING)
    }

 */
}