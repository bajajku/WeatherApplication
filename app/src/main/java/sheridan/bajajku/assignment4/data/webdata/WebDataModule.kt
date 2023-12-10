package sheridan.bajajku.assignment4.data.webdata

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WebDataModule {

    @Singleton
    @Provides
    fun provideRetrofitCampus(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(UrlData.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        //.create(WeatherService::class.java)
    }

    @Singleton
    @Provides
    fun provideWebDataApi(retrofit: Retrofit): WeatherService {
        return retrofit.create(WeatherService::class.java)
    }
}