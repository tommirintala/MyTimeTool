package fi.fimurito.mytimer.data.model

import java.time.LocalDateTime

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser(
    val userId: String,
    val displayName: String,
    val loginTime: LocalDateTime
)