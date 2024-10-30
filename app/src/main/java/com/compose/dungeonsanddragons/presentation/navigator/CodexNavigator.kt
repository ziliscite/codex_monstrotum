package com.compose.dungeonsanddragons.presentation.navigator

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.compose.dungeonsanddragons.presentation.detail.DetailEvent
import com.compose.dungeonsanddragons.presentation.detail.DetailScreen
import com.compose.dungeonsanddragons.presentation.detail.DetailViewModel
import com.compose.dungeonsanddragons.presentation.favorite.FavoriteScreen
import com.compose.dungeonsanddragons.presentation.favorite.FavoriteViewModel
import com.compose.dungeonsanddragons.presentation.home.HomeScreen
import com.compose.dungeonsanddragons.presentation.home.HomeViewModel
import com.compose.dungeonsanddragons.presentation.navgraph.Route
import com.compose.dungeonsanddragons.presentation.navigator.components.CodexBottomNavigation
import com.compose.dungeonsanddragons.presentation.search.SearchScreen
import com.compose.dungeonsanddragons.presentation.search.SearchViewModel
import com.compose.dungeonsanddragons.util.Dimens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CodexNavigator(
    bottomPadding: Dp
) {
    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }
    selectedItem = when(backStackState?.destination?.route) {
        Route.HomeScreen.route -> 0
        Route.SearchScreen.route -> 1
        Route.FavouritesScreen.route -> 2
        else -> 3
    }

    // Check if the current route is the detail screen
    val isDetailScreen = backStackState?.destination?.route?.startsWith(Route.DetailsScreen.route) == true

    val visibility by animateFloatAsState(
        label = "",
        targetValue = if (isDetailScreen) 0f else 1f,
        animationSpec = tween(300, easing = LinearEasing),
    )

    val backgroundColor by animateColorAsState(
        label = "",
        targetValue = if (isDetailScreen) Color.Transparent else MaterialTheme.colorScheme.surface,
        animationSpec = tween(300, easing = LinearEasing),
    )

    Scaffold(
        bottomBar = {
            CodexBottomNavigation(
                modifier = Modifier
                    .padding(horizontal = Dimens.smallPaddingOne)
                    .statusBarsPadding()
                    .windowInsetsPadding(insets = WindowInsets.safeDrawing)
                    .padding(bottom = bottomPadding + Dimens.extraSmallPadding),
                selectedItem = selectedItem,
                onItemSelected = {
                    when(it) {
                        0 -> navigateToScreen(navController, Route.HomeScreen.route)
                        1 -> navigateToScreen(navController, Route.SearchScreen.route)
                        2 -> navigateToScreen(navController, Route.FavouritesScreen.route)
                    }
                },
                visibility = visibility,
                backgroundColor = backgroundColor
            )
        }
    ) {
        NavHost(
            modifier = Modifier.padding(),
            navController = navController,
            startDestination = Route.HomeScreen.route
        ) {
            composable(
                route = Route.HomeScreen.route,
            ) {
                val viewModel = hiltViewModel<HomeViewModel>()
                val monsters = viewModel.monsters.collectAsLazyPagingItems()
                HomeScreen(monsters, navigateToSearch = {
                    navigateToScreen(navController, Route.SearchScreen.route)
                }) { monsterIndex ->
                    navigateToScreen(navController, Route.DetailsScreen.route + "/$monsterIndex")
                }
            }

            composable(
                route = Route.SearchScreen.route,
            ) {
                val viewModel = hiltViewModel<SearchViewModel>()
                SearchScreen(
                    state = viewModel.searchQuery.value, navigate = { monsterIndex ->
                        navigateToScreen(navController,Route.DetailsScreen.route + "/$monsterIndex")
                    }
                ) { event ->
                    viewModel.onEvent(event)
                }
            }

            composable(
                route = Route.FavouritesScreen.route,
            ) {
                val viewModel = hiltViewModel<FavoriteViewModel>()
                FavoriteScreen(
                    state = viewModel.state.value,
                ) { monsterIndex ->
                    navigateToScreen(navController, Route.DetailsScreen.route + "/$monsterIndex")
                }
            }

            composable(
                route = Route.RouteWithArgs.route,
                arguments = listOf(navArgument("monsterIndex") {
                    type = NavType.StringType
                }),
                enterTransition = {
                    slideIntoContainer(
                        towards = SlideDirection.Start,
                        animationSpec = tween(300)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        towards = SlideDirection.End,
                        animationSpec = tween(300)
                    )
                },
                popEnterTransition = {
                    slideIntoContainer(
                        towards = SlideDirection.Start,
                        animationSpec = tween(300)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        towards = SlideDirection.End,
                        animationSpec = tween(300)
                    )
                }
            ) { backStackEntry ->
                val viewModel = hiltViewModel<DetailViewModel>()
                if (viewModel.sideEffect != null) {
                    Toast.makeText(LocalContext.current, viewModel.sideEffect, Toast.LENGTH_SHORT).show()
                    viewModel.onEvent(DetailEvent.RemoveSideEffect)
                }
                val monsterIndex = backStackEntry.arguments?.getString("monsterIndex") ?: return@composable
                DetailScreen(
                    index = monsterIndex,
                    state = viewModel.monster.value,
                    navigateUp = {
                        navController.popBackStack()
                    }
                ) { event ->
                    viewModel.onEvent(event)
                }
            }
        }
    }
}

fun navigateToScreen(
    navController: NavController,
    route: String
) {
    if (route.startsWith(Route.DetailsScreen.route)) {
        navController.navigate(route)
    } else {
        navController.navigate(route) {
            navController.graph.startDestinationRoute?.let {
                popUpTo(it) {
                    saveState = true
                }
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}
