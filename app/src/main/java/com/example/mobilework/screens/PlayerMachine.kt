package com.example.mobilework.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mobilework.R
import com.example.mobilework.components.GameButton

@Composable
fun PlayerMachine(navController: NavHostController, playerName: String?) {
    var roundGame by remember { mutableStateOf(1) }
    var card by remember { mutableStateOf("") }

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
                text = "Round $roundGame",
                fontSize = 24.sp
            )
        }
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
                    card = "rock"
                    navController.navigate("PlayerFightMachine/$card")
                },
                modifier = Modifier.weight(1f)
            )
            GameButton(
                image = painterResource(id = R.drawable.paper),
                text = "Paper",
                onClick = {
                    card = "paper"
                    navController.navigate("PlayerFightMachine/$card")
                },
                modifier = Modifier.weight(1f)
            )
            GameButton(
                image = painterResource(id = R.drawable.scissor),
                text = "Scissor",
                onClick = {
                    card = "scissor"
                    navController.navigate("PlayerFightMachine/$card")
                },
                modifier = Modifier.weight(1f)
            )
        }
    }
}
