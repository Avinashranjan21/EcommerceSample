package com.example.ecommercesample.repository

import com.example.ecommercesample.model.Section
import com.example.ecommercesample.network.ApiService
import com.example.ecommercesample.utils.NetworkHelper
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MainRepository @Inject constructor(
    private val apiService: ApiService,
    private val networkHelper: NetworkHelper
) {
    suspend fun getSections(): List<Section> = apiService.getSections()
}
