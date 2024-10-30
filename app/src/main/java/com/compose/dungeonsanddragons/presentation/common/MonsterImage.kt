package com.compose.dungeonsanddragons.presentation.common

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.compose.dungeonsanddragons.R
import com.compose.dungeonsanddragons.ui.theme.DungeonsAndDragonsTheme
import com.compose.dungeonsanddragons.util.Constants

@Composable
fun MonsterImage(
    modifier: Modifier = Modifier,
    index: String,
    context: Context,
    card: Boolean = true,
    @DrawableRes alternativePlaceholder: Int = R.drawable.custom_error_image_detail
) {
    var imageUrl by remember {
        mutableStateOf("https://raw.githubusercontent.com/theoperatore/dnd-monster-api/master/src/db/assets/${index}.jpg")
    }
    val alternateUrl = Constants.BASE_URL + "/images/monsters/${index}.png"
    var isAlternateUrlUsed by remember { mutableStateOf(false) }

    AsyncImage(
        modifier = modifier.clip(MaterialTheme.shapes.small),
        model = ImageRequest.Builder(context)
            .data(imageUrl)
            .error(if (card) R.drawable.custom_error_image else alternativePlaceholder)
            .crossfade(true)
            .listener(
                onError = { _, _ ->
                    if (!isAlternateUrlUsed) {
                        imageUrl = alternateUrl
                        isAlternateUrlUsed = true
                    }
                }
            )
            .build(),
        contentDescription = index,
        contentScale = ContentScale.Crop
    )
}

@Preview(showBackground = true)
@Composable
fun MonsterImagePreview() {
    DungeonsAndDragonsTheme {
        MonsterImage(
            index = "adult-gold-dragon",
            context = LocalContext.current
        )
    }
}
