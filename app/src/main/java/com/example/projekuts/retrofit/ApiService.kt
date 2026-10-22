package com.example.projekuts.retrofit

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.projekuts.model.ProductResponse

interface ApiService {
    @GET("products")
    fun getProducts(
        @Query("limit") limit: Int = 25,
        @Query("select") select: String =
            "id,title,description,category,price,stock,availabilityStatus," +
                    "tags,dimensions,reviews,rating,images,thumbnail"
    ): Call<ProductResponse>
}

object RetrofitClient {
    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}