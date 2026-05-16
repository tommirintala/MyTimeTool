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
                data = response.items,
                prevKey = params.prevKey(),
                nextKey = params.nextKey(response.totalItems)
            )
        } catch (e: Exception) {
            LoadResult(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Task>): Int =
        ((state.anchorPosition ?: 0) - state.config.initialLoadSize / 2)
            .coerceAtLeast(0)
}