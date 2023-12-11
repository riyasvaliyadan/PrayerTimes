package com.vmc.prayertimes.data

import android.content.Context
import com.vmc.prayertimes.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Data {
    companion object {
        fun getSalahTimes(context: Context): List<SalahTime> {
            val salahTimes = ArrayList<SalahTime>()

            for (line in getRawData(context, R.raw.data).lines()) {
                val values = line.split(",")
                if (values[0] == getTodayDate()) {
                    salahTimes.add(SalahTime("fajr", values[1]))
                    salahTimes.add(SalahTime("zuhr", values[2]))
                    salahTimes.add(SalahTime("asr", values[3]))
                    salahTimes.add(SalahTime("maghrib", values[4]))
                    salahTimes.add(SalahTime("isha", values[5]))
                    break
                }
            }
            return salahTimes
        }

        private fun getRawData(context: Context, rawId: Int): String {
            val inputStream = context.resources.openRawResource(rawId)
            return inputStream.bufferedReader().use { it.readText() }
        }

        private fun getTodayDate(): String {
            val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
            return LocalDateTime.now().format(formatter)
        }
    }
}