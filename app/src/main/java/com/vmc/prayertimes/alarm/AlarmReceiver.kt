package com.vmc.prayertimes.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.util.Log
import com.vmc.prayertimes.data.TimeProvider

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (Intent.ACTION_BOOT_COMPLETED == intent?.action) {
            if (context != null) {
                MyAlarm.setAlarm(context)
            }
        } else {
            if (context != null) {
                MyAlarm.playSound(context)
                MyAlarm.setAlarm(context)
            }
        }
    }
}