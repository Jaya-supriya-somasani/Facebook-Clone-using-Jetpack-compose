package com.example.facebookcloneusingjetpackcompose

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.example.facebookcloneusingjetpackcompose.model.Friends
import com.example.facebookcloneusingjetpackcompose.model.FriendsDetails
import com.example.facebookcloneusingjetpackcompose.ui.theme.Black500
import com.example.facebookcloneusingjetpackcompose.ui.theme.Blue
import com.example.facebookcloneusingjetpackcompose.ui.theme.LightGray200
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@Composable
fun ForgotPasswordPage() {
//
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize(),
    ) {
//        ScaffoldLayout()
        Text(
            text = "Recently Uploaded",
            modifier = Modifier.padding(top = 30.dp, start = 16.dp),
            fontWeight = FontWeight.Bold, fontFamily = FontFamily.SansSerif,
            style = MaterialTheme.typography.h5
        )
        Spacer(modifier = Modifier.height(20.dp))
        ImageSliderUI()
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "All eChronicles", modifier = Modifier.padding(start = 16.dp, bottom = 8.dp),
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
        )
//        Spacer(modifier = Modifier.height(20.dp))
        LazyVerticalGrid(columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            content = {
                items(4) {
                    EchronicleSection()
                }
            })

    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImageSliderUI() {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .height(350.dp)
            .fillMaxWidth(), shape = RoundedCornerShape(10.dp), elevation = 2.dp
    ) {
        Column {
            ViewPagerImageSlider(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.8f)
            )
            Column(modifier = Modifier.weight(0.2f)) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Magazine April Month 2022",
                    modifier = Modifier.padding(start = 10.dp),
                    style = MaterialTheme.typography.h6
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "12-Mar-2022",
                    modifier = Modifier.padding(start = 10.dp),
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}


@ExperimentalPagerApi
@Composable
fun ViewPagerImageSlider(modifier: Modifier) {
    val details = FriendsDetails().getFriends()
    val pagerState = rememberPagerState(initialPage = 10)

    //Creating the slider
    PagerSliderUIs(
        modifier = modifier,
        pageCount = details.size,
        pagerState = pagerState,

        //this is call back
        itemUi = {

            val userDetails = details[pagerState.currentPage]

            //Creating the Slide UI
            PageContentUIs(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        val pageOffset =
                            calculateCurrentOffsetForPage(pagerState.currentPage).absoluteValue

                        lerp(
                            start = 0.85f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale

                        }
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )

                    },
                details = userDetails
            )
        }
    )
}

@Composable
private fun PageContentUIs(modifier: Modifier, details: Friends) {
    Card(
        modifier = modifier
            .fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(
                    id = details.image
                ),
                contentDescription = "Image",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@ExperimentalPagerApi
@Composable
private fun PagerSliderUIs(
    modifier: Modifier = Modifier,
    pageCount: Int,
    pagerState: PagerState,
    itemUi: @Composable PagerScope.(Int) -> Unit
) {
    val scope = rememberCoroutineScope()
    Box(modifier = modifier) {
        Log.d("pager","pager current state ${pagerState.currentPage} total :$pageCount")

        //Creating the horizontal pager which contains swipe gesture
        HorizontalPager(
            count = pageCount,
            state = pagerState,
            content = itemUi
        )

        //Arranging the left arrow and right arrow buttons
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(horizontal = 10.dp)
        ) {

            //moving to the previous image slide when user click on the left arrow
            IconButton(
                onClick = {
                    scope.launch {
                        pagerState.scrollToPage(if (pagerState.currentPage == 0) pageCount+1 else (pagerState.currentPage - 1) % pageCount)
                    }
                },
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Black500)
                    .size(40.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBackIos,
                    contentDescription = "Left Slide",
                    tint = White
                )
            }

            //moving to the next image slide when user click on the right arrow

            IconButton(
                onClick = {
                    scope.launch {
                        pagerState.scrollToPage((pagerState.currentPage + 1) % pageCount)
                    }
                },
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Black500)
                    .size(40.dp),
            ) {
                Icon(
                    imageVector = Icons.Rounded.ArrowForwardIos,
                    contentDescription = "Right Slide",
                    tint = White
                )
            }
        }

        //Horizontal dot indicator
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 10.dp),
            activeColor = Red,
            inactiveColor = LightGray200,
            spacing = 10.dp
        )
    }
}


@Composable
fun EchronicleSection() {
    Card(
        modifier = Modifier
            .height(250.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 2.dp
    ) {
        Column {
            Row(

                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.2f)
                    .padding(end = 20.dp, top = 10.dp),
            ) {
                Icon(imageVector = Icons.Default.ThumbUp, contentDescription = "Liked")
                Text(text = "22", modifier = Modifier.padding(start = 5.dp))
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .align(Alignment.CenterHorizontally)
                    .weight(1f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.student),
                    contentDescription = "student image",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(LightGray200)
                    .padding(start = 20.dp)
            ) {
                Text(
                    text = "Magazines April 2...",
                    modifier = Modifier.padding(top = 10.dp),
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "12-Mar-2022",
                    modifier = Modifier.padding(vertical = 5.dp),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun ScaffoldLayout() {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(
                onMenuClicked = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.open()
                    }
                })
        },
        bottomBar = { BottomBar() },
        content = { Body() },
        drawerContent = { Drawer() },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                coroutineScope.launch {
                    when (scaffoldState.snackbarHostState.showSnackbar(
                        message = "Snack bar is displaying",
                        actionLabel = "Dismiss"
                    )) {
                        SnackbarResult.Dismissed -> {}
                        SnackbarResult.ActionPerformed -> {}
                    }
                }
            }, backgroundColor = Blue, contentColor = White)
            {
                Text(text = "Fab")
            }
        }, isFloatingActionButtonDocked = true
    )
}

@Composable
fun TopBar(onMenuClicked: () -> Unit) {
    TopAppBar(
        title = { Text(text = "Scaffold ", color = Color.Red) },
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",
                modifier = Modifier.clickable(onClick = onMenuClicked)
            )
        },
        backgroundColor = White
    )
}


@Composable
fun BottomBar() {
    BottomAppBar(
        backgroundColor = Blue,
        cutoutShape = MaterialTheme.shapes.small.copy(
            CornerSize(percent = 50)
        )
    ) {
        Text(text = "Bottom App Bar", color = White)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Body() {
    val bottomSheetScaffoldState =
        rememberBottomSheetScaffoldState(bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed))
    val coroutineScope = rememberCoroutineScope()
    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color(0XFF0F9D58))
            ) {
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Hello Geek!", fontSize = 20.sp, color = Color.White)
                }
            }
        },
        sheetPeekHeight = 0.dp
    ) {}
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            coroutineScope.launch {
                if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                    bottomSheetScaffoldState.bottomSheetState.expand()
                } else {
                    bottomSheetScaffoldState.bottomSheetState.collapse()
                }
            }
        }, colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFF0F9D58))) {
            Text(text = "Click Me", color = White)
        }
    }
}

@Composable
fun Drawer() {
    Column {
        repeat(5) { item ->
            Text(
                text = "item $item",
                modifier = Modifier.padding(10.dp),
                color = Color.Blue
            )

        }

    }
}
