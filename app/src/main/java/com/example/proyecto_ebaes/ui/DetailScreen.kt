package com.example.proyecto_ebaes.ui

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.proyecto_ebaes.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavHostController) {
    Scaffold(
        topBar = { DetailsAppBar(navController) },
        content = {
            DetailContent(navController)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsAppBar(navController: NavHostController) {
    TopAppBar(
        title = {
            Text(
                text = "Detalles del alumno", color = MaterialTheme.colorScheme.onBackground
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { navController.popBackStack() },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_ios_new_24),
                    contentDescription = "back",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
    )
}

@Composable
fun DetailContent(navController: NavHostController) {

    val context = LocalContext.current
    val boxBackground = Color(0x4196979A)

    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            contentAlignment = Alignment.TopCenter
        )
        {
            Column(
                Modifier.padding(horizontal = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_account_circle_24),
                    contentDescription = "picture",
                    Modifier.size(150.dp)
                )

                Spacer(Modifier.size(8.dp))

                Text(
                    text = "Joscelyn Pauleth Covarrubias Murrieta",
                    textAlign = TextAlign.Center,
                    lineHeight = 30.sp,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )
                Spacer(modifier = Modifier.size(5.dp))
                Text(
                    text = "18/Sep/2001",
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Light
                )
            }
        }

        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxWidth()
                .padding(15.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(boxBackground)
        ) {
            Column(Modifier.padding(10.dp)) {
                Text(
                    text = "Correo",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Light
                )
                Text(
                    text = "Universidad",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Light
                )
                Text(
                    text = "Carrera",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Light
                )
                Text(
                    text = "Estado, Ciudad",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Light
                )
                Text(
                    text = "Promedio",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Light
                )
                Text(
                    text = "Semestre",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Light
                )
                Text(
                    text = "Sexo",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Light
                )
                Text(
                    text = "Estado Civil",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Light
                )
                Text(
                    text = "Transtorno",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Light
                )
                Text(
                    text = "Tratamiento",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Light
                )
            }
        }

        Box(
            Modifier
                .fillMaxWidth()
                .padding(20.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            Button(
                onClick = {
                    Toast.makeText(context, "Alumno guardado", Toast.LENGTH_SHORT).show()
                },
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
            ) {
                Text(
                    text = "Guardar alumno",
                    fontSize = 15.sp,
                    color = MaterialTheme.colorScheme.onTertiary
                )
            }
        }
    }
}