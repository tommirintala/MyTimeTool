package fi.fimurito.mytimer.ui

import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.ImageBitmap
//import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import fi.fimurito.mytimer.R
import fi.fimurito.mytimer.MainViewModel

@Composable
fun SplashScreen(mainViewModel: MainViewModel, modifier: Modifier = Modifier) {

    Image(
        painter = painterResource(id = R.drawable.splash),
        contentDescription = null,
        modifier = Modifier,
        contentScale = ContentScale.FillHeight
    )


}