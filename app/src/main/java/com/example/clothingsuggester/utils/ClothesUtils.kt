package com.example.clothingsuggester.utils

import com.example.clothingsuggester.R
import com.example.clothingsuggester.model.Clothe

object ClothesUtils {
     fun clothesInit():MutableList<Clothe>{
        return mutableListOf(
            Clothe(1, R.drawable.hot1 , R.drawable.pant1 , 15),
            Clothe(2, R.drawable.hot2 , R.drawable.pant1 , 15),
            Clothe(3, R.drawable.hot3 , R.drawable.pant1 , 15),
            Clothe(4, R.drawable.sweet1 , R.drawable.pant1 , 9),
            Clothe(5, R.drawable.sweet2 , R.drawable.pant1 , 9),
            Clothe(6, R.drawable.sweet3 , R.drawable.pant1 , 9),
        )
    }

}