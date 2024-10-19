package com.compose.dungeonsanddragons

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.compose.dungeonsanddragons.domain.usecases.AppEntryUseCases
import com.compose.dungeonsanddragons.presentation.onboarding.OnBoardingScreen
import com.compose.dungeonsanddragons.presentation.onboarding.OnBoardingViewModel
import com.compose.dungeonsanddragons.ui.theme.DungeonsAndDragonsTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var appEntryUseCases: AppEntryUseCases

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            appEntryUseCases.readAppEntry().collect {
                Log.d("HILT", it.toString())
            }
        }

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
                    // use hiltViewModel, cuz we inject inject shit to it. Gotta specify which one tho
                    val onBoardingViewModel = hiltViewModel<OnBoardingViewModel>()
                    OnBoardingScreen {
                        onBoardingViewModel.onEvent(it)
                    }
                }
            }
        }
    }
}
