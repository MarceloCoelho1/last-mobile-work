package com.example.mobilework.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mobilework.R
import com.example.mobilework.components.GameButton
import kotlin.random.Random

@Composable
fun PlayerMachine(navController: NavHostController, playerName: String?) {
    var playerChoice by remember { mutableStateOf("") }
    var machineChoice by remember { mutableStateOf("") }
    var winner by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Choose your card:",
                fontSize = 18.sp
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            GameButton(
                image = painterResource(id = R.drawable.rock),
                text = "Rock",
                onClick = {
                    playerChoice = "rock"
                    machineChoice = getMachineChoice()
                    winner = determineWinner(playerChoice, machineChoice, playerName)
                },
                modifier = Modifier.weight(1f)
            )
            GameButton(
                image = painterResource(id = R.drawable.paper),
                text = "Paper",
                onClick = {
                    playerChoice = "paper"
                    machineChoice = getMachineChoice()
                    winner = determineWinner(playerChoice, machineChoice, playerName)
                },
                modifier = Modifier.weight(1f)
            )
            GameButton(
                image = painterResource(id = R.drawable.scissor),
                text = "Scissor",
                onClick = {
                    playerChoice = "scissor"
                    machineChoice = getMachineChoice()
                    winner = determineWinner(playerChoice, machineChoice, playerName)
                },
                modifier = Modifier.weight(1f)
            )
        }

        if (playerChoice.isNotEmpty() && machineChoice.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Machine chose: $machineChoice",
                    fontSize = 18.sp
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = winner ?: "",
                    fontSize = 18.sp
                )
            }
        }
    }
}

fun getMachineChoice(): String {
    return when (Random.nextInt(1, 4)) {
        1 -> "paper"
        2 -> "scissor"
        else -> "rock"
    }
}

fun determineWinner(playerChoice: String, machineChoice: String, playerName: String?): String {
    return when {
        playerChoice == machineChoice -> "It's a tie!"
        (playerChoice == "rock" && machineChoice == "scissor") ||
                (playerChoice == "scissor" && machineChoice == "paper") ||
                (playerChoice == "paper" && machineChoice == "rock") -> "$playerName wins!"
        else -> "Machine wins!"
    }
}
