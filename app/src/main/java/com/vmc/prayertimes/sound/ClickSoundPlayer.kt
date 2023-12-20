package com.vmc.prayertimes.sound

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import com.vmc.prayertimes.R

const val STREAM_COUNT = 1

class ClickSoundPlayer(context: Context) {
    private val clickSound: Int
    private val audioAttributes: AudioAttributes = AudioAttributes.Builder()
        .setUsage(AudioAttributes.USAGE_GAME)
        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
        .build()
    private val soundPool: SoundPool = SoundPool.Builder()
        .setMaxStreams(STREAM_COUNT)
        .setAudioAttributes(audioAttributes)
        .build()

    init {
        clickSound = soundPool.load(context, R.raw.click_3, 1)
    }
    fun play() {
        soundPool.play(clickSound, 1f, 1f, 0, 0, 1f)
    }
}
