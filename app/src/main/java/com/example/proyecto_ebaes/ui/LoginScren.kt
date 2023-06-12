package com.example.proyecto_ebaes.ui

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.proyecto_ebaes.R
import com.example.proyecto_ebaes.navigation.Screen
import com.example.proyecto_ebaes.sign_in.SignInState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(isDarkMode: MutableState<Boolean>, navController: NavHostController, state: SignInState, onSignInClick: () -> Unit) {
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()

        }
    }

    Scaffold(
        topBar = { LoginAppBar(isDarkMode) },
        content = {
            Box(modifier = Modifier.fillMaxSize()) {
                ScreenContent(navController, onSignInClick)
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
                text = "Iniciar Sesión", color = MaterialTheme.colorScheme.onBackground
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
fun ScreenContent(navController: NavHostController, onSignInClick: () -> Unit) {
    val correoState = remember { mutableStateOf(TextFieldValue()) }
    val passwordState = remember { mutableStateOf(TextFieldValue()) }

    val context = LocalContext.current

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
                text = "EBAES \n para investigadores",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 40.sp,
                modifier = Modifier.padding(vertical = 30.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.logo_ebaes),
                modifier = Modifier.size(250.dp),
                contentDescription = "logo"
            )
            /**
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
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            focusedLabelColor = MaterialTheme.colorScheme.onSurface,
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
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            focusedLabelColor = MaterialTheme.colorScheme.onSurface,
            containerColor = MaterialTheme.colorScheme.surface
            )
            )
             */

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                horizontalArrangement = Arrangement.Center
            ) {

                Button(
                    onClick = {
                        //Toast.makeText(context, "Login correcto", Toast.LENGTH_SHORT).show()
                        onSignInClick()
                        //navController.navigate(Screen.MainScreen.route)
                    },
                    modifier = Modifier
                        .width(150.dp)
                        .height(80.dp)
                ) {
                    Text(
                        text = "Login", fontSize = 25.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

                Spacer(modifier = Modifier.size(10.dp))

                /**
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
                 */
            }


        }
    }
}