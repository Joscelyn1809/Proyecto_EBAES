package com.example.proyecto_ebaes

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_ebaes.navigation.Screen
import com.example.proyecto_ebaes.sign_in.GoogleAuthUiClient
import com.example.proyecto_ebaes.sign_in.SignInViewModel
import com.example.proyecto_ebaes.ui.DetailScreen
import com.example.proyecto_ebaes.ui.ListScreen
import com.example.proyecto_ebaes.ui.LoginScreen
import com.example.proyecto_ebaes.ui.MainScreen
import com.example.proyecto_ebaes.ui.theme.Proyecto_EBAESTheme
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isDarkMode = remember {
                mutableStateOf(false)
            }
            Proyecto_EBAESTheme(darkTheme = isDarkMode.value) {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Screen.Login.route) {

                    composable(route = Screen.Login.route){
                        val signInViewModel = viewModel<SignInViewModel>()
                        val signInState by signInViewModel.state.collectAsState()

                        val launcher = rememberLauncherForActivityResult(
                            contract = ActivityResultContracts.StartIntentSenderForResult(),
                            onResult = {result ->
                                if(result.resultCode == RESULT_OK) {
                                    lifecycleScope.launch {
                                        val signInResult = googleAuthUiClient.getSignInWithIntent(
                                            intent = result.data ?: return@launch
                                        )
                                        signInViewModel.onSignInResult(signInResult)
                                    }
                                }

                            })

                        LaunchedEffect(key1 = signInState.isSignInSuccessful) {
                            if (signInState.isSignInSuccessful) {
                                Toast.makeText(applicationContext, "Sign in exitoso", Toast.LENGTH_SHORT).show()
                            }
                        }

                        LoginScreen(isDarkMode = isDarkMode, navController, signInState) {
                            lifecycleScope.launch{
                                val signInIntentSender = googleAuthUiClient.signIn()
                                launcher.launch(
                                    IntentSenderRequest.Builder(
                                        signInIntentSender ?: return@launch
                                    ).build()
                                )
                                Toast.makeText(applicationContext, "Intento de sign in", Toast.LENGTH_SHORT).show()
                            }
                        }
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
            //LoginScreen(isDarkMode)
            }
        }
    }
}