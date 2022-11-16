package com.example.facebookcloneusingjetpackcompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.facebookcloneusingjetpackcompose.ui.theme.Blue
import com.example.facebookcloneusingjetpackcompose.ui.theme.Green

@Composable
fun LoginPage(
    navigateToHome: () -> Unit,
    navigateToSignUp: () -> Unit,
    navigateToForgotPasswordPage: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            Image(painter = painterResource(id = R.drawable.login), contentDescription = "Login")
        }
        Divider(color = Color.Gray, thickness = 0.2.dp)
        Row(
            modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "English (United Kingdom)",
                modifier = Modifier.padding(end = 10.dp),
                fontSize = 12.sp,
                color = Color.Gray
            )
            CircleDot()
            Text(
                text = "Espanol",
                modifier = Modifier.padding(end = 10.dp),
                fontSize = 12.sp,
                color = Color.Gray
            )
            CircleDot()
            Text(text = "More...", color = Color.Blue, fontSize = 12.sp)

        }
        Spacer(modifier = Modifier.height(30.dp))
        PhoneNumTextField("Phone or Email")
        Spacer(modifier = Modifier.height(10.dp))
        PasswordTextField(label = "Password")
        Spacer(modifier = Modifier.height(20.dp))
        LoginButton(navigateToHome = navigateToHome)
        Spacer(modifier = Modifier.height(10.dp))
        ForgotPasswordButton(navigateToForgotPasswordPage = navigateToForgotPasswordPage)
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth(0.8f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Divider(color = Color.Gray, thickness = 0.2.dp, modifier = Modifier.weight(1F))
            Text(
                text = "OR", modifier = Modifier
                    .wrapContentWidth()
                    .padding(horizontal = 10.dp)
            )
            Divider(color = Color.Gray, thickness = 0.2.dp, modifier = Modifier.weight(1F))
        }
        CreateAccount(navigateToSignUp = navigateToSignUp)
    }
}

@Composable
fun CircleDot() {
    Dot(shape = CircleShape)
}

@Composable
fun Dot(shape: Shape) {
    Column(
        modifier = Modifier
            .wrapContentSize(Alignment.Center)
    ) {
        Box(
            modifier = Modifier
                .padding(end = 5.dp)
                .size(3.dp)
                .clip(shape)
                .background(Color.Gray)
        )

    }

}

@Composable
fun PhoneNumTextField(label: String) {
    var userInput by rememberSaveable { mutableStateOf("") }
    TextField(
        value = userInput,
        onValueChange = { userInput = it },
        label = { Text(label) },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(0.8f),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            cursorColor = Color.Gray,
            focusedLabelColor = Color.Gray, focusedIndicatorColor = Blue
        )
    )
}

@Composable
fun PasswordTextField(label: String) {
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    TextField(
        value = password,
        onValueChange = { password = it },
        label = { Text(text = label) },
        singleLine = true,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
            val description = if (passwordVisible) "Hide Password" else "Show password"
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = image, description)
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            cursorColor = Color.Gray,
            focusedLabelColor = Color.Gray, focusedIndicatorColor = Blue
        ),
        modifier = Modifier.fillMaxWidth(0.8f)
    )
}

@Composable
fun LoginButton(navigateToHome: () -> Unit) {
    Button(
        onClick = navigateToHome,
        modifier = Modifier.fillMaxWidth(0.8f),
        colors = ButtonDefaults.buttonColors(backgroundColor = Blue)
    ) {
        Text(text = "Login", color = Color.White, fontWeight = FontWeight.W800)
    }
}

@Composable
fun ForgotPasswordButton(navigateToForgotPasswordPage: () -> Unit) {
    Text(
        text = "Forgot Password",
        color = Blue,
        modifier = Modifier.clickable {
            navigateToForgotPasswordPage.invoke()
        },
        style = TextStyle(fontWeight = FontWeight.W800, fontSize = 16.sp)
    )
}

@Composable
fun CreateAccount(navigateToSignUp: () -> Unit) {
    Spacer(modifier = Modifier.height(40.dp))
    Button(
        onClick = navigateToSignUp,
        modifier = Modifier.fillMaxWidth(0.8f),
        colors = ButtonDefaults.buttonColors(backgroundColor = Green)
    ) {
        Text(text = "Create a new Facebook account", color = Color.White)

    }
}
