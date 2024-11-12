package com.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.presentation.ui.main.MainScreen


@Composable
fun CookarooNavHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(navController = navController, startDestination = Navigation.Main.destination) {
        composable(Navigation.Main.destination) {
            MainScreen()
        }
    }
}