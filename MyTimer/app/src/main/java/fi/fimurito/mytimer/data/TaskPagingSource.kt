package fi.fimurito.mytimer.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import fi.fimurito.mytimer.data.model.Task

class TaskPagingSource(
    private val query: String,
    private val repo: TaskRepository
) : PagingSource<Int, Task>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Task> {
        return try {
            if (query.isEmpty()) return LoadResult.Page(data = emptyList(), null, null)
            val nextPageNumber = params.key ?: 1
            val response = repo.getTasks(query, nextPageNumber, params.loadSize)

            LoadResult.Page(
                data = response,
                prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                nextKey = if (response.isEmpty()) null else nextPageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Task>): Int =
        ((state.anchorPosition ?: 0) - state.config.initialLoadSize / 2)
            .coerceAtLeast(0)
}