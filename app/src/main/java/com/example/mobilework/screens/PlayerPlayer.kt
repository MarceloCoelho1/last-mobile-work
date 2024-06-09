package com.example.mobilework.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
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

@Composable
fun PlayerPlayer(
    navController: NavHostController,
    player1Name: String?,
    player2Name: String?
) {
    var player1Choice by remember { mutableStateOf<String?>(null) }
    var player2Choice by remember { mutableStateOf<String?>(null) }
    var winner by remember { mutableStateOf<String?>(null) } // Corrigido

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Área do jogador 1
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
                text = player1Name ?: "Jogador 1",
                fontSize = 50.sp,
                color = Color.White
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                GameButton(
                    image = painterResource(id = R.drawable.rock),
                    text = "Rock",
                    onClick = { player1Choice = "rock"; checkResult(player1Choice, player2Choice, player1Name, player2Name, setWinner = { winner = it }) }
                )
                GameButton(
                    image = painterResource(id = R.drawable.paper),
                    text = "Paper",
                    onClick = { player1Choice = "paper"; checkResult(player1Choice, player2Choice, player1Name, player2Name, setWinner = { winner = it }) }
                )
                GameButton(
                    image = painterResource(id = R.drawable.scissor),
                    text = "Scissor",
                    onClick = { player1Choice = "scissor"; checkResult(player1Choice, player2Choice, player1Name, player2Name, setWinner = { winner = it }) }
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
                Button(onClick = { navController.navigate("home") }) {
                    Text("Home")
                }
                Button(onClick = {}) {
                    Text("Pause")
                }
                // Botão Restart
                Button(onClick = {
                    player1Choice = null
                    player2Choice = null
                    winner = null // Corrigido
                }) {
                    Text("Restart")
                }
            }
        }

        // Área do jogador 2
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
                text = player2Name ?: "Jogador 2",
                fontSize = 50.sp,
                color = Color.White
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                GameButton(
                    image = painterResource(id = R.drawable.rock),
                    text = "Rock",
                    onClick = { player2Choice = "rock"; checkResult(player1Choice, player2Choice, player1Name, player2Name, setWinner = { winner = it }) }
                )
                GameButton(
                    image = painterResource(id = R.drawable.paper),
                    text = "Paper",
                    onClick = { player2Choice = "paper"; checkResult(player1Choice, player2Choice, player1Name, player2Name, setWinner = { winner = it }) }
                )
                GameButton(
                    image = painterResource(id = R.drawable.scissor),
                    text = "Scissor",
                    onClick = { player2Choice = "scissor"; checkResult(player1Choice, player2Choice, player1Name, player2Name, setWinner = { winner = it }) }
                )
            }
        }

        // Resultado
        winner?.let {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = it,
                    fontSize = 50.sp,
                    color = Color.Black
                )
            }
        }
    }
}

fun checkResult(
    player1Choice: String?,
    player2Choice: String?,
    player1Name: String?,
    player2Name: String?,
    setWinner: (String) -> Unit
) {
    if (player1Choice != null && player2Choice != null) {
        val result = when {
            player1Choice == player2Choice -> "Empate!"
            player1Choice == "rock" && player2Choice == "scissor" -> "${player1Name ?: "Jogador 1"} venceu!"
            player1Choice == "scissor" && player2Choice == "paper" -> "${player1Name ?: "Jogador 1"} venceu!"
            player1Choice == "paper" && player2Choice == "rock" -> "${player1Name ?: "Jogador 1"} venceu!"
            else -> "${player2Name ?: "Jogador 2"} venceu!"
        }
        setWinner(result)
    }
}
