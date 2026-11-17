package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// Cosmic Rang Design System Core Colors
val CelestialNavy = Color(0xFF10112B)
val MysticPurple = Color(0xFF6E4B91)
val CelestialGold = Color(0xFFD8B45B)
val MoonIvory = Color(0xFFF3E9D5)

@Immutable
data class CosmicExtendedColors(
    val cosmicAbyss: Color = CosmicAbyss,
    val celestialNavy: Color = CelestialNavy,
    val cosmicNavy: Color = CelestialNavy,
    val cosmicSurface: Color = CosmicSurface,
    val cosmicSurfaceElevated: Color = CosmicSurfaceElevated,
    val mysticPurple: Color = MysticPurple,
    val mysticPurpleDeep: Color = MysticPurpleDeep,
    val celestialGold: Color = CelestialGold,
    val celestialGoldBright: Color = CelestialGoldBright,
    val celestialGoldSubtle: Color = CelestialGoldSubtle,
    val moonIvory: Color = MoonIvory,
    val moonIvoryMuted: Color = MoonIvoryMuted,
    val goldHairline: Color = CosmicOutline,
    val purpleHairline: Color = CosmicPurpleOutline,
    val emeraldStatus: Color = CosmicEmerald,
    val amberStatus: Color = CosmicAmber
)

val LocalCosmicColors = staticCompositionLocalOf { CosmicExtendedColors() }

private val CosmicDarkColorScheme = darkColorScheme(
    primary = CelestialGold,
    onPrimary = CelestialNavy,
    primaryContainer = CelestialGoldMuted,
    onPrimaryContainer = MoonIvory,
    secondary = MysticPurple,
    onSecondary = MoonIvory,
    secondaryContainer = MysticPurpleDeep,
    onSecondaryContainer = MoonIvory,
    tertiary = CelestialGoldBright,
    onTertiary = CelestialNavy,
    tertiaryContainer = CosmicSurfaceHighest,
    onTertiaryContainer = MoonIvory,
    background = CelestialNavy,
    onBackground = MoonIvory,
    surface = CosmicSurface,
    onSurface = MoonIvory,
    surfaceVariant = CosmicSurfaceElevated,
    onSurfaceVariant = MoonIvoryMuted,
    surfaceContainerLowest = CosmicAbyss,
    surfaceContainerLow = CelestialNavy,
    surfaceContainer = CosmicSurface,
    surfaceContainerHigh = CosmicSurfaceElevated,
    surfaceContainerHighest = CosmicSurfaceHighest,
    outline = CosmicOutline,
    outlineVariant = CosmicPurpleOutline,
    error = CosmicCrimson,
    onError = CosmicAbyss
)

@Composable
fun CosmicRangTheme(
    content: @Composable () -> Unit
) {
    val extendedColors = CosmicExtendedColors()
    CompositionLocalProvider(LocalCosmicColors provides extendedColors) {
        MaterialTheme(
            colorScheme = CosmicDarkColorScheme,
            typography = CosmicTypography,
            shapes = CosmicShapes,
            content = content
        )
    }
}
