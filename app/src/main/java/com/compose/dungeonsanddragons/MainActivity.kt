package com.compose.dungeonsanddragons

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.compose.dungeonsanddragons.presentation.navgraph.NavGraph
import com.compose.dungeonsanddragons.ui.theme.DungeonsAndDragonsTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel by viewModels<MainViewModel>()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // We wanna keep the splash screen visible until we
        // load the the start navigation from data store preference
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                mainViewModel.splashCondition // if false, the splash screen will disappear
            }
        }

        enableEdgeToEdge()
        setContent {
            DungeonsAndDragonsTheme {
                val isDarkMode = isSystemInDarkTheme()
                val systemController = rememberSystemUiController()

                SideEffect {
                    systemController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = !isDarkMode
                    )
                }

                Scaffold(
                    topBar = {},
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.background)
                ) {
                    val startDestination = mainViewModel.startDestination
                    NavGraph(startDestination = startDestination)
                }
            }
        }
    }
}
