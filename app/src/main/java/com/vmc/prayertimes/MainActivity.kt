package com.vmc.prayertimes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vmc.prayertimes.data.Data
import com.vmc.prayertimes.data.SalahTime
import com.vmc.prayertimes.ui.theme.PrayerTimesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var prayerTimes = Data.getSalahTimes(applicationContext)

        setContent {
            PrayerTimesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp(modifier = Modifier, prayerTimes)
                }
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier, times: ArrayList<SalahTime>) {
    Box {
        Column(modifier = modifier.align(Alignment.TopStart)) {
            times.forEach { prayer ->
                ListItem(prayer.name, prayer.time)
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
fun ListItem(name: String = "PrayerName", time: String = "00:00 AM") {
    Row(modifier = Modifier.padding(4.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(text = name, style = MaterialTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.Bold, fontSize = 20.sp
        ))
        Text(text = " : $time", fontSize = 20.sp)
    }
}