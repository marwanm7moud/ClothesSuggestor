package com.example.clothingsuggester.model

data class WeatherModel(
    val timelines: TimeLines,
    val location: Location
)

data class Location(
    val lat: Double,
    val lon: Double,
    val name: String,
    val type: String,
)
data class TimeLines(val daily: List<Daily>)
data class Daily(
    val time: String,
    val values: Values
)
data class Values(
    val temperatureApparentAvg: Double,
)
