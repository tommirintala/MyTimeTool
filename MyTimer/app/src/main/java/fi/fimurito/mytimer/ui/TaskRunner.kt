package fi.fimurito.mytimer.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.core.R

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults.InputField
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.remember
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
            Text(stringResource(R.string.label_started))
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