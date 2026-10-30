package com.example.pantallas

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity

class HorizontalActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        setContentView(R.layout.activity_horizontal)
    }
}