package com.example.ecommercesample.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

suspend fun checkNetworkConnectivity(
    context: Context,
    scope: CoroutineScope,
    onConnected: suspend () -> Unit,
    onDisconnected: suspend () -> Unit
) {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            scope.launch {
                onConnected()
            }
        }

        override fun onLost(network: Network) {
            scope.launch {
                onDisconnected()
            }
        }
    }

    val networkRequest = android.net.NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .build()

    withContext(Dispatchers.IO) {
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }
}

class NetworkHelper @Inject constructor(private val context: Context) {

    fun isNetworkAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
    }
}

