package eu.rafaelsantiago.rimluas.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.rafaelsantiago.rimluas.data.repository.LuasRepository
import eu.rafaelsantiago.rimluas.domain.model.LuasStopForecastResponse
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    val forecastData = MutableLiveData<LuasStopForecastResponse>()
    private val repository = LuasRepository()

    fun getStopForecast(luasStopAbbrev: String) {
        viewModelScope.launch {
            try {
                forecastData.value =  repository.getStopForecast(luasStopAbbrev)
            } catch(e:Exception) {
                Log.d(this@MainViewModel::class.simpleName, "Error -> ${e.stackTrace}")
            }
        }
    }
}