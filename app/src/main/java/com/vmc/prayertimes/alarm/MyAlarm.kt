package com.vmc.prayertimes.alarm

import android.app.AlarmManager
import android.app.AlarmManager.AlarmClockInfo
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import androidx.activity.ComponentActivity
import androidx.annotation.RawRes
import com.vmc.prayertimes.MainActivity
import com.vmc.prayertimes.R
import com.vmc.prayertimes.data.TimeProvider.Companion.getMillisForNextPrayer
import com.vmc.prayertimes.data.TimeProvider.Companion.getNextMinuteMillis
import com.vmc.prayertimes.data.TimeProvider.Companion.getPreviousTime

object MyAlarm {
    @RawRes private val ringtone = R.raw.azan_ringtone
    private const val ID_CODE = 0

    fun setAlarm(context: Context) {
        val alarmTime = getMillisForNextPrayer(context)
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val i = Intent(context, AlarmReceiver::class.java)
        val intent = Intent(context, MainActivity::class.java)
        val showIntent = PendingIntent.getActivity(context, ID_CODE, intent, PendingIntent.FLAG_MUTABLE)
        val pendingIntent = PendingIntent.getBroadcast(context, ID_CODE, i, PendingIntent.FLAG_MUTABLE)

        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, alarmTime, pendingIntent)

        /*val alarmClockInfo = AlarmClockInfo(alarmTime, showIntent)
        alarmManager.setAlarmClock(alarmClockInfo, pendingIntent)*/
    }

    fun cancelAlarm(context: Context) {
        val alarmManager = context.getSystemService(ComponentActivity.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, ID_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        alarmManager.cancel(pendingIntent)
    }

    fun playSound(context: Context) {
        val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        if (audioManager.ringerMode == AudioManager.RINGER_MODE_NORMAL) {
            // Play the sound
            val mediaPlayer = MediaPlayer.create(context, ringtone).apply {
                setVolume(1F, 1F)
            }
            mediaPlayer.start()
        }

    }
}