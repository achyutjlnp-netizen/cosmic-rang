package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExpandLess
import androidx.compose.material.icons.rounded.ExpandMore
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.core.i18n.LocalAppLanguage
import com.example.core.i18n.localizedString
import com.example.domain.model.CosmicCatalog
import com.example.ui.designsystem.CosmicBadgePill
import com.example.ui.designsystem.CosmicCard
import com.example.ui.theme.LocalCosmicColors

@Composable
fun ArchitectureScreen(
    expandedMilestoneNumber: Int,
    onToggleMilestone: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = LocalCosmicColors.current
    val language = LocalAppLanguage.current

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .widthIn(max = 960.dp)
                .fillMaxSize()
                .testTag("architecture_roadmap_list")
        ) {
            item(key = "arch_header") {
                Column(modifier = Modifier.padding(bottom = 6.dp)) {
                    Text(
                        text = localizedString(R.string.arch_title),
                        style = MaterialTheme.typography.headlineLarge,
                        color = colors.moonIvory
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = localizedString(R.string.arch_subtitle),
                        style = MaterialTheme.typography.bodyMedium,
                        color = colors.moonIvoryMuted
                    )
                }
            }

            items(
                items = CosmicCatalog.milestones,
                key = { it.number }
            ) { milestone ->
                val expanded = expandedMilestoneNumber == milestone.number
                CosmicCard(
                    highlighted = milestone.isActive,
                    onClick = { onToggleMilestone(milestone.number) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("milestone_card_${milestone.number}")
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(
                                    if (milestone.isActive) colors.celestialGold
                                    else colors.mysticPurpleDeep
                                )
                        ) {
                            Text(
                                text = milestone.number.toString(),
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = if (milestone.isActive) colors.cosmicNavy else colors.moonIvory
                                )
                            )
                        }

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = milestone.title.resolve(language),
                                style = MaterialTheme.typography.titleMedium,
                                color = colors.moonIvory
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            CosmicBadgePill(
                                text = localizedString(
                                    if (milestone.isActive) R.string.milestone_badge_m1
                                    else R.string.milestone_badge_planned
                                ),
                                accentColor = if (milestone.isActive) colors.emeraldStatus else colors.celestialGold,
                                containerColor = colors.cosmicNavy
                            )
                        }

                        Icon(
                            imageVector = if (expanded) Icons.Rounded.ExpandLess else Icons.Rounded.ExpandMore,
                            contentDescription = null,
                            tint = colors.celestialGold
                        )
                    }

                    AnimatedVisibility(visible = expanded) {
                        Column(modifier = Modifier.padding(top = 12.dp)) {
                            Text(
                                text = milestone.summary.resolve(language),
                                style = MaterialTheme.typography.bodyMedium,
                                color = colors.moonIvory
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = milestone.deliverables.resolve(language),
                                style = MaterialTheme.typography.bodySmall,
                                color = colors.celestialGoldBright
                            )
                        }
                    }
                }
            }
        }
    }
}
