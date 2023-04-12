package com.example.clothingsuggester.Interactor

import com.example.clothingsuggester.model.Clothe

class GetClothesByTmp(
    val clothesList : MutableList<Clothe>
    ) {

    fun execute(tmp:Double , id:Int): Clothe {
        clothesList.removeIf{clothe-> clothe.id ==id}
        return if(tmp>12) {
            clothesList.filter {it.tempAvg>12 }.random()
        }else{
            clothesList.filter { it.tempAvg<12}.random()
        }
    }
}