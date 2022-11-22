package com.example.facebookcloneusingjetpackcompose

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.facebookcloneusingjetpackcompose.datamodels.FriendsDetails
import com.example.facebookcloneusingjetpackcompose.ui.theme.Blue500
import com.example.facebookcloneusingjetpackcompose.ui.theme.FacebookLabel
import com.example.facebookcloneusingjetpackcompose.ui.theme.LightGray
import com.example.facebookcloneusingjetpackcompose.ui.theme.LightGray200

@Composable
fun ProfilePage() {
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.fillMaxSize().verticalScroll(scrollState)) {
        Image(
            painter = painterResource(id = R.drawable.login),
            contentDescription = "User cover photo",
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .border(BorderStroke(2.dp, LightGray))
        )
        Text(text = "Priya Somasani", fontSize = 24.sp, modifier = Modifier.padding(start = 10.dp))
        Row(modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp)) {
            OutlinedButton(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(FacebookLabel),
                modifier = Modifier
                    .padding(end = 5.dp)
                    .weight(0.4f),
                border = BorderStroke(0.dp, Color.Transparent)
            )
            {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                Text(text = "Add to Story", color = Color.White)
            }
            OutlinedButton(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(LightGray),
                modifier = Modifier
                    .padding(start = 5.dp, end = 5.dp)
                    .weight(0.4f),
                border = BorderStroke(0.dp, Color.Transparent)
            )
            {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
                Text(text = "Edit")
            }
            OutlinedButton(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(LightGray),
                modifier = Modifier
                    .padding(start = 5.dp)
                    .weight(0.2f),
                border = BorderStroke(0.dp, Color.Transparent)

            ) {
                Icon(imageVector = Icons.Default.MoreHoriz, contentDescription = "settings")
            }
        }
        Divider(
            color = Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
        Column(
            modifier = Modifier
                .padding(top = 20.dp, start = 10.dp, end = 10.dp)
                .background(
                    LightGray200
                )
        ) {
            Row(modifier = Modifier.padding(10.dp)) {
                Image(
                    imageVector = Icons.Default.Star,
                    contentDescription = "insights",
                    modifier = Modifier.size(50.dp)
                )
                Column(modifier = Modifier.padding(start = 10.dp)) {
                    Text(
                        text = "Get Content insights and grow your audience",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Text(
                        text = "Try professional mode to see how your content is performing and reach more people. If you're eligible, you can start earning money on your content.",
                        modifier = Modifier.padding(top = 5.dp)
                    )
                }
            }
            Row(modifier = Modifier.padding(bottom = 10.dp, start = 15.dp, end = 15.dp)) {
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(LightGray),
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .weight(0.2f),
                    border = BorderStroke(0.dp, Color.Transparent)

                ) {
                    Text(text = "Not Now")
                }
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(LightGray),
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .weight(0.2f),
                    border = BorderStroke(0.dp, Color.Transparent)

                ) {
                    Text(text = "Try it")
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Divider(color = Color.LightGray, thickness = 10.dp)
        Row {
            ChipsSection()
        }
        Divider(color = Color.LightGray, thickness = 1.dp)
        DetailsSection()
        OutlinedButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Blue500),
            border = BorderStroke(
                0.dp,
                Transparent
            )
        )
        {
            Text(text = "Edit public details", color = FacebookLabel)
        }
//        Divider(thickness = 1.dp, modifier = Modifier.padding(horizontal = 20.dp))
        FriendsSection()
        FriendsListGrid()
        OutlinedButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(LightGray),
            border = BorderStroke(
                0.dp,
                Transparent
            )
        )
        {
            Text(text = "See all friends")
        }
        Divider(color = Color.LightGray, thickness = 10.dp)


    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChipsSection() {
    Chip(
        onClick = { /* Do something! */
        },
        content = {
            Text(text = "Sample")
        }, modifier = Modifier.padding(start = 10.dp)
    )
    Chip(
        onClick = { /* Do something! */ },
        content = {
            Text(text = "Sample2")
        }, modifier = Modifier.padding(start = 10.dp)
    )
}

@Composable
fun DetailsSection() {
    Column() {
        Text(
            text = "Details",
            modifier = Modifier.padding(start = 15.dp, top = 10.dp),
            fontWeight = FontWeight.Bold, fontSize = 24.sp
        )
        Row(modifier = Modifier.padding(start = 15.dp, top = 15.dp)) {
            Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
            Text(text = "Current City", modifier = Modifier.padding(start = 10.dp), color = Gray)
        }
        Row(modifier = Modifier.padding(start = 15.dp, top = 15.dp)) {
            Icon(imageVector = Icons.Default.BusinessCenter, contentDescription = "Box")
            Text(text = "Workplace", modifier = Modifier.padding(start = 10.dp), color = Gray)
        }
        Row(modifier = Modifier.padding(start = 15.dp, top = 15.dp)) {
            Icon(imageVector = Icons.Default.School, contentDescription = "School")
            Text(text = "School", modifier = Modifier.padding(start = 10.dp), color = Gray)
        }
        Row(modifier = Modifier.padding(start = 15.dp, top = 15.dp)) {
            Icon(imageVector = Icons.Default.LocationOn, contentDescription = "Home")
            Text(text = "Hometown", modifier = Modifier.padding(start = 10.dp), color = Gray)
        }
        Row(modifier = Modifier.padding(start = 15.dp, top = 15.dp)) {
            Icon(imageVector = Icons.Default.FamilyRestroom, contentDescription = "Home")
            Text(
                text = "Relationship Status",
                modifier = Modifier.padding(start = 10.dp),
                color = Gray
            )
        }
        Row(modifier = Modifier.padding(start = 15.dp, top = 15.dp)) {
            Icon(imageVector = Icons.Default.MoreHoriz, contentDescription = "Home")
            Text(text = "See your About info", modifier = Modifier.padding(start = 10.dp))
        }
    }
}

@Composable
fun FriendsSection() {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Column(modifier = Modifier.padding(start = 20.dp)) {
            Text(text = "Friends", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(text = "116 friends")
        }
        TextButton(
            onClick = { /*TODO*/ }, modifier = Modifier
                .align(Alignment.Top)
                .padding(end = 20.dp)
        ) {
            Text(text = "Find Friends", color = FacebookLabel)
        }

    }
}

@Composable
fun FriendsListGrid() {
    val friendsDetails = FriendsDetails()
    val getFriendsList = friendsDetails.getFriends()
    LazyVerticalGrid(
        columns = GridCells.Fixed(3), content = {
            items(getFriendsList.size) { item ->
                Column() {
                    val name = getFriendsList[item].name
                    Image(
                        painter = painterResource(getFriendsList[item].image),
                        contentDescription = "images",
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )
                    Text(
                        text = "$name",
                        modifier = Modifier.padding(start = 5.dp, bottom = 10.dp),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }, modifier = Modifier
            .padding(horizontal = 15.dp)
            .height(500.dp)
    )
}