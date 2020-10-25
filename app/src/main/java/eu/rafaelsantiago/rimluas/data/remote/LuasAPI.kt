package eu.rafaelsantiago.rimluas.data.remote

import eu.rafaelsantiago.rimluas.domain.model.LuasStopForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface LuasAPI {
    @GET("get.ashx?action=forecast&encrypt=false")
    suspend fun getStopForecast(@Query("stop") luasStopAbreviation: String) : LuasStopForecastResponse
}