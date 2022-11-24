package com.example.facebookcloneusingjetpackcompose

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
fun FriendsRequestsPage() {
    val scrollState = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().verticalScroll(scrollState)
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
        repeat(10) {
            FriendReqLists()
        }
    }
}

@Composable
fun FriendReqSection() {
    var showDialog by remember {
        mutableStateOf(false)
    }
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
            onClick = {
                showDialog = true
            },
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
    AlertDialogDisplaying(showDialog) { isDismissed ->
        showDialog = isDismissed
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
                onClick = { /* */ },
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


@Composable
fun AlertDialogDisplaying(isOpened: Boolean, onStateChanged: (Boolean) -> Unit ) {
    val context = LocalContext.current

    if (isOpened) {
        AlertDialog(
            onDismissRequest = { onStateChanged(false) },
            title = { Text(text = "Are You Sure?") },
            text = { Text(text = "This is dialog content") },
            confirmButton = {
                TextButton(onClick = {
                    onStateChanged(false)
                    ShowToast(context, "Confirm button clicked")

                }) { Text(text = "Confirm") }
            },
            dismissButton = {
                TextButton(onClick = {
                    ShowToast(context, "Dismiss button clicked")
                    onStateChanged(false)

                }) {
                    Text(
                        text = "" +
                                "Dismiss"
                    )
                }
            }
        )
    }
}

fun ShowToast(context: Context, textMsg: String) {
    Toast.makeText(context, textMsg, Toast.LENGTH_SHORT).show()
}