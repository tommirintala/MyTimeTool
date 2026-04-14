package fi.fimurito.mytimer.data

import android.content.Context
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockedConstruction
import org.mockito.Mockito.mock
import org.mockito.kotlin.mock
import java.lang.reflect.Constructor

class PrefsTest {

    @Mock
    private lateinit var mockContext: Context
    private lateinit var prefs: Prefs


    @BeforeEach
    fun setUp() {
        val mockContext = mock<Context> {
            // on { getString("FOO") } doReturn "BAR"
        }
        val prefs = Prefs(mockContext)
    }

    @AfterEach
    fun tearDown() {
        TODO("Not yet implemented")
    }

    @Test
    fun getCurrentMinuteDivisor() {
        assertNotEquals(0,prefs.getCurrentMinuteDivisor())
        assertNotEquals(null,prefs.getCurrentMinuteDivisor())
    }

    @Test
    fun setCurrentMinuteDivisor() {
        // should not be zero
        val preVal = prefs.getCurrentMinuteDivisor()
        assertNotEquals(0, preVal)

        // test for addition
        prefs.setCurrentMinuteDivisor(preVal + 5)
        assertEquals(preVal + 5, prefs.getCurrentMinuteDivisor())

        // should not accept negative values
        prefs.setCurrentMinuteDivisor(-1)
        assertNotEquals(-1, prefs.getCurrentMinuteDivisor())

    }

    @Test
    fun getCurrentTaskLength() {
        var preVal = prefs.getCurrentTaskLength()
        assertNotEquals(0, preVal)

        // test for addition
        prefs.setCurrentMinuteDivisor(preVal + 5)
        assertEquals(preVal + 5, prefs.getCurrentMinuteDivisor())

        // should not accept negative values
        preVal = prefs.getCurrentTaskLength()
        prefs.setCurrentMinuteDivisor(-1)
        assertNotEquals(-1,prefs.getCurrentMinuteDivisor())
        assertEquals(preVal, prefs.getCurrentTaskLength())
    }

}