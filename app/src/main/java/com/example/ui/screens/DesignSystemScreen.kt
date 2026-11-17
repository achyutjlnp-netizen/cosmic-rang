package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ContentCopy
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.core.i18n.LocalAppLanguage
import com.example.core.i18n.localizedString
import com.example.domain.model.CosmicCatalog
import com.example.ui.designsystem.CelestialDivider
import com.example.ui.designsystem.CosmicBadgePill
import com.example.ui.designsystem.CosmicCard
import com.example.ui.designsystem.CosmicFilterChip
import com.example.ui.designsystem.CosmicOutlinedButton
import com.example.ui.designsystem.CosmicPrimaryButton
import com.example.ui.theme.LocalCosmicColors

@Composable
fun DesignSystemScreen(
    onShowMessage: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = LocalCosmicColors.current
    val language = LocalAppLanguage.current
    val clipboard = LocalClipboardManager.current
    val copiedLabel = localizedString(R.string.copied_token_feedback)
    val previewToast = localizedString(R.string.preview_interactive_toast)
    var sampleChipActive by remember { mutableStateOf(true) }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .widthIn(max = 960.dp)
                .fillMaxSize()
                .testTag("design_system_list")
        ) {
            item(key = "header") {
                Column {
                    CosmicBadgePill(
                        text = localizedString(R.string.milestone_badge_m1),
                        accentColor = colors.celestialGoldBright,
                        containerColor = colors.mysticPurpleDeep
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = localizedString(R.string.design_title),
                        style = MaterialTheme.typography.headlineLarge,
                        color = colors.moonIvory
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = localizedString(R.string.design_subtitle),
                        style = MaterialTheme.typography.bodyMedium,
                        color = colors.moonIvoryMuted
                    )
                }
            }

            item(key = "palette") {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(
                        text = localizedString(R.string.design_palette_title),
                        style = MaterialTheme.typography.titleLarge,
                        color = colors.celestialGold
                    )
                    CosmicCatalog.colorTokens.forEach { token ->
                        Surface(
                            shape = MaterialTheme.shapes.medium,
                            color = colors.cosmicSurface,
                            border = BorderStroke(1.dp, colors.goldHairline),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(MaterialTheme.shapes.medium)
                                .clickable {
                                    clipboard.setText(AnnotatedString(token.hex))
                                    onShowMessage("$copiedLabel: ${token.hex}")
                                }
                                .minimumInteractiveComponentSize()
                                .testTag("swatch_${token.hex}")
                        ) {
                            Row(
                                modifier = Modifier.padding(14.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(14.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(48.dp)
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(token.color)
                                )
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = "${token.name.resolve(language)} · ${token.hex}",
                                        style = MaterialTheme.typography.titleMedium,
                                        color = colors.moonIvory
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = token.usage.resolve(language),
                                        style = MaterialTheme.typography.bodySmall,
                                        color = colors.moonIvoryMuted
                                    )
                                }
                                Icon(
                                    imageVector = Icons.Outlined.ContentCopy,
                                    contentDescription = copiedLabel,
                                    tint = colors.celestialGold,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }
                }
            }

            item(key = "typography") {
                CosmicCard(highlighted = true) {
                    Text(
                        text = localizedString(R.string.design_type_title),
                        style = MaterialTheme.typography.titleLarge,
                        color = colors.celestialGold
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Display · Cinzel",
                        style = MaterialTheme.typography.headlineMedium,
                        color = colors.moonIvory
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Title · Plus Jakarta Sans SemiBold",
                        style = MaterialTheme.typography.titleMedium,
                        color = colors.moonIvory
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Body · Clean multilingual legibility across English and हिन्दी (Devanagari).",
                        style = MaterialTheme.typography.bodyMedium,
                        color = colors.moonIvoryMuted
                    )
                }
            }

            item(key = "components") {
                CosmicCard {
                    Text(
                        text = localizedString(R.string.design_components_title),
                        style = MaterialTheme.typography.titleLarge,
                        color = colors.celestialGold
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        CosmicPrimaryButton(
                            text = localizedString(R.string.cta_explore_services),
                            onClick = { onShowMessage(previewToast) },
                            leadingIcon = Icons.Rounded.AutoAwesome,
                            modifier = Modifier.weight(1f),
                            testTag = "ds_preview_primary_btn"
                        )
                        CosmicOutlinedButton(
                            text = localizedString(R.string.nav_design),
                            onClick = { onShowMessage(previewToast) },
                            modifier = Modifier.weight(1f),
                            testTag = "ds_preview_outlined_btn"
                        )
                    }
                    Spacer(modifier = Modifier.height(14.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CosmicFilterChip(
                            label = localizedString(R.string.filter_charts),
                            selected = sampleChipActive,
                            onClick = { sampleChipActive = !sampleChipActive },
                            testTag = "ds_sample_chip"
                        )
                        CosmicBadgePill(
                            text = "role: customer",
                            accentColor = colors.emeraldStatus,
                            containerColor = colors.cosmicNavy
                        )
                    }
                    Spacer(modifier = Modifier.height(14.dp))
                    CelestialDivider()
                }
            }
        }
    }
}
