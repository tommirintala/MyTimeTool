package fi.fimurito.mytimer.data

import kotlinx.coroutines.flow.Flow
import java.util.Date

class TaskLogRepository(private val dao: TaskLogDao) {
    suspend fun addNewTaskLog(
                           tId: Long, bTime: Date, eTime: Date,
                           logComment: String
    ) {
        dao.insertAll(TaskLog(
            taskId = tId,
            beginDate = bTime,
            endDate = eTime,
            comment = logComment
        ))
    }

    //suspend fun getLastTask(): TaskRecord {
    //    return dao.getLastTaskR()
    //}

    suspend fun commitTaskEntry(taskIdNum: Long, startTime: Date, endTime: Date, taskComment: String) {
        dao.insertAll(TaskLog(
            taskId = taskIdNum,
            beginDate = startTime,
            endDate = endTime,
            comment = taskComment,
            modificationTime = Date()
        ))
    }

    suspend fun updateTaskLog(record: TaskLog) {
        record.modificationTime
        dao.updateTaskLog(record )
    }

    suspend fun getAllTasks(): Flow<List<TaskLog>> {
        return dao.getAll()
    }

    suspend fun deleteAll() {
        val flow = dao.getAll()
        flow.collect {
            list -> list.forEach { dao.delete( it ) }
        }
    }
}