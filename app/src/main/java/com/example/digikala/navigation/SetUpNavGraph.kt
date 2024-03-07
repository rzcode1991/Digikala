package com.example.digikala.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavArgument
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.digikala.ui.screens.BasketScreen
import com.example.digikala.ui.screens.CategoryScreen
import com.example.digikala.ui.screens.home.HomeScreen
import com.example.digikala.ui.screens.ProfileScreen
import com.example.digikala.ui.screens.SplashScreen
import com.example.digikala.ui.screens.home.WebViewScreen

@Composable
fun SetUpNavGraph(
    navController: NavHostController
){
    NavHost(navController = navController, startDestination = Screen.Splash.route){
        composable(route = Screen.Splash.route){
            SplashScreen(navController = navController)
        }
        composable(route = Screen.Home.route){
            HomeScreen(navController = navController)
        }
        composable(route = Screen.Category.route){
            CategoryScreen(navController = navController)
        }
        composable(route = Screen.Basket.route){
            BasketScreen(navController = navController)
        }
        composable(route = Screen.Profile.route){
            ProfileScreen(navController = navController)
        }
        composable(
            route = Screen.WebView.route+"?url={url}",
            arguments = listOf(
                navArgument("url"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ){navBackStackEntry ->
            val url = navBackStackEntry.arguments?.getString("url")
            url?.let {
                WebViewScreen(navController, url)
            }
        }
    }
}