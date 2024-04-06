package com.vmc.prayertimes.data

import com.vmc.prayertimes.model.Prayer

object SampleData {
    val sampleTimes = listOf(
        Prayer("Fajr", "05:35", 25),
        Prayer("Zuhr", "12:28", 25),
        Prayer("Asr", "03:48", 25),
        Prayer("Maghrib", "06:20", 7),
        Prayer("Isha", "07:28", 25)
    )
}