package com.example.kotlinlearning.retrofit

import android.net.Network
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {

    val apiInterface: APIInterface by lazy {

        val retrofit = Retrofit.Builder()
                // The 10.0.2.2 address routes request from the Android emulator
                // to the localhost / 127.0.0.1 of the host PC
                .baseUrl("https://reqres.in/")
                // Moshi maps JSON to classes
                .addConverterFactory(GsonConverterFactory.create())
                // The call adapter handles threads
                .build()

        // Create Retrofit client
        return@lazy retrofit.create(APIInterface::class.java)
    }
}

