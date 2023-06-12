package com.example.proyecto_ebaes.navigation

import android.app.Activity.RESULT_OK
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.proyecto_ebaes.sign_in.SignInViewModel
import com.example.proyecto_ebaes.ui.DetailScreen
import com.example.proyecto_ebaes.ui.ListScreen
import com.example.proyecto_ebaes.ui.LoginScreen
import com.example.proyecto_ebaes.ui.MainScreen

@Composable
fun SetupNavGraph(navController: NavHostController, isDarkMode: MutableState<Boolean>) {

}