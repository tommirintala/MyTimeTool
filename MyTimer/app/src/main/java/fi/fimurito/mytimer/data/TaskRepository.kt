package fi.fimurito.mytimer.data

import kotlinx.coroutines.flow.Flow
import java.util.Date

class TaskRepository(private val dao: TaskDao) {
    suspend fun addNewTask(rId: Long,
                           code: String, title: String,
                           abbreviation: String,
                           cTime: Date, mTime: Date
    ) {
        dao.insertAll(Task(
            id = null,
            code = code,
            abbr = abbreviation,
            title = title,
            remoteId = rId,
            creationTime = cTime,
            modificationTime = mTime,
            beginTime = null,
            endTime = null,
        ))
    }

    suspend fun getLastTask(): Task {
        return dao.getLastTask()
    }

    suspend fun getAllTasks(): Flow<List<Task>> {
        return dao.getAll()
    }
}