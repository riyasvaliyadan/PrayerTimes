package com.vmc.prayertimes.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.vmc.prayertimes.data.TimeProvider
import com.vmc.prayertimes.sound.ClickSoundPlayer

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (Intent.ACTION_BOOT_COMPLETED == intent?.action) {
            // reset all alarms
            // todo
        } else {
            // perform scheduled task here (eg. send alarm notification)
            Log.d("Riyas.Vmc", "Alarming...")
            context?.let { ClickSoundPlayer(it).play() }
            context?.let { MyAlarm.setAlarm(it, TimeProvider.getTimeForNextFajr()) }
        }
    }
}