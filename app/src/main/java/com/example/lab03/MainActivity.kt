package com.example.lab03

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab03.ui.theme.Lab03Theme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab03Theme {
                val snackbarHostState = remember { SnackbarHostState() }
                val scope = rememberCoroutineScope()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {  },
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Añadir elemento"
                            )
                        }
                    },
                    snackbarHost = { SnackbarHost(snackbarHostState) }
                ) { innerPadding ->
                    GreetingCard(
                        modifier = Modifier.padding(innerPadding),
                        snackbarHostState = snackbarHostState,
                        scope = scope
                    )
                }
            }
        }
    }
}

@Composable
fun GreetingCard(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope
) {
    var name by remember { mutableStateOf("") }
    var isSwitchOn by remember { mutableStateOf(false) }
    var sliderValue by remember { mutableStateOf(0.5f) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Text(
                text = "¡Bienvenido al curso!",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            Text(
                text = "Hola, $name!",
                fontSize = 20.sp
            )
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Ingresa tu nombre") },
                modifier = Modifier.fillMaxWidth()
            )
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            Image(
                painter = painterResource(id = R.drawable.snorlax),
                contentDescription = "Imagen del curso",
                modifier = Modifier
                    .size(150.dp)
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            )
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            Button(onClick = { }) {
                Text("Accede al curso")
            }
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Descuento activo",
                    modifier = Modifier.weight(1f),
                    fontSize = 16.sp
                )
                Switch(
                    checked = isSwitchOn,
                    onCheckedChange = { isSwitchOn = it }
                )
            }
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Nivel de progreso: ${(sliderValue * 100).toInt()}%",
                    fontSize = 16.sp
                )
                Slider(
                    value = sliderValue,
                    onValueChange = { sliderValue = it },
                    valueRange = 0f..1f,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = androidx.compose.ui.graphics.Color.LightGray
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Información del Curso",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Este curso te ayudará a aprender los fundamentos de Jetpack Compose y a construir interfaces modernas.",
                        fontSize = 14.sp
                    )
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            Button(onClick = {
                scope.launch {
                    val result = snackbarHostState.showSnackbar(
                        message = "Acción realizada correctamente",
                        actionLabel = "OK"
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                    }
                }
            }) {
                Text("Mostrar Snackbar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGreetingCard() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Lab03Theme {
        GreetingCard(snackbarHostState = snackbarHostState, scope = scope)
    }
}
