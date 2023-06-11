package com.example.proyecto_ebaes.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.proyecto_ebaes.R
import com.example.proyecto_ebaes.navigation.Screen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(isDarkMode: MutableState<Boolean>, navController: NavHostController) {
    Scaffold(
        topBar = { LoginAppBar(isDarkMode) },
        content = {
            Box(modifier = Modifier.fillMaxSize()) {
                ScreenContent(navController)
            }
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginAppBar(isDarkMode: MutableState<Boolean>) {
    TopAppBar(
        title = {
            Text(
                text = "", color = MaterialTheme.colorScheme.onBackground
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    isDarkMode.value = !isDarkMode.value
                }, //modifier = Modifier.padding(start = 20.dp)
            ) {
                Icon(
                    painter = painterResource(
                        id = if (isDarkMode.value) R.drawable.baseline_brightness_medium_24
                        else R.drawable.baseline_brightness_2_24
                    ),
                    contentDescription = "color",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenContent(navController: NavHostController) {
    val correoState = remember { mutableStateOf(TextFieldValue()) }
    val passwordState = remember { mutableStateOf(TextFieldValue()) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Iniciar Sesión",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(vertical = 30.dp)
            )

            TextField(
                value = correoState.value,
                onValueChange = {
                    correoState.value = it
                },
                placeholder = {
                    Text(text = "Correo")
                },
                shape = CircleShape,
                modifier = Modifier.wrapContentWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = MaterialTheme.colorScheme.onBackground,
                    placeholderColor = MaterialTheme.colorScheme.onSurface,
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )

            Spacer(modifier = Modifier.size(12.dp))

            TextField(
                value = passwordState.value,
                onValueChange = {
                    passwordState.value = it
                },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                placeholder = {
                    Text(text = "Password")
                },
                shape = CircleShape,
                modifier = Modifier.wrapContentWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = MaterialTheme.colorScheme.onBackground,
                    placeholderColor = MaterialTheme.colorScheme.onSurface,
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                horizontalArrangement = Arrangement.Center
            ) {

                Button(
                    onClick = { navController.navigate(Screen.MainScreen.route) },
                    modifier = Modifier.width(125.dp)
                ) {
                    Text(text = "Login", fontSize = 20.sp)
                }

                Spacer(modifier = Modifier.size(10.dp))

                Button(
                    onClick = { navController.navigate(Screen.MainScreen.route) },
                    modifier = Modifier.width(125.dp),
                    enabled = false,
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiary)
                ) {
                    Text(
                        text = "No se",
                        fontSize = 20.sp
                    )
                }
            }


        }
    }
}