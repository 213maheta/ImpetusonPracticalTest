package com.impetuson.practicaltest.retrofit

import com.impetuson.practicaltest.model.ResponseNews
import retrofit2.Response
import retrofit2.http.GET

interface NewsApiService {

    @GET("articles")
    suspend fun getNewsList():Response<List<ResponseNews>>
}