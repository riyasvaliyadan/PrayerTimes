package com.vmc.prayertimes.alarm

import android.app.AlarmManager
import android.app.AlarmManager.AlarmClockInfo
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.RingtoneManager
import androidx.activity.ComponentActivity
import androidx.annotation.RawRes
import com.vmc.prayertimes.MainActivity
import com.vmc.prayertimes.R
import com.vmc.prayertimes.alarm.TimeProvider.Companion.getMillisForNextPrayer

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

        val alarmClockInfo = AlarmClockInfo(alarmTime, showIntent)
        alarmManager.setAlarmClock(alarmClockInfo, pendingIntent)
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
            // play Sound
            val mediaPlayer = MediaPlayer.create(context, ringtone)
            mediaPlayer.start()
        }
    }
}