package fi.fimurito.mytimer.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.room.util.TableInfo
import fi.fimurito.mytimer.data.model.Task

@Composable
fun TaskItem(
    task: Task,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier, verticalAlignment = Alignment.Top) {
        Spacer(modifier = Modifier.width(15.dp))

        val thumbnail = task.taskType?.imageLinks?.thumbnail?.replaceFirst("http:", "https:")
        Column(
            Modifier
                .fillMaxHeight()
                .weight(0.2f)
        )
    }
}