package fi.fimurito.mytimer.data

import fi.fimurito.mytimer.data.model.TaskLog
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime
import java.util.Date

class TaskLogRepository(private val dao: TaskLogDao) {
    suspend fun addNewTaskLog(
        tId: Long,
        bTime: LocalDateTime,
        eTime: LocalDateTime,
        logComment: String
    ) {
        dao.insertAll(
            /*
            TaskLog(
                taskId = tId,
                beginDate = bTime,
                endDate = eTime,
                comment = logComment
            )

             */
        )
    }

    //suspend fun getLastTask(): TaskRecord {
    //    return dao.getLastTaskR()
    //}

    suspend fun commentTaskEntry(taskEntry: Long, taskIdNum: Long, taskComment: String) {
        /*
        updateTaskLog(
            TaskLog(
                id = taskEntry,
                taskId = taskIdNum,
                comment = taskComment
            )
        )

         */
        /*
        dao.insertAll(
            TaskLog(
                taskId = taskIdNum,
                beginDate = startTime,
                endDate = endTime,
                comment = taskComment,
                modificationTime = LocalDateTime.now()
            )
        )
         */
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