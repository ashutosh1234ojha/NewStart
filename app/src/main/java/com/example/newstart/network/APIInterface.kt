package com.example.newstart.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface APIInterface {
    @GET("/")
    fun doGetListResources(): Call<String?>?
//
//    @POST("/api/users")
//    fun createUser(@Body user: User?): Call<User?>?
}