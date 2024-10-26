package com.compose.dungeonsanddragons.presentation.common

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.compose.dungeonsanddragons.R
import com.compose.dungeonsanddragons.util.Constants

@Composable
fun MonsterImage(
    monsterIndex: String, context: Context
) {
    var imageUrl by remember {
        mutableStateOf("https://raw.githubusercontent.com/theoperatore/dnd-monster-api/master/src/db/assets/${monsterIndex}.jpg")
    }
    val alternateUrl = Constants.BASE_URL + "/images/monsters/${monsterIndex}.png"

    AsyncImage(
        modifier = Modifier
            .fillMaxSize()
            .clip(MaterialTheme.shapes.small),
        model = ImageRequest.Builder(context)
            .data(imageUrl)
            .error(R.drawable.custom_error_image)
            .crossfade(true)
            .listener(
                onError = { _, _ ->
                    // Switch to alternate URL on error
                    imageUrl = alternateUrl
                }
            )
            .build(),
        contentDescription = monsterIndex,
        contentScale = ContentScale.Crop
    )
}
