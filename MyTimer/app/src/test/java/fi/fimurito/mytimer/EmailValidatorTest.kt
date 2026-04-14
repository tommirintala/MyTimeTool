package fi.fimurito.mytimer

import org.junit.Assert.assertTrue
import org.junit.Test

import fi.fimurito.mytimer.EmailValidator

class EmailValidatorTest {
    @Test
    fun emailValidator_CorrectEmailSimple_ReturnsTrue() {
        val validator = EmailValidator()
        assertTrue(validator.isValidEmail("john@localhost"))
    }
}