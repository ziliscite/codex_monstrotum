package com.compose.dungeonsanddragons.presentation.navgraph

sealed class Route(
    val route: String
) {
    data object OnBoardingScreen : Route("onBoardingScreen")
    data object HomeScreen : Route("homeScreen")

    data object DetailsScreen : Route("detailsScreen")
    data object RouteWithArgs : Route("detailsScreen/{monsterIndex}")

    data object SearchScreen : Route("searchScreen")
    data object FavouritesScreen : Route("favouritesScreen")

    // Route for sub navigation/graph
    data object AppStartNavigation : Route("appStartNavigation") // - shows onboarding screen
    data object CodexNavigation : Route("codexNavigation")

    // Another screen
    data object CodexNavigatorScreen : Route("codexNavigatorScreen")
}
