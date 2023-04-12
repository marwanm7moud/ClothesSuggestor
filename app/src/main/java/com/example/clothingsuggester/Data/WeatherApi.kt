package com.example.clothingsuggester.Data

import com.example.clothingsuggester.R
import com.example.clothingsuggester.model.Clothe
import com.example.clothingsuggester.model.WeatherModel
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class WeatherApi {
    private val client = OkHttpClient()

    fun getWeatherToday(network: INetwork) {
        network.onLoading()
        val request = Request.Builder().url(getUrl()).build()
        client.newCall(request).enqueue(object:Callback{
            override fun onFailure(call: Call, e: IOException) {
                e.message?.let {
                    network.onError(e.message!!)
                }
            }

            override fun onResponse(call: Call, response: Response) {
                var weatherModel: WeatherModel =
                    Gson().fromJson(response.body?.string() , WeatherModel::class.java)
                network.onSuccess(weatherModel)
            }

        })
    }
    private fun getUrl(): HttpUrl {
        return HttpUrl.Builder()
            .scheme("Https")
            .host(host)
            .addPathSegment("v4")
            .addPathSegment("weather")
            .addPathSegment("forecast")
            .addQueryParameter("location", "newyork")
            .addQueryParameter("timesteps", "1d")
            .addQueryParameter("apikey", apiKey)
            .build()
    }

    companion object {
        const val host = "api.tomorrow.io"
        const val apiKey = "8LGREjRJ124Pi3USUWxT7juX2SdCTf3M"
    }
}