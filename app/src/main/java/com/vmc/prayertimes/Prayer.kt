package com.vmc.prayertimes

import java.time.LocalTime

data class Prayer(val name: String, val azan: String, private val offset: Int){
    val iqama: String get() = LocalTime.parse(azan).plusMinutes(offset.toLong()).toString()
}