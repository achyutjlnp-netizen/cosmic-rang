package com.example

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.core.i18n.LocalAppLanguage
import com.example.core.i18n.LocalLocalizedContext
import com.example.core.i18n.rememberLocalizedContext
import com.example.domain.model.CosmicTab
import com.example.ui.designsystem.CosmicBottomNavigationBar
import com.example.ui.designsystem.CosmicNavigationRail
import com.example.ui.designsystem.CosmicTopAppBar
import com.example.ui.screens.ArchitectureScreen
import com.example.ui.screens.DesignSystemScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.theme.CosmicRangTheme
import com.example.ui.theme.LocalCosmicColors
import com.example.ui.viewmodel.CosmicHomeViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color.parseColor("#10112B")),
            navigationBarStyle = SystemBarStyle.dark(Color.parseColor("#090A1D"))
        )
        setContent {
            CosmicRangTheme {
                CosmicRangApp()
            }
        }
    }
}

@Composable
fun CosmicRangApp(
    viewModel: CosmicHomeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val localizedContext = rememberLocalizedContext(uiState.language)
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val colors = LocalCosmicColors.current

    BackHandler(enabled = uiState.selectedTab != CosmicTab.HOME) {
        viewModel.selectTab(CosmicTab.HOME)
    }

    CompositionLocalProvider(
        LocalAppLanguage provides uiState.language,
        LocalLocalizedContext provides localizedContext
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.cosmicNavy)
        ) {
            val useNavigationRail = maxWidth >= 600.dp

            Scaffold(
                containerColor = colors.cosmicNavy,
                contentWindowInsets = WindowInsets(0, 0, 0, 0),
                topBar = {
                    CosmicTopAppBar(
                        currentLanguage = uiState.language,
                        onSelectLanguage = viewModel::setLanguage,
                        onLogoClick = { viewModel.selectTab(CosmicTab.HOME) }
                    )
                },
                bottomBar = {
                    if (!useNavigationRail) {
                        CosmicBottomNavigationBar(
                            selectedTab = uiState.selectedTab,
                            onSelectTab = viewModel::selectTab
                        )
                    }
                },
                snackbarHost = {
                    SnackbarHost(hostState = snackbarHostState) { data ->
                        Snackbar(
                            snackbarData = data,
                            containerColor = colors.cosmicSurfaceElevated,
                            contentColor = colors.moonIvory,
                            actionColor = colors.celestialGold
                        )
                    }
                }
            ) { innerPadding ->
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    if (useNavigationRail) {
                        CosmicNavigationRail(
                            selectedTab = uiState.selectedTab,
                            onSelectTab = viewModel::selectTab
                        )
                    }

                    Box(modifier = Modifier.weight(1f)) {
                        Crossfade(
                            targetState = uiState.selectedTab,
                            label = "main_tab_crossfade"
                        ) { tab ->
                            when (tab) {
                                CosmicTab.HOME -> HomeScreen(
                                    uiState = uiState,
                                    onSelectCategory = viewModel::selectCategory,
                                    onInspectService = viewModel::inspectService,
                                    onOpenDesignSystem = { viewModel.selectTab(CosmicTab.DESIGN_SYSTEM) }
                                )

                                CosmicTab.DESIGN_SYSTEM -> DesignSystemScreen(
                                    onShowMessage = { msg ->
                                        coroutineScope.launch {
                                            snackbarHostState.currentSnackbarData?.dismiss()
                                            snackbarHostState.showSnackbar(msg)
                                        }
                                    }
                                )

                                CosmicTab.ROADMAP -> ArchitectureScreen(
                                    expandedMilestoneNumber = uiState.expandedMilestoneNumber,
                                    onToggleMilestone = viewModel::toggleMilestone
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
