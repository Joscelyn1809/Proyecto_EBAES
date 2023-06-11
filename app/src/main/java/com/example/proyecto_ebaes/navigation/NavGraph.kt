package com.example.proyecto_ebaes.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.proyecto_ebaes.ui.DetailScreen
import com.example.proyecto_ebaes.ui.ListScreen
import com.example.proyecto_ebaes.ui.LoginScreen
import com.example.proyecto_ebaes.ui.MainScreen

@Composable
fun SetupNavGraph(navController: NavHostController, isDarkMode: MutableState<Boolean>) {
    NavHost(navController = navController, startDestination = Screen.Login.route) {

        composable(route = Screen.Login.route){
            LoginScreen(isDarkMode = isDarkMode, navController)
        }

        composable(route = Screen.MainScreen.route){
            MainScreen(navController)
        }

        composable(route = Screen.ListScreen.route){
            ListScreen(navController)
        }

        composable(route = Screen.DetailScreen.route){
            DetailScreen(navController)
        }
    }

}