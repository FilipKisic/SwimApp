package hr.algebra.swimapp.api

import hr.algebra.swimapp.model.weather.WeatherInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

const val API_KEY = "cb778593405e9e1413cad2657433ff0f"
const val API_URL = "https://api.openweathermap.org/data/2.5/"
//const val API_URL_PARAMS = "metric&exclude=minutely,hourly,daily,alerts&appid=$API_KEY"

interface WeatherAPI {
    @GET("weather")
    fun getWeatherResponse(@QueryMap options: Map<String, String>): Call<WeatherInfo>
}