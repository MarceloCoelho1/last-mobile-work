package com.example.mobilework.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mobilework.R

@Composable
fun PlayerMachine(navController: NavHostController, playerName: String?) {
    var roundGame by remember { mutableStateOf(1) }
    var card by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box() {
            Text(
                text = "Round $roundGame"
            )
        }
        Box() {
            Text(
                text = "Choose your card:"
            )
        }
        Box() {
            Row() {
                    Button(
                        onClick = {
                            card = "rock"
                            navController.navigate("fight/${card}")
                        } // Run the choice of the "machine"
                    ) {
                        Image(painterResource(id = R.drawable.rock), contentDescription = "Rock")
                        Text(
                            text = "Rock",
                            fontSize = 50.sp,
                            modifier = Modifier
                                .weight(5f)
                        )
                    }
                    Button(
                        onClick = {
                            card = "paper"
                            navController.navigate("fight")
                        } // Run the choice of the "machine"
                    ) {
                        Image(painterResource(id = R.drawable.paper), contentDescription = "Paper")
                        Text(
                            text = "Paper",
                            fontSize = 50.sp,
                            modifier = Modifier
                                .weight(5f)
                        )
                    }
                    Button(
                        onClick = {
                            card = "scissor"
                            navController.navigate("fight")
                        } // Run the choice of the "machine"
                    ) {
                        Image(painterResource(id = R.drawable.scissor), contentDescription = "Scissor")
                        Text(
                            text = "Scissor",
                            fontSize = 50.sp,
                            modifier = Modifier
                                .weight(5f)
                        )
                    }

            }
        }
    }
}
