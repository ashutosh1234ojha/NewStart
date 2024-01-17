package com.example.newstart.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitBuilder {
    private var retrofit: Retrofit? = null
val baseUrl="http://10.0.2.2:5001/"
//val baseUrl="http://127.0.0.1:5001/"


    fun getClient(): Retrofit? {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        //   val client: OkHttpClient = Builder().addInterceptor(interceptor).build()
        retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(client)
            .build()
        return retrofit
    }
}