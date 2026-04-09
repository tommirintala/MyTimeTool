package fi.fimurito.mytimer

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import fi.fimurito.mytimer.ui.theme.MyTimerTheme
import org.junit.Before
import org.junit.Ignore
import org.junit.Rule

class UserInputTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val activity by lazy { composeTestRule.activity}

    @Before
    fun setup() {
        // Launch screen
        composeTestRule.setContent {
            MyTimerTheme() {
                ConversationContent {
                    uiState = exampleUiState,
                    navigateToProfile = {},
                    onNavIconPressed = {},
                }
            }
        }
    }

    @Test
    @Ignore("Issue with keyobard sync https://issuetracker.google.com/169235317")
    fun sendButton_enableToggles() {
        findSendButton().assertIsNotEnabled()

        findTextInputField().performTextInput("Some text")

        findSendbutton().assertIsEnabled()
    }

    private fun findChangeTaskButton() = composeTestRule.onNodeWithText(activity.getString(R.string.button_change_task))
}