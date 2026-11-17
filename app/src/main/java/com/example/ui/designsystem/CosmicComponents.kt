package com.example.ui.designsystem

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Architecture
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.rounded.Architecture
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Palette
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.core.i18n.AppLanguage
import com.example.core.i18n.localizedString
import com.example.domain.model.CosmicTab
import com.example.ui.theme.LocalCosmicColors

@Composable
fun CosmicTopAppBar(
    currentLanguage: AppLanguage,
    onSelectLanguage: (AppLanguage) -> Unit,
    onLogoClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = LocalCosmicColors.current
    Surface(
        color = colors.cosmicAbyss.copy(alpha = 0.94f),
        tonalElevation = 2.dp,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.statusBarsPadding()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(14.dp))
                        .clickable(onClick = onLogoClick)
                        .minimumInteractiveComponentSize()
                        .padding(end = 8.dp)
                        .testTag("top_bar_brand")
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_cosmic_rang_mark_1794859798146),
                        contentDescription = localizedString(R.string.brand_name),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .border(1.2.dp, colors.celestialGold, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = localizedString(R.string.brand_name),
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontFamily = MaterialTheme.typography.displayLarge.fontFamily,
                                fontWeight = FontWeight.SemiBold,
                                color = colors.celestialGold
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = localizedString(R.string.brand_tagline),
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = colors.moonIvoryMuted
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                CosmicLanguageToggle(
                    currentLanguage = currentLanguage,
                    onSelectLanguage = onSelectLanguage
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(
                                Color.Transparent,
                                colors.celestialGold.copy(alpha = 0.45f),
                                colors.mysticPurple.copy(alpha = 0.45f),
                                Color.Transparent
                            )
                        )
                    )
            )
        }
    }
}

@Composable
fun CosmicLanguageToggle(
    currentLanguage: AppLanguage,
    onSelectLanguage: (AppLanguage) -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = LocalCosmicColors.current
    val toggleDescription = localizedString(R.string.lang_toggle_cd)

    Surface(
        shape = RoundedCornerShape(999.dp),
        color = colors.cosmicSurface,
        border = BorderStroke(1.dp, colors.goldHairline),
        modifier = modifier.semantics { contentDescription = toggleDescription }
    ) {
        Row(
            modifier = Modifier.padding(3.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppLanguage.entries.forEach { language ->
                val isSelected = language == currentLanguage
                Surface(
                    shape = RoundedCornerShape(999.dp),
                    color = if (isSelected) colors.celestialGold else Color.Transparent,
                    modifier = Modifier
                        .clip(RoundedCornerShape(999.dp))
                        .clickable { onSelectLanguage(language) }
                        .minimumInteractiveComponentSize()
                        .testTag("lang_toggle_${language.code}")
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = language.shortLabel,
                            style = MaterialTheme.typography.labelMedium.copy(
                                color = if (isSelected) colors.cosmicNavy else colors.moonIvory,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CosmicPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    testTag: String = "cosmic_primary_button"
) {
    val colors = LocalCosmicColors.current
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colors.celestialGold,
            contentColor = colors.cosmicNavy
        ),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 14.dp),
        modifier = modifier
            .minimumInteractiveComponentSize()
            .testTag(testTag)
    ) {
        if (leadingIcon != null) {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun CosmicOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    testTag: String = "cosmic_outlined_button"
) {
    val colors = LocalCosmicColors.current
    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(14.dp),
        border = BorderStroke(1.2.dp, colors.celestialGold.copy(alpha = 0.72f)),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = colors.cosmicNavy.copy(alpha = 0.55f),
            contentColor = colors.moonIvory
        ),
        contentPadding = PaddingValues(horizontal = 18.dp, vertical = 14.dp),
        modifier = modifier
            .minimumInteractiveComponentSize()
            .testTag(testTag)
    ) {
        if (leadingIcon != null) {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                tint = colors.celestialGold,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun CosmicCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    highlighted: Boolean = false,
    contentPadding: PaddingValues = PaddingValues(18.dp),
    content: @Composable ColumnScope.() -> Unit
) {
    val colors = LocalCosmicColors.current
    val borderBrush = Brush.linearGradient(
        colors = if (highlighted) {
            listOf(
                colors.celestialGold.copy(alpha = 0.75f),
                colors.mysticPurple.copy(alpha = 0.65f)
            )
        } else {
            listOf(
                colors.goldHairline,
                colors.purpleHairline
            )
        }
    )

    Surface(
        shape = MaterialTheme.shapes.medium,
        color = if (highlighted) colors.cosmicSurfaceElevated else colors.cosmicSurface,
        tonalElevation = if (highlighted) 4.dp else 1.dp,
        border = BorderStroke(1.dp, borderBrush),
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .then(
                if (onClick != null) {
                    Modifier
                        .clickable(onClick = onClick)
                        .minimumInteractiveComponentSize()
                } else {
                    Modifier
                }
            )
    ) {
        Column(
            modifier = Modifier.padding(contentPadding),
            content = content
        )
    }
}

@Composable
fun CosmicBadgePill(
    text: String,
    accentColor: Color,
    containerColor: Color,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(999.dp),
        color = containerColor,
        border = BorderStroke(0.8.dp, accentColor.copy(alpha = 0.45f)),
        modifier = modifier
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall.copy(
                color = accentColor,
                fontWeight = FontWeight.SemiBold
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
        )
    }
}

@Composable
fun CosmicFilterChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    testTag: String = ""
) {
    val colors = LocalCosmicColors.current
    Surface(
        shape = RoundedCornerShape(999.dp),
        color = if (selected) colors.celestialGold else colors.cosmicSurface,
        border = BorderStroke(
            width = 1.dp,
            color = if (selected) colors.celestialGold else colors.goldHairline
        ),
        modifier = modifier
            .clip(RoundedCornerShape(999.dp))
            .clickable(onClick = onClick)
            .minimumInteractiveComponentSize()
            .then(if (testTag.isNotEmpty()) Modifier.testTag(testTag) else Modifier)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium.copy(
                    color = if (selected) colors.cosmicNavy else colors.moonIvory,
                    fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun CelestialDivider(
    modifier: Modifier = Modifier
) {
    val colors = LocalCosmicColors.current
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(20.dp)
    ) {
        val centerY = size.height / 2f
        val centerX = size.width / 2f
        val starRadius = 6.dp.toPx()
        val gap = 14.dp.toPx()

        drawLine(
            brush = Brush.horizontalGradient(
                colors = listOf(Color.Transparent, colors.celestialGold.copy(alpha = 0.65f)),
                startX = 0f,
                endX = centerX - gap
            ),
            start = Offset(0f, centerY),
            end = Offset((centerX - gap).coerceAtLeast(0f), centerY),
            strokeWidth = 1.dp.toPx()
        )

        drawLine(
            brush = Brush.horizontalGradient(
                colors = listOf(colors.celestialGold.copy(alpha = 0.65f), Color.Transparent),
                startX = centerX + gap,
                endX = size.width
            ),
            start = Offset((centerX + gap).coerceAtMost(size.width), centerY),
            end = Offset(size.width, centerY),
            strokeWidth = 1.dp.toPx()
        )

        val diamond = Path().apply {
            moveTo(centerX, centerY - starRadius)
            lineTo(centerX + starRadius * 0.65f, centerY)
            lineTo(centerX, centerY + starRadius)
            lineTo(centerX - starRadius * 0.65f, centerY)
            close()
        }
        drawPath(path = diamond, color = colors.celestialGold)
    }
}

@Composable
fun CosmicBottomNavigationBar(
    selectedTab: CosmicTab,
    onSelectTab: (CosmicTab) -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = LocalCosmicColors.current
    NavigationBar(
        containerColor = colors.cosmicAbyss,
        contentColor = colors.moonIvory,
        windowInsets = WindowInsets.navigationBars,
        modifier = modifier
    ) {
        CosmicTab.entries.forEach { tab ->
            val selected = tab == selectedTab
            val (labelRes, selectedIcon, unselectedIcon) = tabMeta(tab)
            NavigationBarItem(
                selected = selected,
                onClick = { onSelectTab(tab) },
                icon = {
                    Icon(
                        imageVector = if (selected) selectedIcon else unselectedIcon,
                        contentDescription = localizedString(labelRes)
                    )
                },
                label = {
                    Text(
                        text = localizedString(labelRes),
                        style = MaterialTheme.typography.labelSmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = colors.cosmicNavy,
                    selectedTextColor = colors.celestialGold,
                    indicatorColor = colors.celestialGold,
                    unselectedIconColor = colors.moonIvoryMuted,
                    unselectedTextColor = colors.moonIvoryMuted
                ),
                modifier = Modifier.testTag("nav_tab_${tab.name.lowercase()}")
            )
        }
    }
}

@Composable
fun CosmicNavigationRail(
    selectedTab: CosmicTab,
    onSelectTab: (CosmicTab) -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = LocalCosmicColors.current
    NavigationRail(
        containerColor = colors.cosmicAbyss,
        contentColor = colors.moonIvory,
        modifier = modifier.padding(top = 12.dp)
    ) {
        CosmicTab.entries.forEach { tab ->
            val selected = tab == selectedTab
            val (labelRes, selectedIcon, unselectedIcon) = tabMeta(tab)
            NavigationRailItem(
                selected = selected,
                onClick = { onSelectTab(tab) },
                icon = {
                    Icon(
                        imageVector = if (selected) selectedIcon else unselectedIcon,
                        contentDescription = localizedString(labelRes)
                    )
                },
                label = {
                    Text(
                        text = localizedString(labelRes),
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                colors = NavigationRailItemDefaults.colors(
                    selectedIconColor = colors.cosmicNavy,
                    selectedTextColor = colors.celestialGold,
                    indicatorColor = colors.celestialGold,
                    unselectedIconColor = colors.moonIvoryMuted,
                    unselectedTextColor = colors.moonIvoryMuted
                ),
                modifier = Modifier
                    .padding(vertical = 6.dp)
                    .testTag("rail_tab_${tab.name.lowercase()}")
            )
        }
    }
}

private data class TabMeta(
    val labelRes: Int,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

private fun tabMeta(tab: CosmicTab): TabMeta = when (tab) {
    CosmicTab.HOME -> TabMeta(
        labelRes = R.string.nav_home,
        selectedIcon = Icons.Rounded.Home,
        unselectedIcon = Icons.Outlined.Home
    )
    CosmicTab.DESIGN_SYSTEM -> TabMeta(
        labelRes = R.string.nav_design,
        selectedIcon = Icons.Rounded.Palette,
        unselectedIcon = Icons.Outlined.Palette
    )
    CosmicTab.ROADMAP -> TabMeta(
        labelRes = R.string.nav_architecture,
        selectedIcon = Icons.Rounded.Architecture,
        unselectedIcon = Icons.Outlined.Architecture
    )
}
