package com.example.facebookcloneusingjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.facebookcloneusingjetpackcompose.ui.theme.FacebookCloneUsingJetpackComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FacebookCloneUsingJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Navigation()
//                    var pageState by remember { mutableStateOf("Login") }
//                    when (pageState) {
//                        "Login" -> {
//                            LoginPage(navigateToHome = {
//                                pageState = "Home"
//                            }, navigateToSignUp = {
//                                pageState = "SignUp"
//                            }, navigateToForgotPasswordPage = {
//                                pageState = "Forgot"
//                            }
//                            )
//                        }
//                        "Home" -> {
//                            Home()
//                        }
//                        "SignUp" -> {
//                            SignupPage()
//                        }
//                        "Forgot" -> {
//                            ForgotPasswordPage()
//                        }
//                    }
                }
            }
        }
    }
}

