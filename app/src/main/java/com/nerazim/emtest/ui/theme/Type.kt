package com.nerazim.emtest.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.nerazim.emtest.R

val sfProDisplay = FontFamily(
    Font(R.font.sf_pro_display_semibold, FontWeight.SemiBold),
    Font(R.font.sf_pro_display_medium, FontWeight.Medium),
    Font(R.font.sf_pro_display_regular, FontWeight.Normal)
)

// Set of Material typography styles to start with
val Typography = Typography(
    //title 1
    titleLarge = TextStyle(
        fontFamily = sfProDisplay,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp
    ),
    //title 2
    titleMedium = TextStyle(
        fontFamily = sfProDisplay,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp
    ),
    //title 3
    titleSmall = TextStyle(
        fontFamily = sfProDisplay,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    //title 4
    bodyLarge = TextStyle(
        fontFamily = sfProDisplay,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    ),
    //text 1
    bodyMedium = TextStyle(
        fontFamily = sfProDisplay,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    //button text 1
    headlineLarge = TextStyle(
        fontFamily = sfProDisplay,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    ),
    //button text 2
    headlineMedium = TextStyle(
        fontFamily = sfProDisplay,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    //tab text
    headlineSmall = TextStyle(
        fontFamily = sfProDisplay,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp
    ),
    //number
    labelSmall = TextStyle(
        fontFamily = sfProDisplay,
        fontWeight = FontWeight.Normal,
        fontSize = 7.sp
    )
)