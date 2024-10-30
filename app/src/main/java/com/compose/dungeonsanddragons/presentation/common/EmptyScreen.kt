package com.compose.dungeonsanddragons.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import com.compose.dungeonsanddragons.R
import com.compose.dungeonsanddragons.ui.theme.DungeonsAndDragonsTheme

@Composable
fun EmptyScreen(
    error: LoadState.Error? = null,
    errorString: String? = null
) {
    val message by remember {
        mutableStateOf(if (error != null) parseErrorMessage(error) else parseErrorString(errorString))
    }

    val icon by remember {
        mutableIntStateOf(R.drawable.error_page)
    }

    var startAnimation by remember {
        mutableStateOf(false)
    }

    val alphaAnimation by animateFloatAsState(
        targetValue = if (startAnimation) 0.6f else 0f,
        animationSpec = tween(durationMillis = 1000), label = ""
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
    }

    EmptyContent(alphaAnim = alphaAnimation, message = message, iconId = icon)
}

@Composable
fun EmptyScreen(
    error: LoadState.Error? = null,
    errorString: String? = null,
    onRefreshClick: () -> Unit
) {
    val message by remember {
        mutableStateOf(if (error != null) parseErrorMessage(error) else parseErrorString(errorString))
    }

    val icon by remember {
        mutableIntStateOf(R.drawable.error_page)
    }

    var startAnimation by remember {
        mutableStateOf(false)
    }

    val alphaAnimation by animateFloatAsState(
        targetValue = if (startAnimation) 0.6f else 0f,
        animationSpec = tween(durationMillis = 1000), label = ""
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
    }

    EmptyContent(modifier = Modifier.padding(bottom = 10.dp), alphaAnim = alphaAnimation, message = message, iconId = icon, refreshable = true) {
        onRefreshClick()
    }
}

@Composable
fun EmptyContent(modifier: Modifier = Modifier, alphaAnim: Float, message: String, iconId: Int, refreshable: Boolean = false, onRefreshClick: () -> Unit = {}) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .size(320.dp)
                .alpha(alphaAnim)
                .clickable { onRefreshClick() },
            painter = painterResource(id = iconId),
            contentDescription = null,
        )
        Text(
            modifier = Modifier
                .padding(5.dp)
                .alpha(alphaAnim)
                .fillMaxWidth(0.7f),
            text = message,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            color = if (isSystemInDarkTheme()) LightGray else DarkGray,
        )
        if (refreshable) {
            Text(
                modifier = Modifier
                    .padding(5.dp)
                    .alpha(alphaAnim)
                    .fillMaxWidth(0.7f),
                text = "Click me to refresh",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall,
                color = if (isSystemInDarkTheme()) LightGray else DarkGray,
            )
        }
    }
}


fun parseErrorMessage(error: LoadState.Error?): String {
    return error?.error?.message.toString()
}

fun parseErrorString(errorString: String?): String {
    return errorString ?: "Something went wrong."
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun EmptyScreenPreview() {
    DungeonsAndDragonsTheme {
        EmptyScreen(errorString = "Try refreshing to decipher the runes.") {}
    }
}
