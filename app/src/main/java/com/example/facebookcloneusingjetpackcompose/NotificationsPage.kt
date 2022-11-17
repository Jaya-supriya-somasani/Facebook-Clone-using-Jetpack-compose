package com.example.facebookcloneusingjetpackcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.facebookcloneusingjetpackcompose.ui.theme.Blue500

@Composable
fun NotificationsPage() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        NotificationSearchSection()
    }
}

@Composable
fun NotificationSearchSection() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp, vertical = 10.dp)
    ) {
        Text(
            text = "Notifications",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 10.dp)
        )
        Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
    }
    Column(horizontalAlignment = Alignment.Start) {
        Text(
            text = "Earlier",
            fontSize = 20.sp,
            modifier = Modifier.padding(start = 10.dp, top = 10.dp, bottom = 20.dp),
            fontWeight = FontWeight.Bold
        )
        UnCheckedNotification()
        UnCheckedNotification()
        CheckedNotification()
        CheckedNotification()
        UnCheckedNotification()
        CheckedNotification()
        UnCheckedNotification()
        CheckedNotification()
        CheckedNotification()
    }
}

@Composable
fun UnCheckedNotification(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Blue500)
            .padding(top = 10.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "Notification Icon",
            modifier = Modifier
                .padding(start = 15.dp)
                .size(80.dp)
                .weight(0.2f)
        )
        Column(modifier = Modifier
            .weight(0.8f)
            .padding(start = 10.dp)) {
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Jaya Supriya ")
                    }
                    append("added a new photo.")
                }
            )
            Text(text = "Wed at 20:52", color = Color.Gray, fontSize = 14.sp, modifier = Modifier.padding(top = 2.dp))
        }
        Icon(
            imageVector = Icons.Default.MoreHoriz,
            contentDescription = "more",
            modifier = Modifier
                .weight(0.1f)

        )
    }
}

@Composable
fun CheckedNotification(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "Notification Icon",
            modifier = Modifier
                .padding(start = 15.dp)
                .size(80.dp)
                .weight(0.2f)
        )
        Column(modifier = Modifier
            .weight(0.8f)
            .padding(start = 10.dp)) {
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Jaya Supriya ")
                    }
                    append("added a new photo.")
                }
            )
            Text(text = "Wed at 20:52", color = Color.Gray, fontSize = 14.sp, modifier = Modifier.padding(top = 2.dp))
        }
        Icon(
            imageVector = Icons.Default.MoreHoriz,
            contentDescription = "more",
            modifier = Modifier
                .weight(0.1f)
        )
    }
}