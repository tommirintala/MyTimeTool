package fi.fimurito.mytimer.data

import fi.fimurito.mytimer.data.model.Task
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime
// import java.util.Date

class TaskRepository(private val dao: TaskDao) {
    suspend fun addNewTask(rId: Long,
                           code: String, title: String,
                           abbreviation: String,
                           cTime: LocalDateTime, mTime: LocalDateTime
    ) {
        dao.insertAll(
            Task(
                id = null,
                code = code,
                abbr = abbreviation,
                title = title,
                remoteId = rId,
                creationTime = cTime,
                modificationTime = mTime,
                beginTime = null,
                endTime = null,
            )
        )
    }

    suspend fun getLastTask(): Task {
        return dao.getLastTask()
    }

    suspend fun getAllTasks(): Flow<List<Task>> {
        return dao.getAll()
    }
}