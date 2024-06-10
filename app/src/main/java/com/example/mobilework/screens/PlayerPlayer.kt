package com.example.mobilework.screens

import android.service.controls.templates.ControlButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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
    var winner by remember { mutableStateOf<String?>(null) }

    // Variáveis para armazenar a vida dos jogadores
    var player1Life by remember { mutableStateOf(3) }
    var player2Life by remember { mutableStateOf(3) }
    var isWinnerDisplayed by remember { mutableStateOf(false) }

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
                color = Color.White,
            )
            Row (
                modifier = Modifier.padding(end = 16.dp, top = 16.dp),
                horizontalArrangement = Arrangement.End
            ){
                repeat(player1Life) {
                    Image(
                        painter = painterResource(id = R.drawable.heart),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            if (winner == null) {
                GameOptions { choice ->
                    player1Choice = choice
                    checkResult(player1Choice, player2Choice, player1Name, player2Name) { result ->
                        winner = result
                        if (result.contains("venceu")) player1Life -= 1
                        if (player1Life == 0 || player2Life == 0) {
                            isWinnerDisplayed = true
                        }
                    }
                }
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
                    player1Life = 3
                    player2Life = 3
                    winner = null
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
            Row (
                modifier = Modifier.padding(end = 16.dp, top = 16.dp),
                horizontalArrangement = Arrangement.End
            ){
                repeat(player2Life) {
                    Image(
                        painter = painterResource(id = R.drawable.heart),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            if (winner == null) {
                GameOptions { choice ->
                    player2Choice = choice
                    checkResult(player1Choice, player2Choice, player1Name, player2Name) { result ->
                        winner = result
                        if (result.contains("venceu")) player2Life -= 1
                        if (player1Life == 0 || player2Life == 0) {
                            isWinnerDisplayed = true
                        }
                    }
                }
            }
            if (player1Life == 0 ||player2Life == 0 ) {
                AlertDialog(
                    onDismissRequest = {
                        isWinnerDisplayed = false
                        navController.navigate("home")
                    },
                    title = { Text(text = "Fim de Jogo") },
                    text = {
                        Text(
                            text = "O vencedor é ${if (player1Life > player2Life) player1Name ?: "Jogador 1" else player2Name ?: "Jogador 2"}!",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                isWinnerDisplayed = false
                                navController.navigate("home")
                            }
                        ) {
                            Text("Ok")
                        }
                    }
                )
            }
        }

        // Resultado
        winner?.let {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.7f))
                    .clickable {
                        player1Choice = null
                        player2Choice = null
                        winner = null
                    },contentAlignment = Alignment.Center
            ) {
                Text(
                    text = it,
                    fontWeight = FontWeight.Bold, // ou FontWeight.Normal, FontWeight.Light, etc.
                    fontSize = 50.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun GameOptions(onClick: (String) -> Unit) {
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
fun PressableButton(text: String, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text)
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
