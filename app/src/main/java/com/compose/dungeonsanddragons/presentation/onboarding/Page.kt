package com.compose.dungeonsanddragons.presentation.onboarding

import androidx.annotation.DrawableRes
import com.compose.dungeonsanddragons.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        "Welcome, Travelers!",
        "Prepare to journey through the realms of Dungeons & Dragons. Unveil mysteries and plan your adventures with the Codex Monstrorum!",
        R.drawable.img_2
    ),
    Page(
        "Unveil the Creatures",
        "From fearsome dragons to elusive fey, the Codex holds the knowledge of every monster you may encounter on your adventures.",
        R.drawable.img_5
    ),
    Page(
        "Master the Unknown",
        "Plan your encounters effortlessly. Search, filter, and learn about creatures, from stats to lore, and build your strategies to conquer the mystical beast!",
        R.drawable.img_8
    )
)
