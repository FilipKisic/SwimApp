package hr.algebra.swimapp.api

import android.content.Context
import android.util.Log
import hr.algebra.swimapp.framework.sendBroadcast
import hr.algebra.swimapp.model.weather.WeatherInfo
import hr.algebra.swimapp.services.WeatherReceiver
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherFetcher(private val context: Context) {
    private var weatherAPI: WeatherAPI

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        weatherAPI = retrofit.create(WeatherAPI::class.java)
    }

    fun fetchData(lat: Double, long: Double) {
        val request = weatherAPI.getWeatherResponse(
            mapOf(
                "lat" to lat.toString(),
                "lon" to long.toString(),
                "units" to "metric",
                "exclude" to "minutely,hourly,daily,alerts",
                "appid" to API_KEY
            )
        )
        request.enqueue(object : Callback<WeatherInfo> {
            override fun onFailure(call: Call<WeatherInfo>, t: Throwable) {
                Log.d(javaClass.name, t.message, t)
            }

            override fun onResponse(call: Call<WeatherInfo>, response: Response<WeatherInfo>) {
                if (response.body() != null) {
                    printData(response.body()!!)
                }
            }

        })
    }

    private fun printData(data: WeatherInfo) {
        GlobalScope.launch {
            println("LAT: ${data.coord.lat}")
            println("LON: ${data.coord.lon}")
            println("DESC: ${data.weather.first().main}")
            println("TEMP: ${data.main.temp}")
            println("FEELS LIKE: ${data.main.feelsLike}")
            println("CITY: ${data.name}")
            context.sendBroadcast<WeatherReceiver>()
        }
    }
}