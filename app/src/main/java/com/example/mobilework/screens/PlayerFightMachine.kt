package com.example.mobilework.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun PlayerFightMachine(navController: NavHostController, playerChoice: String?) {
    var machineChoice by remember { mutableStateOf("") }
    var winner by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        machineChoice = getMachineChoice()
        winner = determineWinner(playerChoice ?: "", machineChoice, "Player")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Player chose: $playerChoice", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Machine chose: $machineChoice", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = winner ?: "", fontSize = 24.sp)
    }
}
