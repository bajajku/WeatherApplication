package sheridan.bajajku.assignment4.data.webdata

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import sheridan.bajajku.assignment4.domain.WeatherResponse


interface WeatherService {
    @GET("weather")
    suspend fun getWeather(
        @Query("q") location: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): Response<WeatherResponse>
}