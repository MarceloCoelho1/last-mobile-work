package com.example.mobilework

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobilework.ui.theme.MobileWorkTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import com.example.mobilework.screens.HomeScreen
import com.example.mobilework.screens.ExamplePage
import com.example.mobilework.screens.PlayerPlayer
import com.example.mobilework.screens.PlayerMachine

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
                    bottomBar = {
                        if (!((currentRoute == "PlayerPlayer")||(currentRoute == "PlayerMachine"))) {
                            NavigationBar {
                                NavigationBarItem(
                                    selected = currentRoute == "home",
                                    onClick = { navController.navigate("home") },
                                    icon = {
                                        Icon(
                                            painterResource(R.drawable.ic_home),
                                            contentDescription = "Home"
                                        )
                                    }
                                )
                                NavigationBarItem(
                                    selected = currentRoute == "example",
                                    onClick = { navController.navigate("example") },
                                    icon = {
                                        Icon(
                                            painterResource(R.drawable.ic_example),
                                            contentDescription = "Example"
                                        )
                                    }
                                )
                            }
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("home") { HomeScreen(navController) }
                        composable("example") { ExamplePage() }
                        composable("PlayerPlayer") { PlayerPlayer(navController) }
                        composable("PlayerMachine") { PlayerMachine(navController) }
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