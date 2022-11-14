package com.example.facebookcloneusingjetpackcompose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SignupPage() {
    Column {
        Text(
            text = "Sign Up",
            modifier = Modifier
                .padding(top = 50.dp)
                .align(Alignment.CenterHorizontally),
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = "It's quick and easy", style = TextStyle(fontSize = 12.sp), color = Color.Gray, modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(50.dp))
        UserInput("Phone or email")
        Spacer(modifier = Modifier.height(20.dp))
        UserInput("Enter Password")
        Spacer(modifier = Modifier.height(20.dp))
        UserInput("Re-type Password")
        Spacer(modifier = Modifier.height(20.dp))
        UserInput("Date of Birth")
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Gender", modifier = Modifier.padding(start=30.dp,bottom=10.dp))
        LabelledRadioButton()
        TermsCheckBox()
        Spacer(modifier = Modifier.height(10.dp))
        ButtonClick(label = "Sign Up")
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "OR", modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(10.dp))
        ButtonClick(label = "Login")
    }
}

@Composable
fun UserInput(labelName: String) {
        Column( ){
            var name by remember { mutableStateOf("") }
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(text = labelName) }, modifier = Modifier
                    .padding(start = 30.dp, end = 30.dp)
                    .fillMaxWidth())
        }
}

@Composable
fun LabelledRadioButton() {
    val radioOptions = listOf("Male", "Female")
    var selectedItem by remember { mutableStateOf(radioOptions[0]) }
        Row(modifier = Modifier
            .selectableGroup()
            .padding(start = 15.dp)) {
            radioOptions.forEach { label ->
                Row(
                    modifier = Modifier
                        .selectable(
                            selected = (selectedItem == label),
                            onClick = { selectedItem = label },
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        modifier = Modifier.padding(end = 16.dp),
                        selected = (selectedItem == label),
                        onClick = null // null recommended for accessibility with screen readers
                    )
                    Text(text = label)
                }
            }
        }
}
@Composable
fun TermsCheckBox(){
    Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(start = 15.dp, top = 20.dp)) {
        val isChecked = remember { mutableStateOf(false) }

        Checkbox(
            checked = isChecked.value,
            onCheckedChange = { isChecked.value = it },
            enabled = true
        )
        Text(text = "I agree to the terms and conditions")
    }
}
@Composable
fun ButtonClick(label:String){
   Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
       Button(onClick = { /*TODO*/ }, modifier = Modifier
           .fillMaxWidth()
           .padding(horizontal = 30.dp)) {
           Text(text = label)
       }
   }
}