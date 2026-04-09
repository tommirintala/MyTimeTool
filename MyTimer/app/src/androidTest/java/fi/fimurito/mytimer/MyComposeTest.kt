package fi.fimurito.mytimer

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import fi.fimurito.mytimer.ui.theme.MyTimerTheme
import org.junit.Rule
import org.junit.Test

class MyComposeTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun myTest() {
        composeTestRule.setContent {
            MyTimerTheme {
                MainViewModel(uiState = fakeUiState, /* ... */)
            }
        }

        composeTestRule.onNodeWithText("Switch").performClick()
    }
}