package fi.fimurito.mytimer.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import java.util.Date

class TaskRecordRepository(private val dao: TaskRecordDao) {
    suspend fun addNewTaskRecord(
                           tId: Long, bTime: Date, eTime: Date,
                           recordComment: String
    ) {
        dao.insertAll(TaskRecord(
            taskId = tId,
            beginDate = bTime,
            endDate = eTime,
            comment = recordComment
        ))
    }

    //suspend fun getLastTask(): TaskRecord {
    //    return dao.getLastTaskR()
    //}

    suspend fun commitTaskEntry(taskIdNum: Long, startTime: Date, endTime: Date, taskComment: String) {
        dao.insertAll(TaskRecord(
            taskId = taskIdNum,
            beginDate = startTime,
            endDate = endTime,
            comment = taskComment,
            modificationTime = Date()
        ))
    }

    suspend fun updateTaskRecord(record: TaskRecord) {
        record.modificationTime
        dao.updateTaskRecord(record )
    }

    suspend fun getAllTasks(): Flow<List<TaskRecord>> {
        return dao.getAll()
    }

    suspend fun deleteAll() {
        val flow = dao.getAll()
        flow.collect {
            list -> list.forEach { dao.delete( it ) }
        }
    }
}