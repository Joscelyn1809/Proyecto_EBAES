package com.example.proyecto_ebaes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_ebaes.navigation.SetupNavGraph
import com.example.proyecto_ebaes.ui.theme.Proyecto_EBAESTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isDarkMode = remember {
                mutableStateOf(false)
            }
            Proyecto_EBAESTheme(darkTheme = isDarkMode.value) {
                val navController = rememberNavController()
                SetupNavGraph(navController = navController, isDarkMode)
            //LoginScreen(isDarkMode)
            }
        }
    }
}