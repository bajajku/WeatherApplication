package sheridan.bajajku.assignment4

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import sheridan.bajajku.assignment4.data.webdata.WeatherService
import sheridan.bajajku.assignment4.domain.WeatherResponse
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherService: WeatherService
) : ViewModel() {

    private val _weather = MutableLiveData<WeatherResponse>()
    val weather: LiveData<WeatherResponse> get() = _weather

    fun getWeather(location: String) {
        viewModelScope.launch {
            val apiKey = "5386e68ee61b73e93752a3fefb699178"
            val response = weatherService.getWeather(location, apiKey)
            _weather.value = response.body()
        }
    }
}