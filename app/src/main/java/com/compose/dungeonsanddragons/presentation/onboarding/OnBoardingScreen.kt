package com.compose.dungeonsanddragons.presentation.onboarding

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.compose.dungeonsanddragons.presentation.Dimens
import com.compose.dungeonsanddragons.presentation.common.CodexButton
import com.compose.dungeonsanddragons.presentation.common.CodexTextButton
import com.compose.dungeonsanddragons.presentation.onboarding.components.OnBoardingPage
import com.compose.dungeonsanddragons.presentation.onboarding.components.PageIndicator
import com.compose.dungeonsanddragons.ui.theme.DungeonsAndDragonsTheme
import kotlinx.coroutines.launch
import javax.inject.Inject

@Composable
fun OnBoardingScreen(
    event: (OnBoardingEvent) -> Unit
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (pager, nav) = createRefs()

        // Page state, typical
        val pagerState = rememberPagerState(initialPage = 0, pageCount = {
            pages.size
        })

        // Button state for each page, like, the text on it
        val buttonState = remember {
            // The "result" of the state is earned depending on which screen we are on
            derivedStateOf {
                when (pagerState.currentPage) {
                    0 -> Pair("", "Next")
                    1 -> Pair("Back", "Next")
                    2 -> Pair("Back", "Explore")
                    else -> Pair("", "")
                }
            }
        }

        // Horizontal Pager that takes up the whole screen
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.constrainAs(pager) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            },
            userScrollEnabled = false,
            pageContent = {
                OnBoardingPage(page = pages[it])
            }
        )

        // Row at the bottom overlaying the pager
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp) // Adjust the height as needed
                .zIndex(1f) // Ensure this is drawn above the pager
                .padding(horizontal = Dimens.mediumPaddingTwo)
                .navigationBarsPadding()
                .constrainAs(nav) {
                    bottom.linkTo(parent.bottom) // Constrain it to the bottom of the screen
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PageIndicator(
                modifier = Modifier.width(Dimens.pageIndicatorWidth),
                pageSize = pages.size,
                selectedPage = pagerState.currentPage
            )

            // Buttons
            Row(verticalAlignment = Alignment.CenterVertically) {
                val scope = rememberCoroutineScope()
                if (buttonState.value.first.isNotEmpty()) {
                    CodexTextButton(
                        text = buttonState.value.first,
                    ) {
                        scope.launch {
                            // Navigate to the previous screen
                            pagerState.animateScrollToPage(
                                page = pagerState.currentPage - 1,
                                animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.fillMaxWidth(0.05f))

                CodexButton(
                    text = buttonState.value.second,
                ) {
                    scope.launch {
                        // Last page, navigate to home screen
                        if (pagerState.currentPage == 2) {
                            // Save entry
                            event(OnBoardingEvent.SaveAppEntry)
                        } else {
                            // Navigate to the next screen
                            pagerState.animateScrollToPage(
                                page = pagerState.currentPage + 1,
                                animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingScreenPreview() {
    val onBoardingViewModel : OnBoardingViewModel = hiltViewModel()
    DungeonsAndDragonsTheme {
        OnBoardingScreen {
            onBoardingViewModel.onEvent(it)
        }
    }
}
