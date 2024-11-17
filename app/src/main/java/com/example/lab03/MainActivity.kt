package com.example.lab03

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab03.ui.theme.Lab03Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab03Theme {
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
                    }
                ) { innerPadding ->
                    GreetingCard(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun GreetingCard(modifier: Modifier = Modifier) {
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
                        text = "Este curso te ayudara a aprender los fundamentos de Jetpack Compose y a construir interfaces modernas.",
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGreetingCard() {
    Lab03Theme {
        GreetingCard()
    }
}
