package fi.fimurito.mytimer

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


class DivbyTest {
    @Test
    fun divby_test() {
        for (i in 0..20) {
            assertEquals( 0, divby(i, 5))
        }
    }

}