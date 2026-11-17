package com.example.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.core.i18n.AppLanguage
import com.example.domain.model.CosmicCatalog
import com.example.domain.model.CosmicServiceSpec
import com.example.domain.model.CosmicTab
import com.example.domain.model.ServiceCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class CosmicHomeUiState(
    val language: AppLanguage = AppLanguage.ENGLISH,
    val selectedTab: CosmicTab = CosmicTab.HOME,
    val selectedCategory: ServiceCategory = ServiceCategory.ALL,
    val services: List<CosmicServiceSpec> = CosmicCatalog.services,
    val inspectedService: CosmicServiceSpec? = null,
    val expandedMilestoneNumber: Int = 1
) {
    val filteredServices: List<CosmicServiceSpec>
        get() = if (selectedCategory == ServiceCategory.ALL) {
            services
        } else {
            services.filter { it.category == selectedCategory }
        }
}

class CosmicHomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CosmicHomeUiState())
    val uiState: StateFlow<CosmicHomeUiState> = _uiState.asStateFlow()

    fun setLanguage(language: AppLanguage) {
        _uiState.update { it.copy(language = language) }
    }

    fun selectTab(tab: CosmicTab) {
        _uiState.update { it.copy(selectedTab = tab) }
    }

    fun selectCategory(category: ServiceCategory) {
        _uiState.update { it.copy(selectedCategory = category) }
    }

    fun inspectService(service: CosmicServiceSpec?) {
        _uiState.update { it.copy(inspectedService = service) }
    }

    fun toggleMilestone(number: Int) {
        _uiState.update { current ->
            val next = if (current.expandedMilestoneNumber == number) -1 else number
            current.copy(expandedMilestoneNumber = next)
        }
    }
}
