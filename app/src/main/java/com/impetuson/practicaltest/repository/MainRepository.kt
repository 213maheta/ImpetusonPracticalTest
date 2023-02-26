package com.impetuson.practicaltest.repository

import com.aves.practicaltest.sealed.NetworkResult
import com.impetuson.practicaltest.model.ResponseNews
import com.impetuson.practicaltest.retrofit.ApiHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {

    private var _responseType = MutableStateFlow<NetworkResult>(NetworkResult.Empty)
    var responseType = _responseType.asStateFlow()

    var newList = listOf<ResponseNews>()

    suspend fun getEmployee()
    {
        _responseType.value = NetworkResult.Loading
        try {
            val response = apiHelper.getNewsList()
            if (response.code() == 200) {
                response.body()?.let {
                    newList = it
                    _responseType.value = NetworkResult.Success(newList)
                }
            } else {
                _responseType.value = NetworkResult.Error<String>(response.message())
            }
        } catch (e: Exception) {
            _responseType.value = NetworkResult.Error<String>(e.message?:"")
        }
    }

    suspend fun filterNewsList(query:String)
    {
        val filteredList = newList.filter {
            it.title?.lowercase()?.contains(query.lowercase()) == true
        }
        _responseType.value = NetworkResult.Success(filteredList)
    }

}