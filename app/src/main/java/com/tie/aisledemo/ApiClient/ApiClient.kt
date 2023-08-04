package com.tie.aisledemo.ApiClient

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val baseURL = "https://app.aisle.co/V1/"
    private var retrofit: Retrofit? = null
    @JvmStatic
    fun getclient(): Retrofit? {
        if (retrofit == null) {
            val client = OkHttpClient.Builder().build()
            val gson = GsonBuilder().setLenient().create()
            retrofit = Retrofit.Builder().baseUrl(baseURL).client(client)
                .addConverterFactory(GsonConverterFactory.create(gson)).build()
        }
        return retrofit
    }
}