package com.vmc.prayertimes

import android.content.Context
import androidx.activity.ComponentActivity

object MyPreferenceManager {

    private const val PACKAGE_NAME = "com.vmc.prayer_time"
    private const val PREF_NAME = "isFirstRun"

    fun isItFirstStart(context: Context): Boolean {
        val sharedPreferences = context.getSharedPreferences(PACKAGE_NAME, ComponentActivity.MODE_PRIVATE)
        return sharedPreferences.getBoolean(PREF_NAME, true)
    }

    fun setNotFirstRun(context: Context) {
        val sharedPreferences = context.getSharedPreferences(PACKAGE_NAME, ComponentActivity.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean(PREF_NAME, false).apply()
    }
}