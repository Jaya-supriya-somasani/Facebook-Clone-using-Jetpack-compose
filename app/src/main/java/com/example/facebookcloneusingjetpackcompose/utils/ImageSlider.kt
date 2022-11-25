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
import com.example.facebookcloneusingjetpackcompose.model.Friends
import com.example.facebookcloneusingjetpackcompose.model.FriendsDetails
import com.google.accompanist.pager.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue

@ExperimentalPagerApi
@Composable
fun ViewPagerSlider() {
    val details = FriendsDetails().getFriends()
    val pagerState = rememberPagerState(initialPage = 10)
    PagerAutoSliderUI(
        modifier = Modifier.fillMaxSize(),
        pageCount = details.size,
        pagerState = pagerState
    ) {
        val userDetails = details[pagerState.currentPage]
        PageContentUI(
            modifier = Modifier
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
}

@Composable
private fun PageContentUI(modifier: Modifier, details: Friends) {
    Card(
        modifier = modifier
            .fillMaxSize()
            .padding(25.dp, 0.dp, 25.dp, 0.dp),
        shape = RoundedCornerShape(20.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)
        ) {
            Image(
                painter = painterResource(
                    id = details.image
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
                    text = details.name,
                    style = MaterialTheme.typography.h5,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "This is ${details.name}",
                    style = MaterialTheme.typography.body1,
                    color = Color.White,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
                )
            }
        }
    }
}

@ExperimentalPagerApi
@Composable
private fun PagerAutoSliderUI(
    modifier: Modifier = Modifier,
    pageCount: Int,
    pagerState: PagerState,
    itemUi: @Composable PagerScope.(Int) -> Unit
) {

    //Automatically sliding the images
    LaunchedEffect(Unit) {
        while (true) {
            yield()
            delay(2000)
            tween<Float>(600)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pageCount)
            )
        }
    }

    Column(modifier = modifier) {
        Spacer(modifier = Modifier.height(30.dp))
        HorizontalPager(
            count = pageCount,
            state = pagerState,
            modifier = Modifier
                .weight(1F).padding(bottom = 10.dp),
            content = itemUi
        )

        //Horizontal dot indicator
        HorizontalPagerIndicator(
            pagerState = pagerState, modifier = Modifier
                .align(Alignment.CenterHorizontally), spacing = 10.dp
        )
    }
}

