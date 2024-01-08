package com.vmc.prayertimes

import android.content.Context

object Utility {
    fun getRawData(context: Context, rawId: Int): String {
        val inputStream = context.resources.openRawResource(rawId)
        return inputStream.bufferedReader().use { it.readText() }
    }
}