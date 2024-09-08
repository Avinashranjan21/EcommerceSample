package com.example.ecommercesample.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ecommercesample.model.SectionType.BANNER
import com.example.ecommercesample.model.SectionType.HORIZONTAL_FREE_SCROLL
import com.example.ecommercesample.model.SectionType.SPLIT_BANNER
import com.example.ecommercesample.viewmodel.MainViewModel
import com.example.ecommercesample.viewmodel.UiState

@Composable
fun SectionListScreen(viewModel: MainViewModel = hiltViewModel()) {
    when (val state = viewModel.sectionState.value) {
        is UiState.Loading -> {
            Column(modifier = Modifier.fillMaxSize(),
            ) {
                ShimmerBannerSection()
                ShimmerHorizontalFreeScrollSection()
                ShimmerSplitBannerSection()
            }
        }

        is UiState.Success -> {
            LazyColumn {
                items(state.data) { section ->
                    when (section.sectionType) {
                        BANNER -> BannerSection(items = section.items)
                        HORIZONTAL_FREE_SCROLL -> HorizontalFreeScrollSection(items = section.items)
                        SPLIT_BANNER -> SplitBannerSection(items = section.items)
                    }
                }
            }
        }

        is UiState.Error -> {
            ErrorScreen(errorMessage = state.message)
        }
    }
}

