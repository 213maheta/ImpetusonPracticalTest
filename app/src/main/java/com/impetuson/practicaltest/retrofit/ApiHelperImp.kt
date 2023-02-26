package com.impetuson.practicaltest.retrofit

import com.impetuson.practicaltest.model.ResponseNews
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImp @Inject constructor(val newsService:NewsApiService):ApiHelper {

    override suspend fun getNewsList(): Response<List<ResponseNews>> {
        return newsService.getNewsList()
    }
}