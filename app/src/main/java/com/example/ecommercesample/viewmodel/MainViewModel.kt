package com.example.ecommercesample.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommercesample.model.Section
import com.example.ecommercesample.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    var sectionState = mutableStateOf<UiState<List<Section>>>(UiState.Loading)
        private set

    init {
        getSections()
    }

    private fun getSections() {
        viewModelScope.launch {
            try {
                val sections = repository.getSections()
                sectionState.value = UiState.Success(sections)
            } catch (e: Exception) {
                sectionState.value =
                    UiState.Error(e.localizedMessage ?: "An unknown error occurred")
            }
        }
    }
}
