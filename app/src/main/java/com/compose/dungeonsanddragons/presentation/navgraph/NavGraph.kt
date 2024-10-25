package com.compose.dungeonsanddragons.presentation.navgraph

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.compose.dungeonsanddragons.presentation.onboarding.OnBoardingScreen
import com.compose.dungeonsanddragons.presentation.onboarding.OnBoardingViewModel

@Composable
fun NavGraph(
    startDestination: String,
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(
                route = Route.OnBoardingScreen.route
            ) {
                val viewModel = hiltViewModel<OnBoardingViewModel>()
                OnBoardingScreen {
                    viewModel.onEvent(it)
                }
            }
        }

        navigation(
            route = Route.CodexNavigation.route,
            startDestination = Route.CodexNavigatorScreen.route
        ) {
            composable(
                route = Route.CodexNavigatorScreen.route
            ) {
                Text(
                    text = "CodexNavigatorScreen"
                )
            }
        }
    }
}