package com.dicoding.belajar_belajardatastore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.dicoding.belajar_belajardatastore.Manager.DataStoreManager
import com.dicoding.belajar_belajardatastore.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: Models
    private lateinit var dataStoreManager: DataStoreManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this@MainActivity, ViewModelProvider.AndroidViewModelFactory(application)).get(Models::class.java)
        dataStoreManager = DataStoreManager(this)

        checkThemeMode()

        binding.apply {
            modeSwitch.setOnCheckedChangeListener { _, isChecked ->
                lifecycleScope.launch {
                    when (isChecked) {
                        true -> viewModel.setTheme(true)
                        false -> viewModel.setTheme(false)
                    }
                }
            }
        }
    }
    private fun checkThemeMode() {
        binding.apply {
            viewModel.getTheme.observe(this@MainActivity) { isDarkMode ->
                when (isDarkMode) {
                    true -> {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                        modeSwitch.isChecked = true
                        modeSwitch.text="Dark Mode"
                        imgStatus.setAnimation(R.raw.night)
                    }
                    false -> {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                        modeSwitch.isChecked = false
                        modeSwitch.text="Light Mode"
                        imgStatus.setAnimation(R.raw.day)
                    }
                }
            }
        }
    }
}