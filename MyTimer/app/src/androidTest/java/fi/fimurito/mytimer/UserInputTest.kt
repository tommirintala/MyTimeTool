package fi.fimurito.mytimer

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.core.content.ContextCompat.getString
import fi.fimurito.mytimer.ui.theme.MyTimerTheme
import org.junit.Before
import org.junit.Ignore
import org.junit.Rule
import org.junit.jupiter.api.Test

class UserInputTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val activity by lazy { composeTestRule.activity}

    @Before
    fun setup() {
        // Launch screen
        composeTestRule.setContent {
            MyTimerTheme() {
                /*
                ConversationContent {
                    uiState = exampleUiState,
                    navigateToProfile = {},
                    onNavIconPressed = {},
                }

                 */
            }
        }
    }

    @Test
    @Ignore("Issue with keyobard sync https://issuetracker.google.com/169235317")
    fun sendButton_enableToggles() {
        findSendButton().assertIsNotEnabled()

        findTextInputField().performTextInput("Some text")

        findSendButton().assertIsEnabled()
    }

    private fun findChangeTaskButton() = composeTestRule.onNodeWithText(activity.getString(R.string.button_change_task))
    private fun findSendButton() = composeTestRule.onNodeWithText("Send")
    private fun findTextInputField() = composeTestRule.onNodeWithText("INput")
}