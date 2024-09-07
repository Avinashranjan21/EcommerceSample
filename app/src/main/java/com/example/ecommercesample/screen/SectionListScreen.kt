package com.example.ecommercesample.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ecommercesample.viewmodel.MainViewModel
import com.example.ecommercesample.viewmodel.UiState

@Composable
fun SectionListScreen(viewModel: MainViewModel = hiltViewModel()) {
    val state = viewModel.sectionState.value

    when (state) {
        is UiState.Loading -> {
            // Show loading indicator while data is being fetched
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                ShimmeringLoadingScreen()
            }
        }
        is UiState.Success -> {
            Text(text = state.data.toString())
            // Display the list when data is successfully fetched
//            LazyColumn {
//                items(state.data) { section ->
//                    when (section.sectionType) {
//                        "banner" -> BannerSection(items = section.items)
//                        "horizontalFreeScroll" -> HorizontalFreeScrollSection(items = section.items)
//                        "splitBanner" -> SplitBannerSection(items = section.items)
//                    }
//                }
//            }
        }
        is UiState.Error -> {
            // Show error message if something goes wrong
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

