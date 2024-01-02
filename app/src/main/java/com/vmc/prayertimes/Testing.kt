package com.vmc.prayertimes

import android.content.Context
import java.util.Calendar

object Testing {
    fun getNextHalfMinuteMillis(context: Context): Long {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.SECOND, 30)
        return calendar.timeInMillis
    }
}