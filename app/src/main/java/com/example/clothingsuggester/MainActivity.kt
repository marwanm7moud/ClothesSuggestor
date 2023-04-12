package com.example.clothingsuggester

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.clothingsuggester.Interactor.GetClothesByTmp
import com.example.clothingsuggester.databinding.ActivityMainBinding
import com.example.clothingsuggester.Data.INetwork
import com.example.clothingsuggester.Data.WeatherApi
import com.example.clothingsuggester.model.Clothe
import com.example.clothingsuggester.model.WeatherModel
import com.example.clothingsuggester.utils.ClothesUtils
import com.example.clothingsuggester.utils.SharedPrefUtils

class MainActivity : AppCompatActivity(), INetwork {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setup()
    }


    override fun onSuccess(weatherResponse: WeatherModel) {
        weatherModel = weatherResponse
        lastClotheId = SharedPrefUtils.getIntPref(this , weatherResponse.timelines.daily[0].time)
        runOnUiThread {
            binding.progressBar.visibility = View.GONE
            if ( lastClotheId == Int.MIN_VALUE){
                binding.todayBtn.visibility = View.VISIBLE
            }else{
               updateOutfitView(clothesList.filter { it.id == lastClotheId }[0])
            }
        }
    }


    private fun todayOutfitButtonClicked(){
        binding.todayBtn.setOnClickListener {
            binding.todayBtn.visibility = View.GONE
           updateOutfitView(getClothe())
        }
    }

    private fun updateOutfitView(clothe: Clothe) {
        val time = weatherModel.timelines.daily[0].time
        val tmp = weatherModel.timelines.daily[0].values.temperatureApparentAvg
        binding.outfitView.visibility = View.VISIBLE
        SharedPrefUtils.setIntPref(this , weatherModel.timelines.daily[0].time,clothe.id)
        binding.time.text = time
        binding.tmp.text = tmp.toString()
        binding.upper.setImageResource(clothe.upper)
        binding.pants.setImageResource(clothe.pants)
    }


    override fun onError(message: String) {
        Log.d("TAG", message)
    }

    override fun onLoading() {
        runOnUiThread {
            binding.progressBar.visibility = View.VISIBLE
        }
    }

    private fun setup() {
        WeatherApi().getWeatherToday(this)
        todayOutfitButtonClicked()
    }

    private fun getClothe() = GetClothesByTmp(clothesList).execute(
        weatherModel.timelines.daily[0].values.temperatureApparentAvg,
        lastClotheId
    )

    companion object {
        private var clothesList = ClothesUtils.clothesInit()
        private lateinit var weatherModel: WeatherModel
        private var lastClotheId = Int.MIN_VALUE
    }
}
