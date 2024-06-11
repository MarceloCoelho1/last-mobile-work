package com.example.mobilework.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mobilework.R
import com.example.mobilework.components.GameButton
import kotlin.random.Random

@Composable
fun PlayerMachine(
    navController: NavHostController,
    playerName: String?
) {
    var playerChoice by remember { mutableStateOf("") }
    var machineChoice by remember { mutableStateOf("") }
    var winner by remember { mutableStateOf<String?>(null) }
    var isStatusDialogVisible by remember { mutableStateOf(false) }

    // Variáveis de estado para manter a contagem de escolhas
    var rockCount by remember { mutableStateOf(0) }
    var paperCount by remember { mutableStateOf(0) }
    var scissorCount by remember { mutableStateOf(0) }

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
                PressedButtons("Player Status") {
                    isStatusDialogVisible = true
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
                GameOptions { choice ->
                    playerChoice = choice
                    machineChoice = getMachineChoice()
                    winner = determineWinner(playerChoice, machineChoice, playerName)

                    // Atualiza a contagem de escolhas
                    when (choice) {
                        "rock" -> rockCount++
                        "paper" -> paperCount++
                        "scissor" -> scissorCount++
                    }
                }
            }
        }
    }

    if (isStatusDialogVisible) {
        AlertDialog(
            onDismissRequest = {
                isStatusDialogVisible = false
            },
            title = { Text(text = "Player Status") },
            text = {
                Column {
                    Text(text = "Player Name: ${playerName ?: "Player"}", fontSize = 18.sp)
                    Text(text = "Rock Count: $rockCount", fontSize = 18.sp)
                    Text(text = "Paper Count: $paperCount", fontSize = 18.sp)
                    Text(text = "Scissor Count: $scissorCount", fontSize = 18.sp)
                }
            },
            confirmButton = {
                Button(onClick = { isStatusDialogVisible = false }) {
                    Text("Close")
                }
            }
        )
    }
}

@Composable
fun PressedButtons(text: String, onClick: () -> Unit) {
    Button(onClick = onClick) {
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

