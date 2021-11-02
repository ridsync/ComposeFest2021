package com.oklab.devokscomposecodelab.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue

private val DarkColorPalette = darkColors(
    primary = Navy,
    primaryVariant = Navy,
    onPrimary = Chartreuse,
    secondary = Teal200,
    surface = Blue,
    onSurface = Navy
)

private val LightColorPalette = lightColors(
    primary = LightBlue,
    primaryVariant = Navy,
    onPrimary = Navy,
    secondary = Teal200,
    surface = Blue,
    onSurface = Color.White,

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun DevOksComposeCodelabTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}