package com.vmc.prayertimes.data

import android.content.Context
import android.util.Log
import com.vmc.prayertimes.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

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

        @OptIn(ExperimentalTime::class)
        private fun getRawData(context: Context, rawId: Int): String {
            var output: String
            val timeTaken = measureTime {
                val inputStream = context.resources.openRawResource(rawId)
                output = inputStream.bufferedReader().use { it.readText() }
            }
            Log.d("Riyas.Vmc", timeTaken.toString(DurationUnit.MILLISECONDS))
            return output
        }

        private fun getTodayDate(): String {
            val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
            return LocalDateTime.now().format(formatter)
        }

        fun getMillisForNextPrayer(context: Context): Long {
            // todo
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

        fun getPreviousTime(): Long {
            return 1701387960000
        }
    }
}