package com.example.mobilework.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun PlayerStatusScreen(navController: NavHostController, playerName: String?, rockCount: Int, paperCount: Int, scissorCount: Int) {
    // Exibindo os status do jogador
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Player Status", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        // Exibindo o nome do jogador
        Text(text = "Player Name: ${playerName ?: ""}", fontSize = 18.sp)
        // Exibindo as contagens de escolhas
        Text(text = "Rock Count: $rockCount", fontSize = 18.sp)
        Text(text = "Paper Count: $paperCount", fontSize = 18.sp)
        Text(text = "Scissor Count: $scissorCount", fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))
        // Botão para voltar para o jogo
        PressedButtons("Back to Game") {
            navController.popBackStack()
        }
    }
}
