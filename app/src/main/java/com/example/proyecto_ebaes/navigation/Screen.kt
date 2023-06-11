package com.example.proyecto_ebaes.navigation

sealed class Screen(val route: String) {
    object Login : Screen(route = "login_screen")
    object MainScreen: Screen(route = "main_screen")
}
