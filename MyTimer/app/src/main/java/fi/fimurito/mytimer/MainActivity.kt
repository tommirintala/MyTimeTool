package fi.fimurito.mytimer


//import android.os.Build
import android.os.Bundle
import android.util.Log
//import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
//import androidx.annotation.RequiresApi


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
//import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults.InputField
//import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.googlefonts.GoogleFont
//import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.material3.TextButton
//import androidx.compose.material3.TextField
//import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold

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
//import androidx.compose.ui.tooling.preview.PreviewScreenSizes
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
import fi.fimurito.mytimer.data.model.Task
import fi.fimurito.mytimer.ui.theme.MyTimerTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicSecureTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.IconButton
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ShoppingCart
//import androidx.compose.material.ripple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableLongStateOf
//import androidx.compose.runtime.mutableStateMapOf
//import androidx.compose.runtime.setValue
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
//import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType

import androidx.compose.ui.unit.sp
import androidx.room.PrimaryKey
//import androidx.navigation.NavBackStackEntry
//import androidx.navigation.NavController
//import androidx.navigation.NavDestination.Companion.hasRoute
//import androidx.navigation.compose.currentBackStackEntryAsState
//import fi.fimurito.mytimer.ui.MainScreen
import fi.fimurito.mytimer.ui.ProjectStyle
import fi.fimurito.mytimer.ui.TimerClock
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import java.time.Instant
//import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit

const val logPrefix = "MyTimer"
class MainActivity : ComponentActivity() {

    //private val mainViewModel by viewModels<MainViewModel>()
    //private val _lastTask = MutableLiveData<Task>()
    //val lastTask: LiveData<Task> = _lastTask

    /*
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
*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(logPrefix, "Loading app")


        val viewModel = MainViewModel()

        //db = getRoomDatabase(getDatabaseBuilder(applicationContext))
        //taskHandler = TaskHandler()


        enableEdgeToEdge()
        setContent {
            MyTimerTheme(dynamicColor = false) {
                NavigationUI(viewModel)
            }
            /*
            val tasks = mainViewModel.taskPager.collectAsLazyPagingItems()
            val valid by mainViewModel.valid.collectAsState()
            val navController = rememberNavController()
            val context = LocalContext.current
            MyTimerTheme(dynamicColor = false) {
                //MyTimerApp()
                //MainTimerApp()
                //AppScreen()

                NavHost(
                    navController = navController,
                    startDestination = "splash"
                ) {
                    composable(route = "splash") {
                        SplashScreen(
                            valid = valid,
                            onStart = mainViewModel::trackSplashScreenStarted,
                            onSplashEndedValid = {
                                navController.navigate("main") {
                                    popUpTo("splash") { inclusive = true}
                                }
                            },
                            onSplashEndedInvalid = {
                                Toast.makeText(
                                    context,
                                    "Something went horribly wrong...",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        )
                    }
                    composable(route = "main") {
                        MainScreen(tasks = tasks, mainViewModel = mainViewModel)
                    }
                }

             */
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

/*
val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

 */

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




//@PreviewParameter(
//    name = "Medium Phone",
//    device= "spec:width=412dp,height=915dp,dpi=420",
//    showSystemUi=true)
@Preview(showBackground = true)
@Composable
fun HomeScreen(/*viewModel: AppSharedViewModel,*/
               modifier: Modifier = Modifier) {
    //val taskFieldState = rememberTextFieldState()
    //val items = listOf(
    //    "Task 1", "Task 2", "Task 343", "Opetusta"
    //)


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



    /*Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
*/
        Column(modifier = Modifier,
            //.fillMaxWidth()
            //.padding(top = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            //verticalArrangement = Arrangement.Center
                ) {

            //Row(modifier = Modifier.fillMaxWidth(),
              //  horizontalArrangement = Arrangement.Center) {
                //Text("Current date/time")
                TimerClock(
                    modifier = Modifier
                        .size(340.dp)
                        .padding(20.dp),

                    clockStyle = ProjectStyle,
                )
            //}

            Row {
                Text("Current Task:",
                    modifier = Modifier.weight(2f)
                )
                Text(
                    text = "(task title)"/*viewModel.getTaskTitle(),*/,
                    modifier = Modifier.weight(2f),
                )
            }

            Row {
                Text("Start time:",
                    modifier = Modifier.weight(2f))

                Text(text="(start time)" /*viewModel.getStartTime()*/,
                    modifier = Modifier.weight(2f)
                )
            }

            Row {
                Text("End time:",
                    modifier = Modifier.weight(2f))
                Text(text = "(end time)" /*viewModel.getEndTime()*/,
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

            //TaskEditor()
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
//}



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


/*
@Composable
fun LoginScreen(
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

 */


@Composable
fun ProfileScreen(
    /*viewModel: AppSharedViewModel,*/
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
    /*viewModel: AppSharedViewModel,*/
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
    /*viewModel: AppSharedViewModel,*/
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Favourites Screen")
    }
}

/*
@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskEditor(
    viewModel: AppSharedViewModel,
    modifier: Modifier = Modifier
) {
    val datePickerStateFrom = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)
    val datePickerStateTo = rememberDatePickerState(initialDisplayMode = DisplayMode.Picker)
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row() {
            Text(stringResource(R.string.label_task))

        }
        Row() {
            Text(stringResource(R.string.label_task_set_hours))
        }
        Row() {
            Text(stringResource(R.string.label_task_max_hours))
        }
        Row() {
            Text(stringResource(R.string.label_active_from))
            DatePicker(state = datePickerStateFrom)
        }
        Row() {
            Text(stringResource(R.string.label_active_to))
            DatePicker(state = datePickerStateTo)
        }
    }
}

 */


/*
@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: AppDestinations,
    modifier: Modifier = Modifier
) {
    //val sharedViewModel: AndroidViewModel = viewModel(
    //    viewModelStoreOwner = LocalContext.current as ComponentActivity
    //)

    //val appViewModel = AppSharedViewModel(application = Application())


    NavHost(
        navController,
        startDestination = startDestination.route
    ) {
        AppDestinations.entries.forEach { destination ->
            composable(destination.route) {
                when(destination) {
                    AppDestinations.HOME -> HomeScreen(/* appViewModel */)
                    AppDestinations.FAVORITES -> FavouritesScreen(/*appViewModel*/)
                    AppDestinations.LOGIN -> LoginScreen(/*appViewModel*/)
                    AppDestinations.RELOAD -> ReloadScreen(/*appViewModel*/)
                    AppDestinations.PROFILE -> ProfileScreen(/*appViewModel*/)
                }
            }
        }
    }
}

 */

/*
val tabEnabled =  mutableStateMapOf(
    "HOME" to true,
    "Fav" to true,
    "Profile" to true,
    "Login" to true,
    "Reload" to false)
*/


/*
@OptIn(ExperimentalMaterial3Api::class)
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
                    selectedContentColor = MaterialTheme.colorScheme.primary,
                    unselectedContentColor = MaterialTheme.colorScheme.secondary,

                )
            }
            AppNavHost(navController, startDestination,
                modifier = Modifier.padding(contentPadding))
        }



    }


 */
    /*
    Scaffold(
        topBar = {
            topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            AppNavHost(navController, startDestination)
        },
        bottomBar = {},
        floatingActionButton = {}
    ) { innerPadding ->

    }
    */
/*
}

 */


enum class Destination(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val contentDescription: String
) {
    MAIN("main", "Main", Icons.Default.Home, "Main"),
    TASKS("tasks", "Tasks", Icons.Default.Add, "Tasks"),
    SETUP("setup", "Setup", Icons.Default.Settings, "Settings"),
    SYNC("sync", "Sync", Icons.Default.Refresh, "Sync")
}


/*
@Serializable
sealed class Destiny(
    val hasTopBar: Boolean,
    val hasBottomBar: Boolean,
    val title: String = ""
) {
    @Serializable
    data object Splash: Destiny(false, false)

    @Serializable
    data object Home: Destiny(true, true, "Home")

    @Serializable
    data object Login: Destiny(false, true, "Login")

    @Serializable
    data object Sync: Destiny(true, false, "Sync")
}
*/

@Composable
fun SplashScreen(modifier: Modifier = Modifier, onStart: () -> Unit, valid: Boolean?) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(R.drawable.ic_home),
            contentDescription = null,
            modifier = Modifier.size(100.dp))
    }
}

/*
@Composable
fun AppGraph(
    modifier: Modifier = Modifier,
    controller: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = modifier,
        navController = controller,
        startDestination = Destiny.Splash
    ) {
        composable<Destiny.Splash> {
            SplashScreen()
        }
        composable<Destiny.Home> {
            HomeScreen()
        }
        composable<Destiny.Login> {
            LoginScreen()
        }
        composable<Destiny.Sync> {
            SyncScreen()
        }
    }
}

 */

/*
fun NavBackStackEntry?.getDestiny(): Destiny? {
    return this?.let {
        when {
            destination.hasRoute(Destiny.Splash::class) -> Destiny.Splash
            destination.hasRoute(Destiny.Home::class) -> Destiny.Home
            destination.hasRoute(Destiny.Login::class) -> Destiny.Login
            destination.hasRoute(Destiny.Sync::class) -> Destiny.Sync
            else -> null
        }
    }
}

 */

@Composable
fun HomeTopBar(modifier: Modifier = Modifier) {
    Text("HomeTopBar")
}
@Composable
fun SyncTopBar() {
    Text("SyncTopBar")
}

@Composable
fun LoginTopBar() {
    Text("LoginTopBar")
}
@Composable
fun SyncScreen() {
    Text("Sync Screen")
}
/*
@Composable
fun DynamicTopBar(navController: NavController) {
    val entry by navController.currentBackStackEntryAsState()
    val curDest = entry?.getDestiny()

    if (curDest?.hasTopBar ?: false) {
        when (curDest) {
            Destiny.Home -> HomeTopBar()
            Destiny.Sync -> SyncTopBar()
            Destiny.Login -> LoginTopBar()
            else -> HomeTopBar()
        }
    }
}

 */

/*
@Composable
fun AppScreen() {
    val navController = rememberNavController()
    Scaffold(
        topBar = { DynamicTopBar(navController)},
        content = { padding ->
            AppGraph(modifier = Modifier.padding(padding), navController)
        }
    )
}

 */

/*
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


 */

/*
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


 */

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
    //var isShown by remember { mutableStateOf(false) }

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



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationUI(
    viewModel: MainViewModel
) {
    val navController = rememberNavController()
    val startDestination = Destination.MAIN
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = { Text("MyTimer") }
            )
        },
        floatingActionButton = {},
        bottomBar = {
            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets,
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.secondary) {
                Destination.entries.forEachIndexed { index, destination ->
                    NavigationBarItem(
                        selected = selectedDestination == index,
                        onClick = {
                            navController.navigate(route = destination.route)
                            selectedDestination = index
                        },
                        icon = {
                            Icon(
                                destination.icon,
                                contentDescription = destination.contentDescription
                            )
                        },
                        label = { Text(destination.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        AppNavHost(viewModel, navController, startDestination, modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun AppNavHost(
    viewModel: MainViewModel,
    navController: NavHostController,
    startDestination: Destination,
    modifier: Modifier = Modifier
) {


    NavHost(
        navController,
        startDestination = startDestination.route,
        modifier = modifier
    ) {
        Destination.entries.forEach { destination ->
            composable(destination.route) {
                when (destination) {
                    Destination.MAIN -> MainScreen(viewModel)
                    Destination.TASKS -> TaskScreen(viewModel)
                    Destination.SETUP -> SetupScreen(viewModel)
                    Destination.SYNC -> SyncScreen(viewModel)
                }
            }
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun MainScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier) {
    //SimpleDBUITheme {
    Column(modifier = modifier
        .width(IntrinsicSize.Max),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TimerClock(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .padding(20.dp, 8.dp),
            clockStyle = ProjectStyle,
        )

        TaskSelector(
            modifier = Modifier
                .padding(8.dp, 0.dp)
                .fillMaxWidth(),
            viewModel = viewModel
        )
        //TaskDetails(
        //    modifier = Modifier
        //        .weight(0.8f)
        //)
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            Button(
                onClick = {
                    viewModel.autoStopTime = viewModel.autoStopTime.plusMinutes(AppConstants.DEFAULT_TASK_INCREMENT_LENGTH_MINUTES)
                },
                shape = RectangleShape,
                modifier = Modifier
                    .padding(2.dp)
                    .weight(0.3f)
            ) { Text("++")
            }
            Button(
                onClick = {
                    viewModel.taskRunning = false
                },
                shape = RectangleShape,
                modifier = Modifier
                    .padding(2.dp)
                    .weight(0.3f)
            ) { Text("Complete")
            }
            Button(
                onClick = {
                    if (viewModel.currentTask != 0L) {
                        if (!viewModel.taskRunning) {
                            viewModel.currentTaskStart = LocalDateTime.now()
                            viewModel.taskRunning = true
                            viewModel.autoStopTime = LocalDateTime.now()
                                .plusMinutes(AppConstants.DEFAULT_TASK_INCREMENT_LENGTH_MINUTES)
                        }
                    } else {
                        viewModel.taskRunning = false
                    }
                },
                shape = RectangleShape,
                modifier = Modifier
                    .padding(2.dp)
                    .weight(0.3f)
            ) {
                Text("Switch")
            }

        }

        Spacer(modifier = Modifier.height(10.dp))

        Row {
            Text("Cumulative hours:")
            Text("42")
        }
        Row {
            Text("Started:")
            Text("12:00")
        }

    }
    //}
}

// @Preview(showBackground = true)
@Composable
fun SetupScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier) {
    //var networkSyncState by remember { mutableStateOf(false)}
    //var useVAMKServer by remember { mutableStateOf(false)}

    Column(
        modifier = modifier
            .width(IntrinsicSize.Max),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Setup")
        Row {
            Text("Sync network:", modifier.weight(0.4f))
            Checkbox(
                checked = viewModel.syncNetwork,
                onCheckedChange = { viewModel.syncNetwork = it },
                modifier = modifier.weight(0.4f))
        }
        Row {
            Text("VAMK Server:", modifier.weight(0.4f))
            Checkbox(
                checked = viewModel.useVAMKServer,
                onCheckedChange = { viewModel.useVAMKServer = it},
                modifier = modifier.weight(0.4f),
                enabled = viewModel.syncNetwork,)

        }
    }
}

@Composable
fun SyncScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier) {


    Column(
        modifier = modifier
            .width(IntrinsicSize.Max),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Sync")
        Row {
            Button(onClick = {},
            ) {
                Text("Sync")
            }
        }
        /*
        Row {
            Text("VAMK Server:", modifier.weight(0.4f))
            Checkbox(
                checked = viewModel.useVAMKServer,
                onCheckedChange = { viewModel.useVAMKServer = it},
                modifier = modifier.weight(0.4f),
                enabled = viewModel.syncNetwork,)

        }

         */
    }
}


// @Preview(showBackground = true)
@Composable
fun TaskScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Tasks")
        TaskDetails(viewModel = viewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetails(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    tid: Long = -1,
    title: String = "N/A"
) {
    var mId by remember { mutableLongStateOf(tid) }
    var mTitle by remember { mutableStateOf("") }
    var mBegin by remember { mutableStateOf<Long?>(null) }
    var mEnd by remember { mutableStateOf<Long?>(null) }
    var mMaxHours by remember { mutableFloatStateOf(40f) }
    var mCode by remember { mutableStateOf("") }

    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        Text(
            title,
            Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        )
        Row(modifier = Modifier.padding(top = 4.dp)) {
            Text("Id: #", Modifier.weight(0.4f))
            Text("$mId", Modifier.weight(0.4f))
        }
        Row {
            OutlinedTextField(
                value = mTitle,
                onValueChange = { mTitle = it },
                modifier = Modifier
                    .weight(0.4f),
                label = { Text("Task Title") },
                singleLine = true
            )
            OutlinedTextField(
                value = mCode,
                onValueChange = { mCode = it },
                modifier = Modifier
                    .weight(0.4f),
                label = { Text("Code") },
            )
        }
        Column {
            Text("Date Range:")

            CustomDatePicker(
                modifier = Modifier.fillMaxWidth(),
                onDateSelected = { mBegin = it },
                onDismiss = { mBegin = null},
                label = "Begin date"
            )

            CustomDatePicker(
                modifier = Modifier.fillMaxWidth(),
                onDateSelected = { mEnd = it },
                onDismiss = { mEnd = null},
                label = "End date"
            )
        }
        Row {
            OutlinedTextField(
                value = if (mMaxHours == 0f) "" else mMaxHours.toInt().toString(),
                onValueChange = { input ->
                    if (input.length <= 4 && (input.isEmpty() || input.all { it.isDigit() })) {
                        mMaxHours = input.toFloatOrNull() ?: 0f
                    }
                },
                singleLine = true,
                label = { Text("Max hours") },
                modifier = Modifier.fillMaxWidth(0.4f)
            )

        }
        Row {
            Button(
                onClick = {},
                shape = RectangleShape,
                modifier = Modifier
                    .weight(0.25f)
                    .padding(1.dp),
            ) {
                Text("Prev")
            }
            Button(
                onClick = {},
                shape = RectangleShape,
                modifier = Modifier
                    .weight(0.25f)
                    .padding(1.dp)
            ) {
                Text("New")
            }
            Button(
                onClick = {},
                shape = RectangleShape,
                modifier = Modifier
                    .weight(0.25f)
                    .padding(1.dp)
            ) {
                Text("Save")
            }
            Button(
                onClick = {},
                shape = RectangleShape,
                modifier = Modifier
                    .weight(0.25f)
                    .padding(1.dp)
            ) {
                Text("Next")
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePicker(
    modifier: Modifier = Modifier,
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit,
    label: String = "Select date"
) {
    val datePickerState = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)
    var showDatePicker by remember { mutableStateOf(false) }


    val selectedDateText = remember(datePickerState.selectedDateMillis) {
        datePickerState.selectedDateMillis?.let {
            Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalDate().toString()
        } ?: ""
    }

    Box(modifier = modifier) {
        OutlinedTextField(
            value = selectedDateText,
            onValueChange = {},
            label = { Text(label) },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { showDatePicker = !showDatePicker }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Select date"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
        )
        if (showDatePicker) {
            DatePickerDialog(
                onDismissRequest = {
                    showDatePicker = false
                    onDismiss()
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            onDateSelected(datePickerState.selectedDateMillis)
                            showDatePicker = false
                            onDismiss()
                        }
                    ) {
                        Text("Ok")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showDatePicker = false
                            onDismiss()
                        }
                    ) {
                        Text("Cancel")
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }
    }

}


data class Elem (
    @PrimaryKey val key: Int,
    val title: String
)

@Composable
fun TaskSelector(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {
    /*
    val tasks = listOf<Elem>(
        Elem(1, "Task #1"),
        Elem(2, "Task #2"),
        Elem(42, "Task #42")
    )
    val items = listOf(
        "Task 1", "Task 2", "Task 3"
    )
    */
    var expanded by remember { mutableStateOf(false) }
    // var selectedItem by remember { mutableStateOf(items[0]) }
    //var taskRunning by remember { mutableStateOf(false)}
    val pulseRateMs by remember { mutableStateOf(5000L)}

    LaunchedEffect(pulseRateMs) {
        while (isActive) {
            println("Update view")
            delay(pulseRateMs)
            if (viewModel.taskRunning) {
                viewModel.currentTaskTime = ChronoUnit.MINUTES.between(
                    viewModel.currentTaskStart,
                    LocalDateTime.now()
                )
                if (LocalDateTime.now() > viewModel.autoStopTime) {
                    println("Autostop of task")
                    viewModel.taskRunning = false
                }
            }
        }
    }

    // Main Dropdown UI
    Column(modifier) {
        Row {
            Text(text = "Current task: ${viewModel.taskList[viewModel.currentTask]?.title ?: "-- N/A --"}",
                modifier.weight(0.4f))
            Text(text = "Running: " + if (viewModel.taskRunning) "Yes" else "No",
                modifier.weight(0.4f)
                    .background(if (viewModel.taskRunning)  Color.Green else Color.White)
            )
        }
        Row {
            Text(text = "Started at:",
                modifier.weight(0.4f))
            Text(text = viewModel.currentTaskStart.toString(),
                modifier.weight(0.4f))
        }
        Row {
            Text(text = "Task time (min):",
                modifier.weight(0.4f))
            //val diff = ChronoUnit.MINUTES.between(LocalDateTime.now(),viewModel.currentTaskStart)
            Text(text = viewModel.currentTaskTime.toString(),
                modifier.weight(0.4f))
        }
        Row {
            Text(text = "Autostop at:",
                modifier.weight(0.4f))
            Text(text = viewModel.autoStopTime.toString(),
                modifier.weight(0.4f))
        }
        OutlinedTextField(
            value = viewModel.taskList[viewModel.currentTask]?.title.toString(),
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { expanded = true }) {
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Select Task"
                    )
                }
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            viewModel.taskList.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                        // selectedItem = item.key
                        viewModel.currentTask = item.key
                        expanded = false
                        // -> SAVE!!!
                        viewModel.currentTaskStart = LocalDateTime.now()
                        viewModel.taskRunning = true
                        viewModel.autoStopTime = LocalDateTime.now().plusMinutes(AppConstants.DEFAULT_TASK_INCREMENT_LENGTH_MINUTES)
                    },
                    text = { Text(text = item.value.title) })
            }
        }
    }
}
