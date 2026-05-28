package fi.fimurito.mytimer.ui

import androidx.compose.foundation.Image
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.core.R
import fi.fimurito.mytimer.MainViewModel

@Composable
fun SplashScreen (mainViewModel: MainViewModel, modifier: Modifier = Modifier) {
    Scaffold() {
        Image(painterResource(R.drawable.ic_login))
    }

}