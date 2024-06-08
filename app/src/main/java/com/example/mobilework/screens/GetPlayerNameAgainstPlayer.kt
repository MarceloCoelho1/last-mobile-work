package com.example.mobilework.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun EnterNamesTwoPlayers(navController: NavHostController) {
    val player1Name = remember { mutableStateOf("") }
    val player2Name = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Enter Player 1 name", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = player1Name.value,
            onValueChange = { player1Name.value = it },
            label = { Text("Player 1 Name") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Enter Player 2 name", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = player2Name.value,
            onValueChange = { player2Name.value = it },
            label = { Text("Player 2 Name") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navController.navigate("PlayerPlayer/${player1Name.value}/${player2Name.value}")
        }) {
            Text("Submit")
        }
    }
}
