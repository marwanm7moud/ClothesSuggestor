package com.example.clothingsuggester.utils

import android.content.Context
import android.content.SharedPreferences
import java.util.prefs.Preferences

object SharedPrefUtils {
    fun setIntPref(context:Context , key:String , value:Int){
        var sharedPreferences = context.getSharedPreferences("prefs" ,Context.MODE_PRIVATE )
        var editor = sharedPreferences.edit()
        editor.putInt(key , value)
        editor.apply()
    }
    fun getIntPref(context:Context ,key:String): Int {
        var sharedPreferences = context.getSharedPreferences("prefs" ,Context.MODE_PRIVATE )
        return sharedPreferences.getInt(key , Int.MIN_VALUE)
    }
}