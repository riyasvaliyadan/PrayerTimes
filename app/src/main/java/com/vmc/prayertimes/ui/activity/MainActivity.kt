package com.vmc.prayertimes.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vmc.prayertimes.R
import com.vmc.prayertimes.alarm.MyAlarm
import com.vmc.prayertimes.model.Prayer
import com.vmc.prayertimes.ui.screen.HomeViewModel
import com.vmc.prayertimes.ui.screen.MyApp
import com.vmc.prayertimes.ui.theme.PrayerTimesTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // set alarm
        MyAlarm.setAlarm(applicationContext)

        // Using Dependency Injection (recommended)
        // val viewModel: MyViewModel by viewModels()

        // Direct instantiation (consider using a factory for complex dependencies)
        val viewModel = HomeViewModel(application)
        var times: List<Prayer> = listOf()

        viewModel.timesLiveData.observe(this) {
            times = it
        }

        setContent {
            PrayerTimesTheme {
                Surface(modifier = Modifier.background(Color.Black)) {
                    MyApp(times = times)
                }
            }
        }
    }
}
