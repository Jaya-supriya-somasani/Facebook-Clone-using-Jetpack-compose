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
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.facebookcloneusingjetpackcompose.ui.theme.Blue
import com.example.facebookcloneusingjetpackcompose.ui.theme.Green
import com.example.facebookcloneusingjetpackcompose.ui.theme.login.LoginViewModel

@Composable
fun LoginPage(
    navigateToHome: () -> Unit,
    navigateToSignUp: () -> Unit,
    navigateToForgotPasswordPage: () -> Unit,
) {
    val loginViewModel: LoginViewModel = viewModel()

    val userNameState = loginViewModel.userNameFlow.collectAsState()
    val userNameErrorState = loginViewModel.userNameFlowError.collectAsState()

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
//        PhoneNumTextField("Phone or Email", username = {
//            viewModel.name.value = it
//        })
        PhoneNumTextField(
            label = "Phone or Email",
            userName = userNameState.value,
            usernameCallback = loginViewModel::updateUserName
        )
        if(userNameErrorState.value) {
            Text(text = "Invalid username")
        }
        Spacer(modifier = Modifier.height(10.dp))
        PasswordTextField(label = "Password")
        Spacer(modifier = Modifier.height(20.dp))
        LoginButton {
            loginViewModel.validateUserFields()
        }
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
fun PhoneNumTextField(label: String, userName: String = "", usernameCallback: (String) -> Unit) {
    TextField(
        value = userName,
        onValueChange = { name ->
            usernameCallback(name)
        },
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
fun LoginButton(validateUserFields: () -> Unit) {
    Button(
        onClick = validateUserFields,
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
