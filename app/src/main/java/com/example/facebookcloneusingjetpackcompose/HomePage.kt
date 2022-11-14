package com.example.facebookcloneusingjetpackcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Home() {
    Box(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "facebook", style = TextStyle(fontSize = 32.sp))
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = { /*TODO*/ }, modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.Gray)
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "create post")
            }
            Spacer(modifier = Modifier.width(5.dp))
            IconButton(
                onClick = { /*TODO*/ }, modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.Gray)
            ) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "search")
            }
            Spacer(modifier = Modifier.width(5.dp))
            IconButton(
                onClick = { /*TODO*/ }, modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.Gray)
            ) {
                Icon(imageVector = Icons.Filled.Chat, contentDescription = "message")
            }
        }
//        TabLayout()
    }
}

//@Composable
//fun TabLayout() {
//    val pagerState by remember { mutableStateOf(3) }
//    Column() {
//        Tabs(pagerState = pagerState)
//
//    }
//}
//
//


