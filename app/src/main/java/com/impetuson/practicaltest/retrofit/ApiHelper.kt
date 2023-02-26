package com.impetuson.practicaltest.retrofit

import com.impetuson.practicaltest.model.ResponseNews
import retrofit2.Response

interface ApiHelper {
    suspend fun getNewsList(): Response<List<ResponseNews>>
}