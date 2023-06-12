package com.example.proyecto_ebaes.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.proyecto_ebaes.R
import com.example.proyecto_ebaes.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListScreen(navController: NavHostController, windowSize: WindowSizeClass) {
    Scaffold(
        topBar = { ListAppBar(navController) },
        content = {
            AdaptableList(windowSize.widthSizeClass, navController)
            //ListContent(navController, windowSize)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListAppBar(navController: NavHostController) {
    TopAppBar(
        title = {
            Text(
                text = "Alumnado", color = MaterialTheme.colorScheme.onBackground
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
fun AdaptableList(widthSizeClass: WindowWidthSizeClass, navController: NavHostController) {
    val modo = remember {
        mutableStateOf(0)
    }
    when (widthSizeClass) {

        WindowWidthSizeClass.Compact -> {
            modo.value = 1
            ListContent(navController = navController, modo = modo.value)
        }

        WindowWidthSizeClass.Medium -> {
            modo.value = 1
            ListContent(navController = navController, modo = modo.value)
        }

        WindowWidthSizeClass.Expanded -> {
            modo.value = 2
            HorizontalLayout(navController, modo.value)
        }

        else -> {
            ListContent(navController = navController, modo = modo.value)
        }
    }
}

@Composable
fun HorizontalLayout(navController: NavHostController, modo: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            ListContent(navController = navController, modo = modo)
        }
        Spacer(modifier = Modifier.padding(end = 5.dp))
        Box(
            Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            DetailContent(navController = navController)
        }
    }
}


@Composable
fun ListContent(navController: NavHostController, modo: Int) {
    val options = listOf<String>("Todo", "Nombre", "Mujer", "Hombre")
    val selectedOption = remember { mutableStateOf(options.first()) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 60.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FilterOptions(options = options, selectedOption = selectedOption)
            //Spacer(modifier = Modifier.size(8.dp))
            Lista(navController, modo)
        }

    }
}

class Alumno(
    val nombre: String,
    val correo: String,
    val universidad: String
) {

}

@Composable
fun Lista(navController: NavHostController, modo: Int) {
    val alumnos = listOf<Alumno>(
        Alumno(
            "Joscelyn Pauleth Covarrubias Murrieta",
            "joscelyn.covarrubias@uabc.edu.mx",
            "Universidad Autonoma de Baja California"
        ),
        Alumno(
            "Benjamin Bolanos Garrido",
            "bbolanos@uabc.edu.mx",
            "Universidad Autonoma de Baja California"
        ),
        Alumno(
            "Perengano Fulanito Perez",
            "fulanito@uabc.edu.mx",
            "Xochicalco"
        ),
    )
    val boxBackground = Color(0x4196979A)

    Box(modifier = Modifier.fillMaxWidth()) {
        LazyColumn() {
            items(alumnos) { alumno ->
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(boxBackground)
                        .clickable {
                            if (modo == 1) navController.navigate(Screen.DetailScreen.route)
                            else if (modo == 2) {/*TODO() Mostrar solo la informacion del alumno que se clickea*/}
                        },
                    contentAlignment = Alignment.CenterStart
                ) {
                    Column(Modifier.padding(10.dp)) {
                        Text(
                            text = alumno.nombre,
                            fontSize = 25.sp,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = alumno.correo,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Light
                        )
                        Text(
                            text = alumno.universidad,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Light
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(5.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterOptions(
    options: List<String>,
    selectedOption: MutableState<String>
) {
    val selectedTint = MaterialTheme.colorScheme.secondary
    val unselectedTint = MaterialTheme.colorScheme.background

    val searchState = remember { mutableStateOf(TextFieldValue()) }

    Column(Modifier.padding(10.dp)) {
        TextField(
            value = searchState.value,
            onValueChange = { searchState.value = it },
            placeholder = { Text("Buscar") },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_search_24),
                    contentDescription = "search"
                )
            },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colorScheme.onBackground,
                focusedLabelColor = MaterialTheme.colorScheme.onTertiary,
                containerColor = MaterialTheme.colorScheme.surface
            )
        )

        Spacer(modifier = Modifier.size(8.dp))

        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .height(35.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            options.forEachIndexed { index, toggleState ->
                val isSelected = selectedOption.value.lowercase() == toggleState.lowercase()
                val backgroundTint = if (isSelected) selectedTint else unselectedTint
                val textColor =
                    if (isSelected) MaterialTheme.colorScheme.onSecondary else Color.Unspecified

                FilterChip(
                    selected = isSelected,
                    onClick = { selectedOption.value = toggleState },
                    label = { Text(text = toggleState, color = textColor) },
                    colors = FilterChipDefaults.filterChipColors(
                        containerColor = backgroundTint,
                        selectedContainerColor = backgroundTint,
                        selectedLabelColor = MaterialTheme.colorScheme.onBackground
                    )
                )
                Spacer(modifier = Modifier.padding(end = 5.dp))
            }
        }
    }
}