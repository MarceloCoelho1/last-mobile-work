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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
                        composable("PlayerFightMachine/{playerChoice}/{playerName}") { backStackEntry ->
                            val playerChoice = backStackEntry.arguments?.getString("playerChoice")
                            val playerName = backStackEntry.arguments?.getString("playerName")
                            PlayerFightMachine(navController, playerChoice, playerName)
                        }
                        composable("EnterNameSinglePlayer") { EnterNameSinglePlayer(navController) }
                        composable("EnterNamesTwoPlayers") { EnterNamesTwoPlayers(navController) }
                        composable(
                            "playerStatus/{playerName}/{rockCount}/{paperCount}/{scissorCount}",
                            arguments = listOf(
                                navArgument("playerName") { type = NavType.StringType },
                                navArgument("rockCount") { type = NavType.IntType },
                                navArgument("paperCount") { type = NavType.IntType },
                                navArgument("scissorCount") { type = NavType.IntType }
                            )
                        ) { backStackEntry ->
                            val playerName = backStackEntry.arguments?.getString("playerName")
                            val rockCount = backStackEntry.arguments?.getInt("rockCount") ?: 0
                            val paperCount = backStackEntry.arguments?.getInt("paperCount") ?: 0
                            val scissorCount = backStackEntry.arguments?.getInt("scissorCount") ?: 0
                            PlayerStatusScreen(
                                navController = navController,
                                playerName = playerName,
                                rockCount = rockCount,
                                paperCount = paperCount,
                                scissorCount = scissorCount
                            )
                        }
                    }
                }
            }
        }
    }
}

