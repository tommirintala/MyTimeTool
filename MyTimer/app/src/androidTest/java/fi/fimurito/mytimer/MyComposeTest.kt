package fi.fimurito.mytimer

import app.cash.turbine.test
import app.cash.turbine.awaitItem

import androidx.compose.ui.test.junit4.createComposeRule

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class MyComposeTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun myTest() = runTest {
        // Fake state
        val fakeUiState = MyUiState(isLoading = true, data = "Fake Data")

        // set state to ViewModel
        val viewModel = MainViewModel()

        viewModel.uiState.test {
            assertEquals(MyUiState(), awaitItem())

            // run action
            viewModel.fetchData()

            // wait for state
            val result = awaitItem()
            assertEquals(true, result.isLoading)
            assertEquals("Fake Data", result.data)

            cancelAndIgnoreRemainingEvents()
        }
        /*
        composeTestRule.setContent {
            MyTimerTheme {
                MainViewModel(uiState = fakeUiState, /* ... */)
            }
        }

        composeTestRule.onNodeWithText("Switch").performClick()
        */

    }
}