package com.example.facebookcloneusingjetpackcompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource

@Composable
fun LoginPage() {
    Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
        Box() {
            Image(painter = painterResource(id = R.drawable.login), contentDescription ="Login" )
        }
        
    }
}