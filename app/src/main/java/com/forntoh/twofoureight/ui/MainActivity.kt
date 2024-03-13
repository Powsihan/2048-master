package com.forntoh.twofoureight.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.forntoh.twofoureight.store.PreferenceRepository
import kotlinx.coroutines.*

class MainActivity : ComponentActivity() {

    private val preferenceRepository: PreferenceRepository by lazy { PreferenceRepository(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        preferenceRepository.useSystemUiMode = true

        setContent {
            LoadingScreen()
        }

        CoroutineScope(Dispatchers.Main).launch {
            delay(2000) // 2000 milliseconds = 2 seconds
            setContent {
                GameApp()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        preferenceRepository.paused = true
    }

}