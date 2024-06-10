package com.example.mobilework.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
        modifier = Modifier.fillMaxSize()
    ) {
        // Área da máquina
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(5f)
                .background(Color.Red)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Machine",
                fontSize = 50.sp,
                color = Color.White
            )
            if (playerChoice.isNotEmpty() && machineChoice.isNotEmpty()) {
                Text(
                    text = "Machine chose: $machineChoice",
                    fontSize = 18.sp,
                    color = Color.White
                )
                Text(
                    text = winner ?: "",
                    fontSize = 18.sp,
                    color = Color.White
                )
            }
        }

        // Área de controle no meio
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Gray)
                .height(20.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                PressedButtons("Home") { navController.navigate("home") }
                PressedButtons("Restart") {
                    playerChoice = ""
                    machineChoice = ""
                    winner = null
                }
            }
        }

        // Área do jogador
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(5f)
                .background(Color.Blue)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = playerName ?: "Jogador",
                fontSize = 50.sp,
                color = Color.White
            )
            if (winner == null) {
                Text(
                    text = "Choose your card:",
                    fontSize = 18.sp,
                    color = Color.White
                )
                GameOption { choice ->
                    playerChoice = choice
                    machineChoice = getMachineChoice()
                    winner = determineWinner(playerChoice, machineChoice, playerName)
                }
            }
        }
    }
}

@Composable
fun GameOption(onClick: (String) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        GameButton(
            image = painterResource(id = R.drawable.rock),
            text = "Rock",
            onClick = { onClick("rock") }
        )
        GameButton(
            image = painterResource(id = R.drawable.paper),
            text = "Paper",
            onClick = { onClick("paper") }
        )
        GameButton(
            image = painterResource(id = R.drawable.scissor),
            text = "Scissor",
            onClick = { onClick("scissor") }
        )
    }
}

@Composable
fun PressedButtons(text: String, onClick: () -> Unit) {
    androidx.compose.material3.Button(onClick = onClick) {
        Text(text)
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
        playerChoice == machineChoice -> "Empate!"
        (playerChoice == "rock" && machineChoice == "scissor") ||
                (playerChoice == "scissor" && machineChoice == "paper") ||
                (playerChoice == "paper" && machineChoice == "rock") -> "$playerName ganhou!"
        else -> "Maquina venceu!"
    }
}