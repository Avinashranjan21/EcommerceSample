package com.example.ecommercesample.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
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
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                ShimmeringLoadingScreen()
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
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Error: ${state.message}",
                    color = Color.Red,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

