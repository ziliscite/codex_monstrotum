package com.compose.dungeonsanddragons.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.compose.dungeonsanddragons.presentation.detail.DetailScreen
import com.compose.dungeonsanddragons.presentation.detail.DetailViewModel
import com.compose.dungeonsanddragons.presentation.favorite.FavoriteScreen
import com.compose.dungeonsanddragons.presentation.favorite.FavoriteViewModel
import com.compose.dungeonsanddragons.presentation.home.HomeScreen
import com.compose.dungeonsanddragons.presentation.home.HomeViewModel
import com.compose.dungeonsanddragons.presentation.onboarding.OnBoardingScreen
import com.compose.dungeonsanddragons.presentation.onboarding.OnBoardingViewModel
import com.compose.dungeonsanddragons.presentation.search.SearchScreen
import com.compose.dungeonsanddragons.presentation.search.SearchViewModel

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
                val viewModel = hiltViewModel<HomeViewModel>()
                val monsters = viewModel.monsters.collectAsLazyPagingItems()
                HomeScreen(monsters, navigateToSearch = {
                    navController.navigate(Route.SearchScreen.route)
                }) { monsterIndex ->
                    navController.navigate(Route.DetailsScreen.route + "/$monsterIndex")
                }
            }
        }

        composable(
            route = Route.SearchScreen.route
        ) {
            val viewModel = hiltViewModel<SearchViewModel>()
            SearchScreen(
                state = viewModel.searchQuery.value, navigate = { monsterIndex ->
                    navController.navigate(Route.DetailsScreen.route + "/$monsterIndex")
                }
            ) {
                viewModel.onEvent(it)
            }
        }

        composable(
            route = Route.RouteWithArgs.route,
            arguments = listOf(navArgument("monsterIndex") { type = NavType.StringType })
        ) { backStackEntry ->
            val viewModel = hiltViewModel<DetailViewModel>()
            val monsterIndex = backStackEntry.arguments?.getString("monsterIndex") ?: return@composable
            DetailScreen(
                index = monsterIndex,
                state = viewModel.monster.value,
                navigateUp = { navController.navigateUp() }
            ) {
                viewModel.onEvent(it)
            }
        }

        navigation(
            route = Route.CodexNavigatorScreen.route,
            startDestination = Route.FavouritesScreen.route
        ) {
            composable(
                route = Route.FavouritesScreen.route
            ) {
                val viewModel = hiltViewModel<FavoriteViewModel>()
                FavoriteScreen(
                    state = viewModel.state.value,
                ) {
                    navController.navigate(it)
                }
            }
        }
    }
}
