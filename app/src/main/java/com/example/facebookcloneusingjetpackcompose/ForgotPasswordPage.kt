package com.example.facebookcloneusingjetpackcompose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
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

@Composable
fun Body() {
    Column() {
        Text(text = "This is scaffold layout")
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