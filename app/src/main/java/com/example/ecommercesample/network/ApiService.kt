package com.example.ecommercesample.network

import com.example.ecommercesample.model.Section
import retrofit2.http.GET

interface ApiService {
    @GET("5BEJ")
    suspend fun getSections(): List<Section>
}