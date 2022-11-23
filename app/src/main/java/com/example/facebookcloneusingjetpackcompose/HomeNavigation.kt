package com.example.facebookcloneusingjetpackcompose

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.facebookcloneusingjetpackcompose.navigations.Screen

@Composable
fun HomeNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route= Screen.HomeScreen.route){
            Home()
        }
        composable(route= Screen.ProfileScreen.route){
            ProfilePage()
        }
        composable(route= Screen.VideosScreen.route){
            VideosPage()
        }
        composable(route= Screen.FriendReqScreen.route){
            FriendsRequestsPage()
        }
        composable(route= Screen.NotificationsScreen.route){
            NotificationsPage()
        }
        composable(route= Screen.MoreSettingsScreen.route){
            MoreSettings()
        }
        composable(route= Screen.SingUpScreen.route){
            SignupPage()
        }
        composable(route= Screen.ForgotPasswordScreen.route){
            ForgotPasswordPage()
        }
    }
}