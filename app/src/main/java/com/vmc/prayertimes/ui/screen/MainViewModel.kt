package com.vmc.prayertimes.ui.screen

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vmc.prayertimes.alarm.TimeProvider
import com.vmc.prayertimes.model.Prayer
import kotlinx.coroutines.launch

class MainViewModel(private val application: Application) : ViewModel() {

    private val _times = MutableLiveData<List<Prayer>>().apply { value = emptyList() }
    val timesLiveData: LiveData<List<Prayer>> = _times

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    init {
        fetchPrayerTimes() // Load data on initialization
    }

    private fun fetchPrayerTimes() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val times = TimeProvider.getSalahTimes(context = application)
                _times.value = times
            } catch (e: Exception) {
                _error.value = e.localizedMessage
            } finally {
                _isLoading.value = false
            }
        }
    }

    /**
     * Refresh prayer times manually.
     */
    fun refreshPrayerTimes() {
        fetchPrayerTimes()
    }
}