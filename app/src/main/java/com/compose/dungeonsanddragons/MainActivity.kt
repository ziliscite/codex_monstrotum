package com.compose.dungeonsanddragons

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.compose.dungeonsanddragons.presentation.onboarding.OnBoardingScreen
import com.compose.dungeonsanddragons.ui.theme.DungeonsAndDragonsTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        WindowCompat.setDecorFitsSystemWindows(window, false) -- so that image will fit the upper empty space
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            DungeonsAndDragonsTheme {
                Scaffold(
                    topBar = {},
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.background)
                ) {
                    OnBoardingScreen()
                }
            }
        }
    }
}
