package com.vmc.prayertimes.alarm

import android.app.AlarmManager
import android.app.AlarmManager.AlarmClockInfo
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import com.vmc.prayertimes.MainActivity
import com.vmc.prayertimes.R

object MyAlarm {

    val ringtone = R.raw.azan_ringtone
    private const val ID_CODE = 0

    fun setAlarm(context: Context, timeInMillis: Long) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val i = Intent(context, AlarmReceiver::class.java)
        val intent = Intent(context, MainActivity::class.java)
        val showIntent = PendingIntent.getActivity(context, ID_CODE, intent, PendingIntent.FLAG_MUTABLE)
        val pendingIntent = PendingIntent.getBroadcast(context, ID_CODE, i, PendingIntent.FLAG_MUTABLE)
        val alarmClockInfo = AlarmClockInfo(timeInMillis, showIntent)

//        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
        alarmManager.setAlarmClock(alarmClockInfo, pendingIntent)
    }

    fun cancelAlarm(context: Context) {
        val alarmManager = context.getSystemService(ComponentActivity.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, ID_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        alarmManager.cancel(pendingIntent)
    }
}