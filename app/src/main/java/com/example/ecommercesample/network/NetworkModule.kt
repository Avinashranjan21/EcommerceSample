package com.example.ecommercesample.network

import android.app.Application
import android.content.Context
import com.example.ecommercesample.utils.NetworkHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideCache(appContext: Context): Cache {
        val cacheSize = (10 * 1024 * 1024).toLong()
        val cacheDirectory = File(appContext.cacheDir, "http_cache")
        return Cache(cacheDirectory, cacheSize)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(cache: Cache, networkHelper: NetworkHelper): OkHttpClient {
        val onlineInterceptor = Interceptor { chain ->
            val response = chain.proceed(chain.request())
            val maxAge = 60
            response.newBuilder()
                .header("Cache-Control", "public, max-age=$maxAge")
                .build()
        }

        val offlineInterceptor = Interceptor { chain ->
            var request = chain.request()
            if (!networkHelper.isNetworkAvailable()) {
                val maxStale = 60 * 60 * 24 * 7 // Offline cache available for 7 days
                request = request.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                    .build()
            }
            chain.proceed(request)
        }

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(offlineInterceptor)
            .addNetworkInterceptor(onlineInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.jsonkeeper.com/b/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideNetworkHelper(appContext: Context): NetworkHelper {
        return NetworkHelper(appContext)
    }

    @Singleton
    @Provides
    fun provideApplicationContext(application: Application): Context {
        return application.applicationContext
    }
}
