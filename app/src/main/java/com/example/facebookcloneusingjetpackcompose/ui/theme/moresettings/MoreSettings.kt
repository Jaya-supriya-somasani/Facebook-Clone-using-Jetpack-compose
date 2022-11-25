package com.example.facebookcloneusingjetpackcompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.facebookcloneusingjetpackcompose.ui.theme.LightGray200

@Composable
fun MoreSettings() {
    Column(verticalArrangement = Arrangement.Top, modifier = Modifier.fillMaxSize()) {
        TopMenuHeading()
        Spacer(modifier = Modifier.height(10.dp))
        UserProfileSection()
        Spacer(modifier = Modifier.height(10.dp))
        Divider(
            thickness = 1.dp,
            color = Color.LightGray,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(5.dp))
        GridSectionOptions()
    }
}

@Composable
fun TopMenuHeading() {
    Row(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Menu",
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif
        )
        Row(modifier = Modifier.padding(top = 10.dp)) {
            IconButton(
                onClick = { /*TODO*/ }, modifier = Modifier
                    .clip(CircleShape)
                    .background(
                        LightGray200
                    )
                    .size(40.dp)
            ) {
                Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings")
            }
            IconButton(
                onClick = { /*TODO*/ }, modifier = Modifier
                    .clip(CircleShape)
                    .background(
                        LightGray200
                    )
                    .size(40.dp)
            ) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            }
        }
    }
}

@Composable
private fun UserProfileSection() {
    Row(modifier = Modifier
        .padding(horizontal = 8.dp)
        .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = R.drawable.frd1),
            contentDescription = "User profile",
            modifier = Modifier
                .clip(CircleShape)
                .size(30.dp),
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.padding(start = 8.dp)) {
            Text(
                text = "Priya Somasani",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h6
            )
            Text(text = "See your profile", fontSize = 12.sp, color = Gray)
        }
    }
}

@Composable
private fun GridSectionOptions() {
    LazyVerticalGrid(columns = GridCells.Fixed(2), contentPadding = PaddingValues(4.dp), content ={
        items(10) {
            Card(
                modifier = Modifier.height(80.dp).padding(4.dp),
                shape = RoundedCornerShape(10.dp),
                elevation = 2.dp
            ) {
                Column(modifier = Modifier.padding(start = 10.dp).fillMaxSize(), verticalArrangement = Arrangement.SpaceAround) {
                    Image(imageVector = Icons.Default.Search, contentDescription = "Search")
                    Text(text = "Friends")
                }
            }
        }
    },)
}