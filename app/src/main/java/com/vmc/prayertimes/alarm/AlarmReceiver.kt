package com.vmc.prayertimes.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import com.vmc.prayertimes.R
import com.vmc.prayertimes.data.TimeProvider

class AlarmReceiver : BroadcastReceiver() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onReceive(context: Context?, intent: Intent?) {
        if (Intent.ACTION_BOOT_COMPLETED == intent?.action) {
            if (context != null)
                MyAlarm.setAlarm(context, TimeProvider.getMillisForNextPrayer(context))
        } else {
            mediaPlayer = MediaPlayer.create(context, MyAlarm.ringtone)
            mediaPlayer.start()

            if (context != null) {
                MyAlarm.setAlarm(context, TimeProvider.getMillisForNextPrayer(context))
            }
        }
    }
}