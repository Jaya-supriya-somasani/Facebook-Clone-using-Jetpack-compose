package com.example.facebookcloneusingjetpackcompose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.facebookcloneusingjetpackcompose.ui.theme.FacebookLabel
import com.example.facebookcloneusingjetpackcompose.ui.theme.LightGray

@Composable
fun FriendsRequests() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        FriendReqSection()
        Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, start = 10.dp, end = 10.dp, bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                buildAnnotatedString {
                    append("Friend requests ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Red)) {
                        append("39")
                    }
                }
            )
            Text(text = "Sell All")
        }
        FriendReqLists()
        FriendReqLists()
        FriendReqLists()
        FriendReqLists()
        FriendReqLists()
        FriendReqLists()
        FriendReqLists()
        FriendReqLists()
        FriendReqLists()
    }
}
@Composable
fun FriendReqSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Friends", fontSize = 26.sp, fontWeight = FontWeight.Bold)
        Icon(
            imageVector = Icons.Default.Search, contentDescription = "Search",
            Modifier
                .clip(
                    CircleShape
                )
                .background(Color.LightGray)
                .padding(5.dp)
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, bottom = 10.dp, end = 10.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        OutlinedButton(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(LightGray),
            border = BorderStroke(0.dp, Color.Transparent),
            shape = CircleShape
        ) {
            Text(text = "Suggestions")
        }
        Spacer(modifier = Modifier.width(10.dp))
        OutlinedButton(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(LightGray),
            border = BorderStroke(0.dp, Color.Transparent),
            shape = CircleShape
        ) {
            Text(text = "Your Friends")
        }
    }
}

@Composable
fun FriendReqLists() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(top = 10.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.login),
            contentDescription = "user profiles",
            modifier = Modifier
                .weight(1f)
                .size(80.dp)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            Text(text = "Venu", modifier = Modifier.padding(start = 5.dp))
            Text(text = "Mutual", modifier = Modifier.padding(start = 5.dp))
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(FacebookLabel),
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Confirm", color = Color.White)
            }
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f), verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "2y", modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 5.dp)
            )
            OutlinedButton(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(LightGray),
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth(),
                border = BorderStroke(0.dp, Color.Transparent)
            )
            {
                Text(text = "Delete", modifier = Modifier.clickable {
                })
            }
        }
    }
}
