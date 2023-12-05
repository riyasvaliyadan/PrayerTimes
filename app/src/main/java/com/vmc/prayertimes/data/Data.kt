package com.vmc.prayertimes.data

import android.content.Context
import com.vmc.prayertimes.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Data {
    companion object {
        fun getSalahTimes(context: Context): ArrayList<SalahTime> {
            var salahTimes = ArrayList<SalahTime>()

            val inputStream = context.resources.openRawResource(R.raw.data)
            val data = inputStream.bufferedReader().use { it.readText() }

            for (line in data.lines()) {
                val values = line.split(",")
                if (values[0] == getTodayDate()) {
                    salahTimes.add(SalahTime("Fajr", values[1]))
                    salahTimes.add(SalahTime("Zuhr", values[2]))
                    salahTimes.add(SalahTime("Asar", values[3]))
                    salahTimes.add(SalahTime("Magrib", values[4]))
                    salahTimes.add(SalahTime("Isha", values[5]))
                    break
                }
            }
            return salahTimes
        }

        private fun getTodayDate(): String {
            val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
            return LocalDateTime.now().format(formatter)
        }
    }
}