package fi.fimurito.mytimer

import android.os.Bundle
import android.text.style.IconMarginSpan
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedTextField

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getString
import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import fi.fimurito.mytimer.ui.theme.MyTimerTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


const val logPrefix = "MyTimer"
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(logPrefix, "Loading app")
        enableEdgeToEdge()
        setContent {
            MyTimerTheme {
                MyTimerApp()
            }
        }
    }
}

class MyTask
{
    private var myTask = "Default Task"
    fun getValue(): String {
        return myTask
    }
    fun setValue(task: String) {
        myTask = task
    }
}

/*
@OptIn(ExperimentalTextApi::class)
val displayLargeFontFamily =
    FontFamily(
        Font(
            R.font.lato_black,
            variationSettings = FontVariation.Settings(
                FontVariation.width(30f),
                FontVariation.weight(950),
                FontVariation.slant(-6f)
            )
        )
    )
*/

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

@PreviewScreenSizes
@Composable
fun MyTimerApp() {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }

    //var currentTask by rememberSaveable( mutableStateOf(MyTask)) { }

    Log.d(logPrefix, "Creating UIX")

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach {
                item(
                    icon = {
                        Icon(
                            painterResource(it.icon),
                            contentDescription = it.label
                        )
                    },
                    label = { Text(it.label) },
                    selected = it == currentDestination,
                    onClick = { currentDestination = it }
                )
            }
        }
    ) {

        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Greeting(
                name = "Android",
                modifier = Modifier.padding(innerPadding)
            )

            TaskSwitcher()
            //TaskFinisher()
        }
    }


}

enum class AppDestinations(
    val label: String,
    val icon: Int,
) {
    HOME("HOME", R.drawable.ic_home),
    FAVORITES("Favorites", R.drawable.ic_favorite),
    PROFILE("Profile", R.drawable.ic_account_box),
    LOGIN("Login", R.drawable.ic_login),
    RELOAD("Reload", R.drawable.ic_reload),
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyTimerTheme {
        Greeting("Android")
    }
}

@Preview(showBackground=true)
@Composable
fun TaskSwitcher() {
    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()) {
        Row() {
            var task by remember { mutableStateOf("") }
            /*
            if (task.isNotEmpty()) {
                Text(
                    text = "Task",
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
*/
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(1f),
                value = task,
                onValueChange = { task = it },
                label = { Text("Current Task") }
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(
                onClick = {
                    Log.d(logPrefix, "Button: Continue Task")
                },
                modifier = Modifier.fillMaxWidth(.5f),
            ) {
                Text(stringResource(R.string.button_continue_task))
            }
            /*Button(
                onClick = {
                    Log.d(logPrefix, "Button: Switch task")
                },
                modifier = Modifier.fillMaxWidth(.5f),
            ) {
                Text("Switch")
            }*/
            LongTaskDropdown()
        }

        Row()
        {
            FilterTextView()
        }

        Row(verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(1f),
                onClick = {
                    Log.d(logPrefix, "Button: Task end clicked")
                }
            ) {
                Text(stringResource(R.string.button_end_task))
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TaskFinisherField() {
    var logMessage by rememberSaveable { mutableStateOf("") }
    var isShown by remember { mutableStateOf(false) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            modifier = Modifier.fillMaxWidth(1f),
            onClick = {}
        ) { }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(1f),
            value = logMessage,
            onValueChange = { logMessage = it },
            label = { Text(stringResource(R.string.label_log_message)) }
        )
    }
}

@Composable
fun AlertDialogExample(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
    AlertDialog(
        icon = {
            Icon(icon, contentDescription = "Example Icon")
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(stringResource(R.string.button_confirm))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(stringResource(R.string.button_dismiss))
            }
        }
    )
}

// [START android_compose_text_filtertextviewmodel]
class FilterTextViewModel: ViewModel() {
    private val items = listOf(
        "Item 1",
        "Donut",
        "Eclair",
        "School",
        "Task 1",
        "Electronics"
    )

    private val _filteredItems = MutableStateFlow(items)
    var filteredItems: StateFlow<List<String>> = _filteredItems

    fun filterText(input: String) {
        // this filter returns the full items list when input is an empty string.
        _filteredItems.value = items.filter { it.contains(input, ignoreCase = true) }
    }
}
// [END android_compose_text_filtertextviewmodel]

@Composable
fun FilterTextView(modifier: Modifier = Modifier, viewModel: FilterTextViewModel = viewModel()) {
    val filteredItems by viewModel.filteredItems.collectAsStateWithLifecycle()
    var text by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 10.dp)
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
                viewModel.filterText(text)
            },
            label = { Text(stringResource(R.string.label_task)) },
            modifier = Modifier.fillMaxWidth()
        )

        LazyColumn {
            items(
                count = filteredItems.size,
                key = { index -> filteredItems[index] }
            ) {
                ListItem(
                    headlineContent = { Text(filteredItems[it]) },
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .padding(10.dp)
                )
            }
        }
    }
}


@Composable
fun LongTaskDropdown(
) {
    var expanded by remember { mutableStateOf(false) }
    val taskItemData = List(100) { "Task #${ it+1}"}

    Box(
        modifier = Modifier
            .padding(16.dp)

    ) {
        Button(onClick = { expanded = !expanded},
            modifier = Modifier.fillMaxWidth(.5f)) {
            //Icon(, contentDescription = "Task selection")
            Text("Switch")
        }
        DropdownMenu(
            expanded,
            onDismissRequest = { expanded = false }
        ) {
            taskItemData.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        Log.d(logPrefix, "Task '$option' from list clicked")
                        expanded = !expanded
                    }
                )
            }
        }
    }
}