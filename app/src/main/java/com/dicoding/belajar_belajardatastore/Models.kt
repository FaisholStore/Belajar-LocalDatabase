package com.dicoding.belajar_belajardatastore

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dicoding.belajar_belajardatastore.Manager.DataStoreManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Models(application: Application) : AndroidViewModel(application) {
    private val dataStore = DataStoreManager(application)

    val getTheme = dataStore.getTheme().asLiveData(Dispatchers.IO)

    fun setTheme(isDarkMode: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStore.setTheme(isDarkMode)
        }
    }
}
