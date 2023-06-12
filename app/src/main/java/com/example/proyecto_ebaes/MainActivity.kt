package com.example.proyecto_ebaes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.proyecto_ebaes.data.room.StudentsDB
import com.example.proyecto_ebaes.data.room.viewModels.StudentViewModel
import com.example.proyecto_ebaes.navigation.SetupNavGraph
import com.example.proyecto_ebaes.ui.theme.Proyecto_EBAESTheme

class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            StudentsDB::class.java,
            "students.db"
        ).build()
    }

    private val viewModel by viewModels<StudentViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return StudentViewModel(db.studentDao) as T
                }
            }
        }
    )

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isDarkMode = remember {
                mutableStateOf(false)
            }
            Proyecto_EBAESTheme(darkTheme = isDarkMode.value) {
                val state by viewModel.state.collectAsState()
                val windowSize = calculateWindowSizeClass(activity = this)
                val navController = rememberNavController()

                SetupNavGraph(navController = navController, isDarkMode, windowSize, state)
            }
        }
    }
}