package fi.fimurito.mytimer.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import fi.fimurito.mytimer.R
import fi.fimurito.mytimer.data.model.Task

@Composable
fun TaskRunner(modifier: Modifier = Modifier) {
    var currentTask = remember<Task> { Task() }
    Column(
    ) {
        Row() {
            Text(stringResource(R.string.label_task_id))
            Text("#number")
        }
        Row() {
            Text(stringResource(R.string.label_task))
            Text("?title")
        }
    }
    Column() {
        Row() {
            Text(stringResource(R.string.label_date_begin))
            Text("?Begin")
        }
    }
    Column() {
        Row() {
            Text(stringResource(R.string.label_hours_cumulative))
            Text("? 42,3h")
        }
    }
}