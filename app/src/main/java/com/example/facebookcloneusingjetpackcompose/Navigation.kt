package com.example.facebookcloneusingjetpackcompose

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.LoginScreen.route) {
        composable(route = Screen.LoginScreen.route) {
            LoginPage(
                navigateToHome = { navController.navigate(Screen.HomeScreen.route) },
                navigateToSignUp = { navController.navigate(Screen.SingUpScreen.route) },
                navigateToForgotPasswordPage = { navController.navigate(Screen.ForgotPasswordScreen.route) },
            )
        }
        composable(route=Screen.HomeScreen.route){
            Home()
        }
        composable(route=Screen.ProfileScreen.route){
            ProfilePage()
        }
        composable(route=Screen.VideosScreen.route){
            VideosPage()
        }
        composable(route=Screen.FriendReqScreen.route){
            FriendsRequestsPage()
        }
        composable(route=Screen.NotificationsScreen.route){
            NotificationsPage()
        }
        composable(route=Screen.MoreSettingsScreen.route){
            MoreSettings()
        }
        composable(route=Screen.SingUpScreen.route){
            SignupPage()
        }
        composable(route=Screen.ForgotPasswordScreen.route){
            ForgotPasswordPage()
        }
    }
}
