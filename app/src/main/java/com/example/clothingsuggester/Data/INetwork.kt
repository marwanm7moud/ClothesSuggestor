package com.example.clothingsuggester.Data

import com.example.clothingsuggester.model.WeatherModel

interface INetwork {
    fun onSuccess(weatherResponse: WeatherModel)
    fun onError(message: String)
    fun onLoading()
}