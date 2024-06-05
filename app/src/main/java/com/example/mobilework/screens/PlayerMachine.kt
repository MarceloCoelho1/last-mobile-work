package com.example.mobilework.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun PlayerMachine(navController: NavHostController) {
    Column (
        modifier = Modifier
            .fillMaxSize()
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(5f)
                .background(Color.Red)
        ){
            Text(
                text = "Maquina",
                fontSize = 50.sp,
                color = Color.White
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Gray)
                .height(20.dp)
        ){
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
                    Text("Pouse")
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(5f)
                .background(Color.Blue)
        ){
            Text(
                text = "Jogador",
                fontSize = 50.sp,
                color = Color.White
            )
        }
    }
}