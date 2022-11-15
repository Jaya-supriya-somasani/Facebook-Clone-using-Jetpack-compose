package com.example.facebookcloneusingjetpackcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Magenta
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@Composable
fun Home() {
    val personDetails = PersonDetails()
    val getData = personDetails.getPersonsDetails()
    val scrollState = rememberScrollState()

    val myViewModel =MyViewModel()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = scrollState)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.facebook_label),
                style = TextStyle(fontSize = 32.sp)
            )
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
        Column {
            TabLayout()
            CreatingUserFields(viewModel=myViewModel)
            LazyColumn(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(top = 200.dp)
                    .height(500.dp)
            ) {
                items(items = getData) { person ->
                    CustomItems(person = person)
                }
            }

        }

    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabLayout() {
    val pagerState = rememberPagerState(pageCount = 3)
    Column() {
        Tabs(pagerState = pagerState)
        TabsContent(pagerState = pagerState)

    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(pagerState: PagerState) {
    val list = listOf(
        "Home" to Icons.Default.Home,
        "Shopping" to Icons.Default.Shop,
        "Settings" to Icons.Default.Settings
    )
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = Color.Transparent,
        contentColor = Blue,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 2.dp,
                color = Blue
            )
        }
    )
    {
        list.forEachIndexed { index, _ ->
            Tab(
                icon = { Icon(imageVector = list[index].second, contentDescription = null) },
                text = { Text(list[index].first) },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}


//creating individual pages for each tab
@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContent(pagerState: PagerState) {
    HorizontalPager(state = pagerState) { page ->
        when (page) {
            0 -> HomePage()
            1 -> TabsContentScreen()
            2 -> TabsContentScreen()
        }
    }
}

@Composable
fun TabsContentScreen() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            buildAnnotatedString {

                withStyle(style = SpanStyle(color = Blue)) { append("J") }
                append("aya ")
                withStyle(style = SpanStyle(color = Red)) { append("S") }
                append("upriya".repeat(20))
            }
        )
    }

}

@Composable
fun HomePage() {
    val gradientColors = listOf(Blue, Green, Red, Yellow, Magenta)
    Column {
        Text(text = "Hello".repeat(100), maxLines = 2, overflow = TextOverflow.Ellipsis)
        Text(text = "text")
    }
}

@Composable
fun CustomItems(person: Person) {
    val reuseModifier = Modifier.padding(vertical = 20.dp)
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(text = "${person.id}", reuseModifier.weight(1f))
        Text(text = person.name, reuseModifier.weight(1f))
        Text(text = person.lastname, reuseModifier.weight(1f))
        Text(text = "${person.age}", reuseModifier.weight(1f))
    }
}

@Composable
fun CreatingUserFields(viewModel: MyViewModel) {

//    val userName = rememberSaveable { mutableStateOf("") }
//    val password = rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        value = viewModel.userName,
        onValueChange = { viewModel.userNameChanged(it) },
        label = { Text(text = "User name") }
    )

    OutlinedTextField(value = viewModel.password,
        onValueChange = { viewModel.passwordChanged( it) },
        label = { Text(text = "Password") }
    )

    Button(
        onClick = { /*TODO*/ },
        enabled = viewModel.userName.isNotEmpty() && viewModel.password.isNotEmpty(), modifier = Modifier.wrapContentSize()
    ) {
            Text(text = "Submit")
    }

}

class MyViewModel:ViewModel(){

    var userName by  mutableStateOf("")
    var password by mutableStateOf("")

    fun userNameChanged(name:String){
        userName=name
    }
    fun passwordChanged(userPassword: String){
        password=userPassword
    }

}

