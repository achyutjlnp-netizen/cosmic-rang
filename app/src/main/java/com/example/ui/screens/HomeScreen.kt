package com.example.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.Brightness3
import androidx.compose.material.icons.outlined.Calculate
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.HomeWork
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.Psychology
import androidx.compose.material.icons.outlined.RecordVoiceOver
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.Translate
import androidx.compose.material.icons.outlined.VerifiedUser
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.core.i18n.AppLanguage
import com.example.core.i18n.LocalAppLanguage
import com.example.core.i18n.localizedString
import com.example.domain.model.ArchitecturePillar
import com.example.domain.model.CosmicCatalog
import com.example.domain.model.CosmicServiceSpec
import com.example.domain.model.PlatformRoleSpec
import com.example.domain.model.ServiceCategory
import com.example.domain.model.ServiceIconType
import com.example.ui.designsystem.CelestialDivider
import com.example.ui.designsystem.CosmicBadgePill
import com.example.ui.designsystem.CosmicCard
import com.example.ui.designsystem.CosmicFilterChip
import com.example.ui.designsystem.CosmicOutlinedButton
import com.example.ui.designsystem.CosmicPrimaryButton
import com.example.ui.theme.LocalCosmicColors
import com.example.ui.viewmodel.CosmicHomeUiState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    uiState: CosmicHomeUiState,
    onSelectCategory: (ServiceCategory) -> Unit,
    onInspectService: (CosmicServiceSpec?) -> Unit,
    onOpenDesignSystem: () -> Unit,
    modifier: Modifier = Modifier
) {
    val language = LocalAppLanguage.current
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        val isWideLayout = maxWidth >= 600.dp
        val servicesChunkSize = if (isWideLayout) 2 else 1

        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(bottom = 28.dp),
            modifier = Modifier
                .fillMaxSize()
                .testTag("home_lazy_column")
        ) {
            item(key = "hero") {
                CosmicHeroSection(
                    isWideLayout = isWideLayout,
                    onExploreServices = {
                        coroutineScope.launch {
                            listState.animateScrollToItem(index = 2)
                        }
                    },
                    onOpenDesignSystem = onOpenDesignSystem
                )
            }

            item(key = "pillars") {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier
                            .widthIn(max = 1040.dp)
                            .padding(horizontal = 16.dp, vertical = 20.dp)
                    ) {
                        SectionHeader(
                            title = localizedString(R.string.section_pillars_title),
                            subtitle = null
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        val pillarRows = CosmicCatalog.pillars.chunked(if (isWideLayout) 2 else 1)
                        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            pillarRows.forEach { rowPillars ->
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    rowPillars.forEach { pillar ->
                                        PillarCard(
                                            pillar = pillar,
                                            language = language,
                                            modifier = Modifier.weight(1f)
                                        )
                                    }
                                    if (rowPillars.size == 1 && isWideLayout) {
                                        Spacer(modifier = Modifier.weight(1f))
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        CelestialDivider()
                    }
                }
            }

            item(key = "services_header") {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier
                            .widthIn(max = 1040.dp)
                            .padding(horizontal = 16.dp)
                    ) {
                        SectionHeader(
                            title = localizedString(R.string.section_services_title),
                            subtitle = localizedString(R.string.section_services_subtitle)
                        )
                        Spacer(modifier = Modifier.height(14.dp))
                        ServiceFilterRow(
                            selectedCategory = uiState.selectedCategory,
                            onSelectCategory = onSelectCategory
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }

            item(key = "services_grid") {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    AnimatedContent(
                        targetState = uiState.filteredServices,
                        label = "services_filter_transition",
                        modifier = Modifier
                            .widthIn(max = 1040.dp)
                            .padding(horizontal = 16.dp)
                    ) { filtered ->
                        val rows = filtered.chunked(servicesChunkSize)
                        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            rows.forEach { rowItems ->
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    rowItems.forEach { service ->
                                        ServiceCard(
                                            service = service,
                                            language = language,
                                            onClick = { onInspectService(service) },
                                            modifier = Modifier.weight(1f)
                                        )
                                    }
                                    if (rowItems.size == 1 && servicesChunkSize == 2) {
                                        Spacer(modifier = Modifier.weight(1f))
                                    }
                                }
                            }
                        }
                    }
                }
            }

            item(key = "roles_section") {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier
                            .widthIn(max = 1040.dp)
                            .padding(horizontal = 16.dp, vertical = 24.dp)
                    ) {
                        CelestialDivider()
                        Spacer(modifier = Modifier.height(16.dp))
                        SectionHeader(
                            title = localizedString(R.string.section_roles_title),
                            subtitle = localizedString(R.string.section_roles_subtitle)
                        )
                        Spacer(modifier = Modifier.height(14.dp))
                        val roleRows = CosmicCatalog.roles.chunked(if (isWideLayout) 2 else 1)
                        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            roleRows.forEach { rowRoles ->
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    rowRoles.forEach { role ->
                                        RoleCard(
                                            role = role,
                                            language = language,
                                            modifier = Modifier.weight(1f)
                                        )
                                    }
                                    if (rowRoles.size == 1 && isWideLayout) {
                                        Spacer(modifier = Modifier.weight(1f))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    val activeService = uiState.inspectedService
    if (activeService != null) {
        val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        ModalBottomSheet(
            onDismissRequest = { onInspectService(null) },
            sheetState = sheetState,
            containerColor = LocalCosmicColors.current.cosmicSurfaceElevated,
            contentColor = LocalCosmicColors.current.moonIvory
        ) {
            ServiceDetailSheetContent(
                service = activeService,
                language = language,
                onDismiss = { onInspectService(null) }
            )
        }
    }
}

@Composable
private fun CosmicHeroSection(
    isWideLayout: Boolean,
    onExploreServices: () -> Unit,
    onOpenDesignSystem: () -> Unit
) {
    val colors = LocalCosmicColors.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            shape = MaterialTheme.shapes.large,
            color = colors.cosmicAbyss,
            border = BorderStroke(1.dp, colors.goldHairline),
            modifier = Modifier
                .widthIn(max = 1040.dp)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(if (isWideLayout) 360.dp else 400.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_cosmic_hero_1794859799204),
                    contentDescription = localizedString(R.string.brand_tagline),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Atmospheric Cosmic Navy + Mystic Purple directional scrim for text readability
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(
                                    colors.cosmicNavy.copy(alpha = 0.96f),
                                    colors.cosmicNavy.copy(alpha = 0.86f),
                                    colors.mysticPurpleDeep.copy(alpha = 0.45f)
                                )
                            )
                        )
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    colors.cosmicNavy.copy(alpha = 0.85f)
                                )
                            )
                        )
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 22.dp, vertical = 24.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.widthIn(max = 560.dp)) {
                        CosmicBadgePill(
                            text = localizedString(R.string.hero_eyebrow),
                            accentColor = colors.celestialGoldBright,
                            containerColor = colors.mysticPurpleDeep.copy(alpha = 0.72f)
                        )
                        Spacer(modifier = Modifier.height(14.dp))
                        Text(
                            text = localizedString(R.string.brand_tagline),
                            style = if (isWideLayout) {
                                MaterialTheme.typography.displayLarge
                            } else {
                                MaterialTheme.typography.displayMedium
                            },
                            color = colors.moonIvory
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = localizedString(R.string.hero_subtitle),
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = colors.moonIvoryMuted
                            ),
                            maxLines = 4,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        CosmicPrimaryButton(
                            text = localizedString(R.string.cta_explore_services),
                            onClick = onExploreServices,
                            leadingIcon = Icons.Rounded.AutoAwesome,
                            testTag = "hero_explore_services_btn"
                        )
                        CosmicOutlinedButton(
                            text = localizedString(R.string.cta_design_system),
                            onClick = onOpenDesignSystem,
                            leadingIcon = Icons.Outlined.Palette,
                            testTag = "hero_design_system_btn"
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SectionHeader(
    title: String,
    subtitle: String?
) {
    val colors = LocalCosmicColors.current
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            color = colors.moonIvory
        )
        if (!subtitle.isNullOrBlank()) {
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = colors.moonIvoryMuted
            )
        }
    }
}

@Composable
private fun PillarCard(
    pillar: ArchitecturePillar,
    language: AppLanguage,
    modifier: Modifier = Modifier
) {
    val colors = LocalCosmicColors.current
    val icon = when (pillar.id) {
        "ephemeris" -> Icons.Outlined.Explore
        "privacy" -> Icons.Outlined.Lock
        "server_ai" -> Icons.Outlined.Psychology
        else -> Icons.Outlined.Translate
    }
    CosmicCard(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(colors.mysticPurpleDeep)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = colors.celestialGold,
                    modifier = Modifier.size(20.dp)
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = pillar.title.resolve(language),
                    style = MaterialTheme.typography.titleMedium,
                    color = colors.moonIvory
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = pillar.description.resolve(language),
                    style = MaterialTheme.typography.bodySmall,
                    color = colors.moonIvoryMuted
                )
            }
        }
    }
}

@Composable
private fun ServiceFilterRow(
    selectedCategory: ServiceCategory,
    onSelectCategory: (ServiceCategory) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
    ) {
        val options = listOf(
            ServiceCategory.ALL to R.string.filter_all,
            ServiceCategory.CHARTS_AND_MATCH to R.string.filter_charts,
            ServiceCategory.GUIDANCE_AND_SPACE to R.string.filter_guidance,
            ServiceCategory.REPORTS_AND_CONSULTS to R.string.filter_consults
        )
        options.forEach { (category, labelRes) ->
            CosmicFilterChip(
                label = localizedString(labelRes),
                selected = category == selectedCategory,
                onClick = { onSelectCategory(category) },
                testTag = "filter_chip_${category.name.lowercase()}"
            )
        }
    }
}

@Composable
private fun ServiceCard(
    service: CosmicServiceSpec,
    language: AppLanguage,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = LocalCosmicColors.current
    CosmicCard(
        onClick = onClick,
        modifier = modifier.testTag("service_card_${service.id}"),
        contentPadding = PaddingValues(18.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(colors.celestialGoldSubtle)
            ) {
                Icon(
                    imageVector = serviceIcon(service.iconType),
                    contentDescription = null,
                    tint = colors.celestialGold,
                    modifier = Modifier.size(22.dp)
                )
            }
            CosmicBadgePill(
                text = localizedString(R.string.milestone_badge_planned),
                accentColor = colors.celestialGoldBright,
                containerColor = colors.cosmicNavy
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = service.title.resolve(language),
            style = MaterialTheme.typography.titleLarge,
            color = colors.moonIvory
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = service.subtitle.resolve(language),
            style = MaterialTheme.typography.bodyMedium,
            color = colors.moonIvoryMuted,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(14.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = service.deliveryMilestone.resolve(language),
                style = MaterialTheme.typography.labelSmall,
                color = colors.celestialGold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                contentDescription = null,
                tint = colors.celestialGold,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Composable
private fun RoleCard(
    role: PlatformRoleSpec,
    language: AppLanguage,
    modifier: Modifier = Modifier
) {
    val colors = LocalCosmicColors.current
    CosmicCard(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.VerifiedUser,
                    contentDescription = null,
                    tint = colors.celestialGold,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = role.title.resolve(language),
                    style = MaterialTheme.typography.titleMedium,
                    color = colors.moonIvory
                )
            }
            CosmicBadgePill(
                text = role.claimKey,
                accentColor = colors.moonIvory,
                containerColor = colors.mysticPurpleDeep
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = role.scope.resolve(language),
            style = MaterialTheme.typography.bodySmall,
            color = colors.moonIvoryMuted
        )
    }
}

@Composable
private fun ServiceDetailSheetContent(
    service: CosmicServiceSpec,
    language: AppLanguage,
    onDismiss: () -> Unit
) {
    val colors = LocalCosmicColors.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .padding(bottom = 32.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(colors.mysticPurpleDeep)
            ) {
                Icon(
                    imageVector = serviceIcon(service.iconType),
                    contentDescription = null,
                    tint = colors.celestialGold,
                    modifier = Modifier.size(24.dp)
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = service.title.resolve(language),
                    style = MaterialTheme.typography.headlineSmall,
                    color = colors.moonIvory
                )
                Text(
                    text = service.deliveryMilestone.resolve(language),
                    style = MaterialTheme.typography.labelMedium,
                    color = colors.celestialGold
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = service.subtitle.resolve(language),
            style = MaterialTheme.typography.bodyLarge,
            color = colors.moonIvory
        )

        Spacer(modifier = Modifier.height(18.dp))
        CosmicCard(
            highlighted = true,
            contentPadding = PaddingValues(14.dp)
        ) {
            Text(
                text = localizedString(R.string.sheet_required_inputs),
                style = MaterialTheme.typography.labelLarge,
                color = colors.celestialGold
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = service.requiredInputs.resolve(language),
                style = MaterialTheme.typography.bodyMedium,
                color = colors.moonIvoryMuted
            )
        }

        Spacer(modifier = Modifier.height(12.dp))
        CosmicCard(
            contentPadding = PaddingValues(14.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Security,
                    contentDescription = null,
                    tint = colors.emeraldStatus,
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = localizedString(R.string.sheet_integrity_note),
                    style = MaterialTheme.typography.labelLarge,
                    color = colors.emeraldStatus
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = service.integrityGuarantee.resolve(language),
                style = MaterialTheme.typography.bodyMedium,
                color = colors.moonIvoryMuted
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        CosmicPrimaryButton(
            text = localizedString(R.string.sheet_close),
            onClick = onDismiss,
            modifier = Modifier.fillMaxWidth(),
            testTag = "service_sheet_close_btn"
        )
    }
}

private fun serviceIcon(type: ServiceIconType): ImageVector = when (type) {
    ServiceIconType.VEDIC_ASTROLOGY -> Icons.Outlined.AutoAwesome
    ServiceIconType.KUNDALI_GENERATION -> Icons.Outlined.Explore
    ServiceIconType.KUNDALI_MATCHING -> Icons.Outlined.FavoriteBorder
    ServiceIconType.NUMEROLOGY -> Icons.Outlined.Calculate
    ServiceIconType.VASTU -> Icons.Outlined.HomeWork
    ServiceIconType.RASHIFAL -> Icons.Outlined.Brightness3
    ServiceIconType.AI_REPORTS -> Icons.Outlined.Psychology
    ServiceIconType.CONSULTATIONS -> Icons.Outlined.RecordVoiceOver
}
