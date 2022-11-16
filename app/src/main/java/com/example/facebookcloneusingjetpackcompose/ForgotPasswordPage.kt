package com.example.facebookcloneusingjetpackcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.facebookcloneusingjetpackcompose.ui.theme.Blue
import kotlinx.coroutines.launch

@Composable
fun ForgotPasswordPage() {
    Column {
        ScaffoldLayout()
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
                    .background(Color(0XFF0F9D58))) {
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