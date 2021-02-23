package com.example.kotlinlearning.retrofit

import com.example.kotlinlearning.model.UsersResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {

    @GET("/api/users")  // endpoint will be appended to the base url
    fun getUsers(@Query("page") page: Int): Call<UsersResponse>


}