package com.compose.dungeonsanddragons.presentation.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.compose.dungeonsanddragons.R
import com.compose.dungeonsanddragons.presentation.Dimens
import com.compose.dungeonsanddragons.presentation.onboarding.Page
import com.compose.dungeonsanddragons.presentation.onboarding.pages
import com.compose.dungeonsanddragons.ui.theme.DungeonsAndDragonsTheme

@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    page: Page
) {
    ConstraintLayout(
        modifier = modifier.fillMaxWidth().fillMaxHeight()
    ) {
        // Defining references for the views
        val (image, title, description) = createRefs()

        // Background image with subtle blur
        Image(
            painter = painterResource(page.image),
            contentDescription = null,
            contentScale = ContentScale.Crop, // Make the image fill the constraint space
            modifier = Modifier
                .fillMaxSize() // Fills the entire background
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom) // Ensures the image fills the screen
                }
                .graphicsLayer { alpha = 0.9f } // Slight opacity change
                .blur(2.dp) // Reduced blur for subtle effect
        )

        // Title and description overlay on top of the image
        Text(
            text = page.title,
            modifier = Modifier
                .constrainAs(title) {
                    top.linkTo(parent.top, margin = 250.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(horizontal = Dimens.mediumPaddingOne),

            style = MaterialTheme.typography.displayMedium.copy(
                fontWeight = FontWeight.Bold,
                shadow = Shadow(
                    color = MaterialTheme.colorScheme.primary,
                    offset = Offset(3f, 3f),
                    blurRadius = 12f
                )
            ),
            color = colorResource(R.color.text_title),
            textAlign = TextAlign.Center
        )

        Text(
            text = page.description,
            modifier = Modifier
                .constrainAs(description) {
                    top.linkTo(title.bottom, margin = Dimens.smallPaddingOne)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(horizontal = Dimens.mediumPaddingOne),

            style = MaterialTheme.typography.bodyMedium.copy(
                fontStyle = FontStyle.Normal,
            ),
            color = colorResource(R.color.display_small),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingPagePreview() {
    DungeonsAndDragonsTheme {
        OnBoardingPage(
            page = pages[0]
        )
    }
}
