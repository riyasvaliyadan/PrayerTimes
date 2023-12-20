package com.vmc.prayertimes.data

import java.time.LocalTime

/**
 * @param name name of the prayer
 * @param azan the time of azan
 * @param offset offset of start of prayer after azan
 */
data class Prayer(val name: String, val azan: String, private val offset: Int){
    val iqama: String get() = LocalTime.parse(azan).plusMinutes(offset.toLong()).toString()
}