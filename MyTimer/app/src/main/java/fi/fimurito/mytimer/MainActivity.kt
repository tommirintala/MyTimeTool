package fi.fimurito.mytimer


import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge



import androidx.compose.foundation.border
import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

import androidx.compose.ui.graphics.vector.ImageVector

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import fi.fimurito.mytimer.data.model.Task
import fi.fimurito.mytimer.data.AppDatabase
import fi.fimurito.mytimer.ui.theme.MyTimerTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicSecureTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextObfuscationMode
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ShoppingCart
//import androidx.compose.material.ripple
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.text.input.KeyboardType

import androidx.compose.ui.unit.sp
import fi.fimurito.mytimer.ui.ProjectStyle
import fi.fimurito.mytimer.ui.TimerClock

const val logPrefix = "MyTimer"
class MainActivity : ComponentActivity() {

    private val _lastTask = MutableLiveData<Task>()
    val lastTask: LiveData<Task> = _lastTask

    fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<AppDatabase> {
        val appContext = context.applicationContext
        val dbFile = appContext.getDatabasePath(getString(R.string.app_database_name))
        return Room.databaseBuilder<AppDatabase>(
            context = appContext,
            name = dbFile.absolutePath
        )
    }

    fun getRoomDatabase(
        builder: RoomDatabase.Builder<AppDatabase>
    ): AppDatabase {
        return builder
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }

    lateinit var db: AppDatabase



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(logPrefix, "Loading app")

        db = getRoomDatabase(getDatabaseBuilder(applicationContext))
        //taskHandler = TaskHandler()


        enableEdgeToEdge()
        setContent {
            MyTimerTheme(dynamicColor = false) {
                //MyTimerApp()
                MainTimerApp()
            }
        }
    }
}
/*
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
*/
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

/*
private val DarkColors = darkColors(
    primary = R.color.vamk_blue,
    secondary = R.color.purple_700
)

private val LightColors = lightColors(
    primary = R.color.vamk_blue,
    secondary = R.color.black
)

@Composable
fun MyTimerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (darkTheme) DarkColors else LightColors,
        content = content
    )

}
*/


// val currentItem = remember { mutableStateOf<TaskHandler>(null) }
val currentItem = TaskHandler()



@Preview(showBackground = true)
@Preview(
    name = "Medium Phone",
    device= "spec:width=412dp,height=915dp,dpi=420",
    showSystemUi=true)
@Composable
fun HomeScreen(viewModel: AppSharedViewModel,
               modifier: Modifier = Modifier) {
    val taskFieldState = rememberTextFieldState()
    val items = listOf(
        "Task 1", "Task 2", "Task 343", "Opetusta"
    )


/*
    val fileteredItems by remember {
        derivedStateOf {
            val searchText = textFieldState.text.toString()
            if (searchText.isEmpty()) {
                emptyList()
            } else {
                items.filter { it.contains(searchText, ignoreCase = true)}
            }
        }
    }
*/
    //var currentTask: Task

    val infoText = remember { mutableListOf<String>("Ready") }
    val taskSearchText = remember { mutableStateOf("") }



    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,

    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            Row(modifier = Modifier.fillMaxWidth()) {
                Text("Current date/time")
                TimerClock(
                    clockStyle = ProjectStyle,
                )
            }

            Row {
                Text("Current Task:",
                    modifier = Modifier.weight(2f)
                )
                Text(
                    text = viewModel.getTaskTitle(),
                    modifier = Modifier.weight(2f),
                )
            }

            Row {
                Text("Start time:",
                    modifier = Modifier.weight(2f))

                Text(text=viewModel.getStartTime(),
                    modifier = Modifier.weight(2f)
                )
            }

            Row {
                Text("End time:",
                    modifier = Modifier.weight(2f))
                Text(text = viewModel.getEndTime(),
                    modifier = Modifier.weight(2f))
            }
            Row(modifier = Modifier.padding(4.dp)) {
                Button(
                    shape = RectangleShape,
                    onClick = {
                        infoText.add("Clicked ++")

                    },
                    modifier = Modifier.weight(2f),
                ) {
                    Text(stringResource(R.string.button_continue_task))
                }
                Spacer(modifier = Modifier.padding(2.dp))
                Button(
                    shape = RectangleShape,
                    onClick = {
                        infoText.add("'Switch' Clicked")
                    },
                    modifier = Modifier.weight(2f),
                ) {
                    Text(stringResource(R.string.button_change_task))
                    Icon(painterResource( R.drawable.ic_reload), contentDescription = "Switch task",
                        modifier = Modifier.padding(start = 8.dp))
                }
            }
            Row(
                modifier = Modifier.background(MaterialTheme.colorScheme.surface)
            ) {
                OutlinedTextField(
                    value = taskSearchText.value,
                    onValueChange = { taskSearchText.value = it  },
                    label = { Text("Task ID #")},
                    placeholder = { Text("Task ID #")},
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .weight(.33f)
                        .padding(2.dp)
                        //.requiredWidth(120.dp)
                )
                //Icon(painterResource(R.drawable.ic_home), contentDescription = "Show/Hide",
                //    modifier = Modifier.weight(.1f))
                OutlinedTextField(
                    value = taskSearchText.value,
                    label = { Text("Task name")},
                    onValueChange = { taskSearchText.value = it},
                    singleLine = true,
                    modifier = Modifier
                        .weight(.63f)
                        .padding(2.dp)
                        //.requiredWidth(220.dp)

                )
            }
            /*
            Row {

                TaskSearchBar(
                    textFieldState = textFieldState,
                    onSearch = {

                    },
                    searchResults = fileteredItems
                )

                Text("Current item:$currentItem")
            }
*/
            //Row {
                // TaskSwitcher()
            Text("Log:",
                color = MaterialTheme.colorScheme.onPrimaryContainer)
                Text(
                    text = infoText.joinToString(" "),
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 12,
                    color = MaterialTheme.colorScheme.onSurface
                )
            //}
        }
    }
}

private class ScaleNode(private val interactionSource: InteractionSource): Modifier.Node(),
    DrawModifierNode {

        var currentPressPosition: Offset = Offset.Zero
    val animatedScalePercent = Animatable(1f)

    private suspend fun animateToPressed(pressPosition: Offset) {
        currentPressPosition = pressPosition
        animatedScalePercent.animateTo(0.9f, spring())
    }

    private suspend fun animateToResting() {
        animatedScalePercent.animateTo(1f, spring())
    }

    override fun onAttach() {
        coroutineScope.launch {
            interactionSource.interactions.collectLatest { interaction ->
                when (interaction) {
                    is PressInteraction.Press -> animateToPressed(interaction.pressPosition)
                    is PressInteraction.Release -> animateToResting()
                    is PressInteraction.Cancel -> animateToResting()
                }
            }
        }
    }

    override fun ContentDrawScope.draw() {
        scale(
            scale = animatedScalePercent.value,
            pivot = currentPressPosition
        ) {
            this@draw.drawContent()
        }
    }
}
object ScaleIndication: IndicationNodeFactory {
    override fun create(interactionSource: InteractionSource): DelegatableNode {
        return ScaleNode(interactionSource)
    }
    override fun equals(other: Any?): Boolean = other === ScaleIndication
    override fun hashCode() = 100
}

@Composable
fun ScaleButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = CircleShape,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier
            .defaultMinSize(minWidth = 76.dp, minHeight = 48.dp)
            .clickable(
                enabled = enabled,
                indication = ScaleIndication,
                interactionSource = interactionSource,
                onClick = onClick
            )
            .border(width = 2.dp, color = Color.Blue, shape = shape)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        content = content
    )
}

@Composable
fun LoginScreen(
    viewModel: AppSharedViewModel,
    modifier: Modifier = Modifier
) {
    var loggedIn by rememberSaveable() { mutableStateOf(false) }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (loggedIn) {
            Column {
                Row {
                    Text("Logout user",
                        Modifier.padding(30.dp))
                }
                Row {
                    Button(onClick = {
                        loggedIn = false
                        tabEnabled["Reload"] = false
                    }) {
                        Text(stringResource(R.string.label_logout))
                    }
                }
            }


        } else {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {
                    Text(text = "Please Login",
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.W800,
                        fontSize = 32.sp,
                    )
                }
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        state = rememberTextFieldState(initialText = "john.doe@mail.com"),
                        label = { Text(stringResource(R.string.label_login)) }
                    )
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    PasswordTextField(
                        //state = rememberTextFieldState(initialText = ""),
                        //label = { Text(stringResource(R.string.label_password)) },
                    )
                }
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {
                    Button(onClick = {
                        loggedIn = true
                        tabEnabled["Reload"] = true
                    }) {
                        Text(stringResource(R.string.label_login))
                    }
                }
            }
        }




    }
}


@Composable
fun ProfileScreen(
    viewModel: AppSharedViewModel,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Profile Screen")
    }
}

@Composable
fun ReloadScreen(
    viewModel: AppSharedViewModel,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Reload Screen")
    }
}

@Composable
fun FavouritesScreen(
    viewModel: AppSharedViewModel,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Favourites Screen")
    }
}

val appViewModel = AppSharedViewModel(application = Application())

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: AppDestinations,
    modifier: Modifier = Modifier
) {
    //val sharedViewModel: AndroidViewModel = viewModel(
    //    viewModelStoreOwner = LocalContext.current as ComponentActivity
    //)



    NavHost(
        navController,
        startDestination = startDestination.route
    ) {
        AppDestinations.entries.forEach { destination ->
            composable(destination.route) {
                when(destination) {
                    AppDestinations.HOME -> HomeScreen(appViewModel)
                    AppDestinations.FAVORITES -> FavouritesScreen(appViewModel)
                    AppDestinations.LOGIN -> LoginScreen(appViewModel)
                    AppDestinations.RELOAD -> ReloadScreen(appViewModel)
                    AppDestinations.PROFILE -> ProfileScreen(appViewModel)
                }
            }
        }
    }
}

val tabEnabled =  mutableStateMapOf(
    "HOME" to true,
    "Fav" to true,
    "Profile" to true,
    "Login" to true,
    "Reload" to false)



@PreviewScreenSizes
@Composable
fun MainTimerApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val startDestination = AppDestinations.HOME
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }
    /*
    val tabEnabled by rememberSaveable { mutableStateMapOf(
        "HOME" to true,
        "Fav" to true,
        "Profile" to true,
        "Login" to true,
        "Reload" to false)
    }
    */



    Scaffold(modifier = modifier) { contentPadding ->
        PrimaryTabRow(selectedTabIndex = selectedDestination,
            modifier = Modifier.padding(contentPadding)) {
            AppDestinations.entries.forEachIndexed { index, destination ->
                Tab(
                    selected = selectedDestination == index,
                    onClick = {
                        navController.navigate(route = destination.route)
                        selectedDestination = index
                    },
                    text = {
                        Text(
                            text = destination.label,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    enabled = tabEnabled[destination.label] == true,
                    icon = {
                        Icon(
                        painterResource(  destination.icon),
                        contentDescription = destination.contentDescription
                        )
                    },
                    //selectedContentColor = MaterialTheme.colorScheme.primary,
                    //unselectedContentColor = MaterialTheme.colorScheme.secondary,
                )
            }
        }
        AppNavHost(navController, startDestination)
    }
}

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
    val route: String,
    val contentDescription: String
) {
    HOME("HOME", R.drawable.ic_home, "home", "Home"),
    FAVORITES("Fav", R.drawable.ic_favorite, "favourites", "Favourites"),
    PROFILE("Profile", R.drawable.ic_account_box, "profile", "Profile"),
    LOGIN("Login", R.drawable.ic_login, "login", "Login"),
    RELOAD("Reload", R.drawable.ic_reload, "reload", "Reload"),
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

        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()) {
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
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth(1f)
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
            .fillMaxWidth()

    ) {
        Button(onClick = { expanded = !expanded},
            modifier = Modifier.fillMaxWidth(.5f)) {
            //Icon(, contentDescription = "Task selection")
            Text("Switch")
        }
        DropdownMenu(
            expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth(.5f),
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskSearchBar(
    textFieldState: TextFieldState,
    onSearch: (String) -> Unit,
    searchResults: List<String>,
    modifier: Modifier = Modifier
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Box(Modifier
        .fillMaxSize()
        .semantics { isTraversalGroup = true }
    ) {
        SearchBar(
            modifier = Modifier
                .align(Alignment.Center)
                .semantics { traversalIndex = 0f },
            inputField = {
                InputField(
                    query = textFieldState.text.toString(),
                    onQueryChange = { textFieldState.edit { replace(0, length, it)}},
                    onSearch = {
                        onSearch(textFieldState.text.toString())
                        expanded = false
                    },
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                    placeholder = { Text("Etsi") }
                )
            },
            expanded = expanded,
            onExpandedChange = { expanded = it },
        ) {
            Column(Modifier.verticalScroll(rememberScrollState())) {
                searchResults.forEach { result ->
                    ListItem(
                        headlineContent = { Text(result)},
                        modifier = Modifier
                            .clickable {
                                textFieldState.edit { replace(0, length, result) }
                            }
                            .fillMaxWidth()
                    )
                }
            }
        }
    }

}

@Composable
fun PasswordTextField() {
    val state = remember { TextFieldState() }
    var showPassword by remember { mutableStateOf(false) }

    BasicSecureTextField(
        textStyle = TextStyle(
            color = MaterialTheme.colorScheme.primary,
            background = MaterialTheme.colorScheme.surface,
        ),
        state = state,
        textObfuscationMode =
            if (showPassword) {
                TextObfuscationMode.Visible
            } else {
                TextObfuscationMode.RevealLastTyped
            },
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .border(1.dp, Color.LightGray, RoundedCornerShape(6.dp))
            .padding(6.dp),
        decorator = { innerTextField ->
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(stringResource(R.string.label_password),
                    fontSize = 10.sp,)
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 16.dp, end = 48.dp)
                ) {
                    innerTextField()
                }
                Icon(
                    if (showPassword) {
                        //Icons.Filled.Visibility
                        painterResource(R.drawable.ic_visible)
                    } else {
                        //Icons.Filled.VisibilityOff
                        painterResource(R.drawable.ic_visible_off)
                    },
                    contentDescription = "Toggle password visibility",
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .requiredSize(48.dp)
                        .padding(16.dp)
                        .clickable { showPassword = !showPassword }
                )
            }
        }
    )
}