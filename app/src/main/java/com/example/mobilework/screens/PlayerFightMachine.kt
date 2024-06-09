package com.example.mobilework.screens
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun fight(navController: NavHostController, cardPlayer: String?) {
    var winner by remember { mutableStateOf(0) } // 1 player wins, 2 machine wins

    when (ChooseHand()) {
        "rock" -> if (cardPlayer == "scissor") {
            winner = 2
        } else if (cardPlayer == "paper") {
            winner = 1
        }
        "paper" -> if (cardPlayer == "rock") {
            winner = 2
        } else if (cardPlayer == "scissor") {
            winner = 1
        }
        "scissor" -> if (cardPlayer == "paper") {
            winner = 2
        } else if (cardPlayer == "rock") {
            winner = 1
        }
    }

    if (winner == 1) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box {

                Text(
                    text = "You win!",
                    fontSize = 50.sp
                )
            }
            Button(
                onClick = {
                    navController.navigate("PlayerMachine") // need to comeback adding in round + 1
                }
            ) {
                Text(
                    text = "Next Round"
                )
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box {

                Text(
                    text = "You lose!",
                    fontSize = 50.sp
                )
            }
            Button(
                onClick = { navController.navigate("home") }
            ) {
                Text(
                    text = "Home"
                )
            }
        }
    }
}

@Composable
fun ChooseHand(): String {
    val machineHandOption = listOf("rock", "paper", "scissor")
    return machineHandOption.random()
}
