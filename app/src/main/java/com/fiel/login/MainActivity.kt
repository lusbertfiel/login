package com.fiel.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fiel.login.ui.presentation.home.HomeScreen
import com.fiel.login.ui.presentation.login.LoginScreen
import com.fiel.login.ui.presentation.register.RegisterScreen
import com.fiel.login.ui.theme.LoginTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screens.Login.route) {
                        composable(route = Screens.Login.route){
                            LoginScreen(navController)
                        }
                        composable(route = Screens.Register.route){
                            RegisterScreen(navController)
                        }
                        composable(route = Screens.Home.route){
                            HomeScreen(navController)
                        }
                    }
                }
            }
        }
    }
}

sealed class Screens(val route:String){
    data object Home:Screens("home")
    data object Login:Screens("login")
    data object Register:Screens("register")
}