package com.vmc.prayertimes.alarm

import android.content.Context
import com.vmc.prayertimes.R
import com.vmc.prayertimes.model.Prayer
import com.vmc.prayertimes.util.Utility.getRawData
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

class TimeProvider {
    companion object {
        fun getSalahTimes(context: Context): List<Prayer> {
            val salahTimes = ArrayList<Prayer>()

            for (line in getRawData(context, R.raw.data).lines()) {
                val values = line.split(",")
                if (values[0] == getTodayDate()) {
                    salahTimes.add(Prayer("fajr", values[1], 25))
                    salahTimes.add(Prayer("zuhr", values[2], 25))
                    salahTimes.add(Prayer("asr", values[3], 20))
                    salahTimes.add(Prayer("maghrib", values[4], 7))
                    salahTimes.add(Prayer("isha", values[5], 25))
                    break
                }
            }
            return salahTimes
        }

        private fun getTodayDate(): String {
            val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
            return LocalDateTime.now().format(formatter)
        }

        fun getMillisForNextPrayer(context: Context): Long {
            val times = getRawData(context, R.raw.time_millis)

            // current time
            val calendar = Calendar.getInstance()
            val currentTimeInMillis = calendar.timeInMillis

            times.split("\n")
                .map { it.toLong() }
                .forEach { time ->
                    if (time > currentTimeInMillis) {
                        return time
                    }
                }
            return Long.MAX_VALUE
        }

        fun getMillisForTimeAfterOneMinute(context: Context): Long {
            // current time
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.SECOND, 15)
            return calendar.timeInMillis
        }
    }
}