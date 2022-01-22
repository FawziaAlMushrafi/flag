package com.example.flag.overview


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flag.internet.Api
import com.example.flag.internet.DataItem
import kotlinx.coroutines.launch

class OverviewViewModel : ViewModel() {
    private var _numberOfCountry = MutableLiveData<String>()
    val numberOfCountry: LiveData<String> = _numberOfCountry
    private var _data = MutableLiveData<List<DataItem>>()
    val data: MutableLiveData<List<DataItem>> = _data
    private var _falg = MutableLiveData<String>()
    val flag: LiveData<String> = _falg
    private var _counterName = MutableLiveData<String>()
    val counterName: LiveData<String> = _counterName

    init {
        getNumberOfCountry()
    }

    private fun getNumberOfCountry() {
        viewModelScope.launch {
            try {
                _data.value = Api.retrofitService.getCountry().data
                _falg.value = _data.value!![0].flag
                _counterName.value = _data.value!![0].name
                _numberOfCountry.value = _data.value!!.size.toString()
            } catch (e: Exception) {
                _numberOfCountry.value = "Failure: ${e.message}"
            }
        }
    }
}