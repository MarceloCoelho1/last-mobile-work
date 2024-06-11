package com.example.mobilework.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun PlayerStatusScreen(
    navController: NavHostController,
    playerName: String?,
    rockCount: Int,
    paperCount: Int,
    scissorCount: Int
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Player Status", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Player Name: ${playerName ?: ""}", fontSize = 18.sp)
        Text(text = "Rock Count: $rockCount", fontSize = 18.sp)
        Text(text = "Paper Count: $paperCount", fontSize = 18.sp)
        Text(text = "Scissor Count: $scissorCount", fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))
        PressedButtons("Back to Game") {
            navController.popBackStack()
        }
    }
}
