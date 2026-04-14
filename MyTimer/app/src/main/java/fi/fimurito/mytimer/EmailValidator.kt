package fi.fimurito.mytimer

class EmailValidator {
    fun isValidEmail(addr: String): Boolean {
        val parts = addr.split("@")
        return parts.size == 2
    }

    fun getName(): String {
        return "EmailValidatorName"
    }
}