package com.compose.dungeonsanddragons.presentation.onboarding

// Class will have the event that will be sent from the UI to the view model
// Like, intermediary?
sealed class OnBoardingEvent {
    data object SaveAppEntry : OnBoardingEvent()
}