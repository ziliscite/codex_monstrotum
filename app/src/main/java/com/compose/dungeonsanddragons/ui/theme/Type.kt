package com.compose.dungeonsanddragons.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.compose.dungeonsanddragons.R

val Caudex = FontFamily(
    fonts = listOf(
        Font(R.font.caudex_regular, FontWeight.Normal),
        Font(R.font.caudex_bold, FontWeight.Bold),
        Font(R.font.caudex_italic, FontWeight.Normal, FontStyle.Italic),
        Font(R.font.caudex_bold_italic, FontWeight.Bold, FontStyle.Italic)
    )
)

// Set of Material typography styles to start with
val Typography = Typography(
    displaySmall = TextStyle(
        fontSize = 24.sp,
        fontFamily = Caudex,
        fontWeight = FontWeight.Normal,
        lineHeight = 36.sp,
    ),
    displayMedium = TextStyle(
        fontSize = 32.sp,
        fontFamily = Caudex,
        fontWeight = FontWeight.Normal,
        lineHeight = 48.sp,
    ),
    displayLarge = TextStyle(
        fontSize = 42.sp,
        fontFamily = Caudex,
        fontWeight = FontWeight.Normal,
        lineHeight = 60.sp,
    ),
    bodySmall = TextStyle(
        fontSize = 14.sp,
        fontFamily = Caudex,
        fontWeight = FontWeight.Normal,
        lineHeight = 21.sp,
    ),
    bodyMedium = TextStyle(
        fontSize = 16.sp,
        fontFamily = Caudex,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp,
    ),
    bodyLarge = TextStyle(
        fontSize = 20.sp,
        fontFamily = Caudex,
        fontWeight = FontWeight.Normal,
        lineHeight = 30.sp,
    ),
    labelSmall = TextStyle(
        fontSize = 13.sp,
        fontFamily = Caudex,
        fontWeight = FontWeight.Normal,
        lineHeight = 19.sp,
    ),
    labelMedium = TextStyle(
        fontSize = 15.sp,
        fontFamily = Caudex,
        fontWeight = FontWeight.Normal,
        lineHeight = 21.sp,
    )
)
