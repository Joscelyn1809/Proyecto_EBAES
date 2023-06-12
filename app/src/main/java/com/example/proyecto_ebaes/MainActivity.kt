package com.example.proyecto_ebaes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_ebaes.navigation.SetupNavGraph
import com.example.proyecto_ebaes.ui.theme.Proyecto_EBAESTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isDarkMode = remember {
                mutableStateOf(false)
            }
            Proyecto_EBAESTheme(darkTheme = isDarkMode.value) {
                val windowSize = calculateWindowSizeClass(activity = this)
                val navController = rememberNavController()

                SetupNavGraph(navController = navController, isDarkMode, windowSize)
            }
        }
    }
}