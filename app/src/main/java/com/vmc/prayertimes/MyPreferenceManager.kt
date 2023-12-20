package com.vmc.prayertimes

import android.content.Context
import androidx.activity.ComponentActivity

object MyPreferenceManager {
    fun isItFirstStart(context: Context): Boolean {
        val sharedPreferences = context.getSharedPreferences("com.vmc.prayer_time", ComponentActivity.MODE_PRIVATE)
        val result = sharedPreferences.getBoolean(PREF_NAME, true)
        if (result) sharedPreferences.edit().putBoolean(PREF_NAME, false).apply()
        return result
    }
}