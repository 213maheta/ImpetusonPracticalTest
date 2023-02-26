package com.impetuson.practicaltest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aves.practicaltest.sealed.MainEvent
import com.aves.practicaltest.sealed.NetworkResult
import com.impetuson.practicaltest.model.ResponseNews
import com.impetuson.practicaltest.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val mainRepository: MainRepository):ViewModel() {

    private var _event = MutableStateFlow<MainEvent>(MainEvent.Idle)
    var event = _event.asStateFlow()

    fun getNewsList() = viewModelScope.launch{
        mainRepository.getEmployee()
    }

    fun filterNewsList(query:String) = viewModelScope.launch {
        mainRepository.filterNewsList(query)
    }

    fun setObserver() = viewModelScope.launch {
        mainRepository.responseType.collect{
            when(it)
            {
                NetworkResult.Loading->{
                    _event.value = MainEvent.ShowProgressBar
                }
                is NetworkResult.Success<*>->{
                    _event.value = MainEvent.ResponseList(it.data as List<ResponseNews>)
                }
                is NetworkResult.Error<*>->{
                    _event.value = MainEvent.ShowToast<String>(it.message)
                }
                else -> {}
            }
        }
    }

}