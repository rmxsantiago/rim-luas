package eu.rafaelsantiago.rimluas.presentation.main.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.rafaelsantiago.rimluas.data.repository.LuasRepository
import eu.rafaelsantiago.rimluas.domain.enum.LuasStopEnum
import eu.rafaelsantiago.rimluas.domain.model.Direction
import eu.rafaelsantiago.rimluas.domain.model.LuasStopForecastResponse
import kotlinx.coroutines.launch
import java.time.LocalTime

class MainViewModel: ViewModel() {
    val forecastData = MutableLiveData<LuasStopForecastResponse>()
    private val repository = LuasRepository()

    fun getStopForecast() {
        viewModelScope.launch {
            try {
                val timeNow = LocalTime.now()
                val luasStop = evaluateTime(timeNow)
                forecastData.value =  repository.getStopForecast(luasStop.abbrev)
            } catch(e:Exception) {
                Log.d(this@MainViewModel::class.simpleName, "Error -> ${e.stackTrace}")
            }
        }
    }

    fun evaluateTime(currentTime: LocalTime): LuasStopEnum {
        var luasStop = LuasStopEnum.MARLBOROUGH

        if (currentTime.isAfter(LocalTime.NOON)) {
            luasStop = LuasStopEnum.STILLORGAN
        }

        return luasStop
    }

    fun getTramDirection(luasStopForecastResponse: LuasStopForecastResponse): Direction {
        var directionString = "Outbound"

        val timeNow = LocalTime.now()

        val luasStopEnum = evaluateTime(timeNow)
        if (luasStopEnum == LuasStopEnum.STILLORGAN) {
            directionString = "Inbound"
        }

        return luasStopForecastResponse.lines.filter { direction -> direction.name == directionString }[0]
    }


}