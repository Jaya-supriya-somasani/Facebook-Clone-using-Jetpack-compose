package com.example.facebookcloneusingjetpackcompose.navigations

sealed class Screen(val route:String){

    object LoginScreen: Screen("Login")
    object ProfileScreen: Screen("Profile_screen")
    object HomeScreen: Screen("Home_screen")
    object FriendReqScreen: Screen("Friend_requests")
    object VideosScreen: Screen("Videos_screen")
    object NotificationsScreen: Screen("Notification_screen")
    object MoreSettingsScreen: Screen("More_settings_Screen")
    object SingUpScreen: Screen("SignUp_screen")
    object ForgotPasswordScreen: Screen("Forgot_password_screen")

}
