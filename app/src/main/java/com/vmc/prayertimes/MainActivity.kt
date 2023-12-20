package com.vmc.prayertimes

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
import com.vmc.prayertimes.MyPreferenceManager.isItFirstStart
import com.vmc.prayertimes.alarm.MyAlarm
import com.vmc.prayertimes.data.Prayer
import com.vmc.prayertimes.data.TimeProvider
import com.vmc.prayertimes.data.TimeProvider.Companion.getTimeForNextFajr
import com.vmc.prayertimes.ui.theme.PrayerTimesTheme

const val PREF_NAME = "isFirstRun"

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // set alarm manager
        if (true && isItFirstStart(applicationContext)) { // todo always true
            MyAlarm.setAlarm(applicationContext, getTimeForNextFajr())
        }

        val times = TimeProvider.getSalahTimes(context = applicationContext)
        setContent {
            PrayerTimesTheme {
                Surface(modifier = Modifier.background(Color.Black)) {
                    MyApp(modifier = Modifier, times)
                }
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier, times: List<Prayer>) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(modifier = modifier, horizontalAlignment = Alignment.End) {
            times.forEach { prayer ->
                ListItem(prayer.name, prayer.azan, prayer.iqama)
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
fun ListItem(name: String = "PrayerName", time: String = "00:00 AM", iqama: String = "00:00") {
    Row(modifier = Modifier.padding(4.dp)) {
        Text(text = name, color = Color.DarkGray, fontSize = 22.sp, fontFamily = FontFamily(Font(R.font.roboto_regular)))
        Text(text = " $time", fontSize = 22.sp, fontFamily = FontFamily(Font(R.font.roboto_light)))
        Text(text = " $iqama", color = Color.Gray, fontSize = 22.sp, fontFamily = FontFamily(Font(R.font.roboto_light)))
    }
}

// todo: learn more about then implement this part
/*
@Preview (
    showBackground = false,
    widthDp = 300,
    heightDp = 600
)
@Composable
fun MyAppPreview() {
    PrayerTimesTheme {
        Surface {
            MyApp(modifier = Modifier, times = times)
        }
    }
}*/
