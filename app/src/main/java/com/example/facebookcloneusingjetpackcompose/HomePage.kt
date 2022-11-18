package com.example.facebookcloneusingjetpackcompose

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.facebookcloneusingjetpackcompose.ui.theme.FacebookLabel
import com.example.facebookcloneusingjetpackcompose.ui.theme.LightGray
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@Composable
fun Home() {
    val personDetails = PersonDetails()
    val getData = personDetails.getPersonsDetails()
    Column(
        modifier = Modifier
            .fillMaxSize()
//            .verticalScroll(state = scrollState)
    ) {
        TopSection()
        Column {
            TabLayout()
        }
    }
}

@Composable
fun TopSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.facebook_label),
            style = TextStyle(fontSize = 32.sp),
            color = FacebookLabel,
            fontFamily = Font(
                R.font.klavika_bold_italic,
                weight = FontWeight.Bold,
            ).toFontFamily()
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = { /*TODO*/ }, modifier = Modifier
                .clip(CircleShape)
                .background(Color.LightGray)
                .size(40.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "create post",
                modifier = Modifier.size(20.dp)
            )
        }
        Spacer(modifier = Modifier.width(5.dp))
        IconButton(
            onClick = { /*TODO*/ }, modifier = Modifier
                .clip(CircleShape)
                .background(Color.LightGray)
                .size(40.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "search",
                modifier = Modifier.size(20.dp)
            )
        }
        Spacer(modifier = Modifier.width(5.dp))
        IconButton(
            onClick = { /*TODO*/ }, modifier = Modifier
                .clip(CircleShape)
                .background(Color.LightGray)
                .size(40.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Chat,
                contentDescription = "message",
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabLayout() {
    val pagerState = rememberPagerState(pageCount = 6)
    Column(modifier = Modifier.fillMaxSize()) {
        Tabs(pagerState = pagerState)
//        Column(modifier = Modifier.fillMaxSize().verticalScroll(scrollState)) {
        TabsContent(pagerState = pagerState)
//        }


    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(pagerState: PagerState) {
    val unSelectedTabList = listOf(
        "Home" to Icons.Outlined.Home,
        "Friend" to Icons.Outlined.Group,
        "Video" to Icons.Outlined.VideoSettings,
        "Profile" to Icons.Outlined.AccountCircle,
        "Notifications" to Icons.Outlined.Notifications,
        "More" to Icons.Outlined.Menu
    )
    val selectedTabList = listOf(
        "Home" to Icons.Default.Home,
        "Friend" to Icons.Default.Group,
        "Video" to Icons.Default.VideoSettings,
        "Profile" to Icons.Default.AccountCircle,
        "Notifications" to Icons.Default.Notifications,
        "More" to Icons.Default.Menu
    )
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = Color.Transparent,
        contentColor = Color.Blue,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 2.dp,
                color = FacebookLabel
            )
        }
    )
    {
        unSelectedTabList.forEachIndexed { index, _ ->
            Tab(
                icon = {
                    Icon(
                        imageVector = selectedTabList[index].second,
                        contentDescription = null,
                    )
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                selectedContentColor = FacebookLabel,
                unselectedContentColor = Color.Gray
            )
        }
    }
}


//creating individual pages for each tab
@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContent(pagerState: PagerState) {
    HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize()) { page ->
        when (page) {
            0 -> HomePage()
            1 -> FriendsRequestsPage()
            2 -> VideosPage()
            3 -> ProfilePage()
            4 -> NotificationsPage()
            5 -> MoreSettings()
        }
    }
}

@Composable
fun HomePage() {
    val scrollState = rememberScrollState()
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        CreatePostSection()
        Divider(color = LightGray, thickness = 10.dp)
        Stories()
        Divider(color = LightGray, thickness = 10.dp)
        UserPosts()
    }
}

@Composable
fun CreatePostSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val userInput = remember { mutableStateOf("") }
        Image(
            painter = painterResource(id = R.drawable.login),
            contentDescription = "user profile",
            modifier = Modifier
                .size(40.dp)
                .fillMaxWidth(0.1f)
                .clip(CircleShape)
                .border(
                    BorderStroke(2.dp, LightGray),
                    CircleShape
                )
        )
        OutlinedTextField(
            value = userInput.value,
            onValueChange = { userInput.value = it },
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(0.8f),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.Gray
            ), shape = CircleShape,
            placeholder = {
                Text(text = "Write Something here ...")
            }
        )
        Image(
            painter = painterResource(id = R.drawable.login),
            contentDescription = "user profile",
            modifier = Modifier
                .size(40.dp)
                .fillMaxWidth(0.1f)
                .clip(CircleShape)
        )
    }
}

@Composable
fun Stories() {
    Row() {
        StoriesSection()
        StoriesSection()
        StoriesSection()
        StoriesSection()
    }
}

@Composable
fun StoriesSection() {
    Box(
        modifier = Modifier
            .height(160.dp)
            .width(100.dp)
            .border(
                BorderStroke(2.dp, LightGray)
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.login),
            contentDescription = "myStories",
            modifier = Modifier
                .matchParentSize()
                .align(Alignment.TopCenter)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)

        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .background(Color.LightGray)
                    .height(50.dp)
            )
            {
                Text(
                    text = "Create Story",
                    modifier = Modifier.align(Alignment.BottomCenter),
                )
            }
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = "create post",
                modifier = Modifier
                    .size(20.dp)
                    .align(Alignment.TopCenter)
                    .clip(CircleShape)
                    .background(Color.White)
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(FacebookLabel),
                tint = Color.White
            )
        }
    }
}

@Composable
fun UserPosts() {
    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row() {
                Image(
                    painter = painterResource(id = R.drawable.login),
                    contentDescription = "user profile",
                    modifier = Modifier
                        .size(40.dp)
                        .fillMaxWidth(0.1f)
                        .clip(CircleShape)
                        .border(
                            BorderStroke(2.dp, LightGray),
                            CircleShape
                        )
                        .padding(horizontal = 5.dp)
                )
                Column(modifier = Modifier.padding(start = 10.dp)) {
                    Text(text = "Jaya Supriya")
                    Text(text = "Nov 8")
                }
            }

            Row() {
                Icon(
                    imageVector = Icons.Filled.MoreHoriz,
                    contentDescription = "Settings",
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .size(20.dp)
                )
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Settings",
                    modifier = Modifier.size(20.dp)
                )
            }
        }
        Row(modifier = Modifier.padding(start = 10.dp)) {
            Text(text = "Post Description")
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .border(BorderStroke(1.dp, Color.LightGray))
                .align(Alignment.CenterHorizontally)
        ) {
            Image(
                painter = painterResource(id = R.drawable.login),
                contentDescription = "user post"
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(top = 10.dp)
        ) {
            Box() {
                Icon(
                    imageVector = Icons.Filled.ThumbUp,
                    contentDescription = "Settings",
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .size(20.dp), tint = FacebookLabel
                )
                Text(text = "Jaya and 21 others")
            }
            Box() {
                Text(text = "6 comments")
            }
        }
    }
}
