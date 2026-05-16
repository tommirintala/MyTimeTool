package fi.fimurito.mytimer.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.LazyPagingItems
import fi.fimurito.mytimer.MainViewModel
import fi.fimurito.mytimer.R
import fi.fimurito.mytimer.ui.theme.MyTimerTheme

@Composable
fun MainScreen(tasks: LazyPagingItems<Task>, mainViewModel, modifier: Modifier = Modifier) {
    val focusManager = LocalFocusManager.current

    Scaffold(topBar = {
        TopAppBar(
            backgroundColor = MaterialTheme.colorScheme.background
        ) {
            SearchView(
                query = mainViewModel.query.value,
                onQueryChanged = { newQuery ->
                    mainViewModel.setQuery(newQuery)
                },
                onSearch = {
                    mainViewModel.invalidateDataSource()
                    focusManager.clearFocus()
                },
                onClearQuery = {
                    mainViewModel.setQuery("")
                    mainViewModel.invalidateDataSource()
                },
                modifier = Modifier
                    .fillMaxWidth(),
                .background(
                    color= MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(8.dp)
                )
            )
        }
    }) { padding ->
        when (tasks.loadState.refresh) {
            LoadState.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            }
            LoadState.Error -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text( text = stringResource(R.string.something_went_wrong))
                }
            }
            else -> {
                LazyColumn(modifier = modifier.padding(padding)) {
                    itemsIndexed(tasks) { index, item ->
                        item?.let {
                            TaskItem(
                                task = item,
                                modifer = Modifier
                                    .fillMaxWidth()
                                    .background(getBackgroundForIndex(index))
                                    .padding(vertical = 15.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

fun getBackgroundForIndex(index: Int) =
    if (index % 2 == 0) MaterialTheme.colorScheme.primaryContainer as Color
else MaterialTheme.colorScheme.secondaryContainer as Color

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyTimerTheme {
        MainScreen(
            flowOf(
                pagingData.from(
                    listOf(
                        Task()
                    )
                )
            ).collectAsLazyPagingItems()
            viewModel(modelClass = MainViewModel::class.java)
        )
    }
}