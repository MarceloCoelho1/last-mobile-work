package com.example.mobilework

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mobilework.screens.*
import com.example.mobilework.ui.theme.MobileWorkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileWorkTheme {
                val navController = rememberNavController()
                val backStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = backStackEntry?.destination?.route

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("home") { HomeScreen(navController) }
                        composable("example") { ExamplePage() }
                        composable("PlayerPlayer/{player1}/{player2}") { backStackEntry ->
                            val player1 = backStackEntry.arguments?.getString("player1")
                            val player2 = backStackEntry.arguments?.getString("player2")
                            PlayerPlayer(navController, player1, player2)
                        }
                        composable("PlayerMachine/{playerName}") { backStackEntry ->
                            val playerName = backStackEntry.arguments?.getString("playerName")
                            PlayerMachine(navController, playerName)
                        }
                        composable("PlayerFightMachine/{playerChoice}") { backStackEntry ->
                            val playerChoice = backStackEntry.arguments?.getString("playerChoice")
                            PlayerFightMachine(navController, playerChoice)
                        }
                        composable("EnterNameSinglePlayer") { EnterNameSinglePlayer(navController) }
                        composable("EnterNamesTwoPlayers") { EnterNamesTwoPlayers(navController) }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MobileWorkTheme {
        HomeScreen(navController = rememberNavController())
    }
}
