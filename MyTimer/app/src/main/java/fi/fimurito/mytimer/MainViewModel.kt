package fi.fimurito.mytimer

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class MyUiState (
    val isLoading: Boolean = false,
            val data: String = ""
)
class MainViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(MyUiState())
    val uiState: StateFlow<MyUiState> = _uiState.asStateFlow()

    fun fetchData() {
        _uiState.value = MyUiState(isLoading = true)
    }
}