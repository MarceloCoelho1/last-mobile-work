package com.example.mobilework.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Home page",
            fontSize = 36.sp,
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = { navController.navigate("PlayerMachine") }) {
            Text("Jogar contra máquina")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("PlayerPlayer") }) {
            Text("Jogador contra Jogador")
        }
    }
}