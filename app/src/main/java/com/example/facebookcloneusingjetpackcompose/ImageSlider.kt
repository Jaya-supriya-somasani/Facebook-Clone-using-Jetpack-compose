package com.example.facebookcloneusingjetpackcompose

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.example.facebookcloneusingjetpackcompose.datamodels.FriendsDetails
import com.google.accompanist.pager.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue

@ExperimentalPagerApi
@Composable
fun ViewPagerSlider() {
    val details = FriendsDetails().getFriends()
    val pagerState = rememberPagerState(initialPage = 10)

    LaunchedEffect(Unit) {
        while (true) {
            yield()
            delay(2000)
            tween<Float>(600)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount)
            )
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(30.dp))
        HorizontalPager(
            count = details.size,
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .padding(0.dp, 40.dp, 0.dp, 40.dp)
        ) { page ->
            Card(modifier = Modifier
                .graphicsLayer {
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

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

                }
                .fillMaxWidth()
                .padding(25.dp, 0.dp, 25.dp, 0.dp),
                shape = RoundedCornerShape(20.dp)
            ) {

                val userDetails = details[page]
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Image(
                        painter = painterResource(
                            id = userDetails.image
                        ),
                        contentDescription = "Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(15.dp)
                    ) {

                        Text(
                            text = userDetails.name,
                            style = MaterialTheme.typography.h5,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "This is ${userDetails.name}",
                            style = MaterialTheme.typography.body1,
                            color = Color.White,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
                        )
                    }
                }
            }
        }

        //Horizontal dot indicator
        HorizontalPagerIndicator(
            pagerState = pagerState, modifier = Modifier
                .align(Alignment.CenterHorizontally), spacing = 10.dp
        )
    }
}

