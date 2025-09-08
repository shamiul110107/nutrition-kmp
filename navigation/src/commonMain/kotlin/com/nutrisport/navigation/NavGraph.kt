package com.nutrisport.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nutrition.auth.component.AuthScreen
import com.nutrition.home.HomeGraphScreen
import com.nutrition.shared.navigation.Screen

@Composable
fun SetupNavGraph(
    startDestination: Screen = Screen.Auth
) {
    val navController = rememberNavController()
    NavHost(
        navController =  navController,
        startDestination = startDestination
    ) {
        composable<Screen.Auth> {
            AuthScreen()
        }
        composable<Screen.HomeGraph> {
            HomeGraphScreen(
                navigateToAuth = {
                    navController.navigate(Screen.Auth) {
                        popUpTo<Screen.HomeGraph> { inclusive = true }
                    }
                }
            )
        }
    }
}