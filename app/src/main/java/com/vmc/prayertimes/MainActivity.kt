package com.vmc.prayertimes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.vmc.prayertimes.alarm.MyAlarm
import com.vmc.prayertimes.ui.screen.MainViewModel
import com.vmc.prayertimes.ui.screen.MyApp
import com.vmc.prayertimes.ui.theme.PrayerTimesTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // set alarm
        MyAlarm.setAlarm(applicationContext)

        val viewModel = MainViewModel(application)

        setContent {
            PrayerTimesTheme {
                val times by viewModel.timesLiveData.observeAsState()
                Surface(modifier = Modifier.background(Color.Black)) {
                    MyApp(times = times!!)
                }
            }
        }
    }
}
