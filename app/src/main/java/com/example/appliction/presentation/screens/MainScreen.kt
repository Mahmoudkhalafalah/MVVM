package com.example.appliction.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appliction.R
import com.example.appliction.presentation.customcomponents.CustomOutlinedTextField
import com.example.appliction.presentation.ui.theme.PurpleGrey40

//@Preview(showBackground = true)
@Composable
fun LoginPage(
    userName: String,
    onUSerNameTextChange: (String) -> Unit,
    password: String,
    onPasswordTextChange: (String) -> Unit,
    onTrailingIconClick: () -> Unit,
    visibility: Boolean = true,
    onSignInButtonClick: (String)->Unit,
    onSignUpTextClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(0.3f),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Welcome", fontSize = 50.sp, fontFamily = FontFamily.Cursive)
            Image(
                imageVector = Icons.Filled.Person,
                contentDescription = "",
                modifier = Modifier.size(120.dp)
            )
            Text(text = "Log in", fontSize = 30.sp, fontFamily = FontFamily.Monospace)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxHeight(0.7f)
        ) {
            CustomOutlinedTextField(
                text = userName,
                onTextChange = { onUSerNameTextChange(it) },
                label = "UserName",
                placeholder = "UserName"
            )
            CustomOutlinedTextField(
                text = password,
                onTextChange = { onPasswordTextChange(it) },
                label = "Password",
                placeholder = "Password",
                trailingIcon = {
                    IconButton(onClick = { onTrailingIconClick() }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.password_eye),
                            contentDescription = ""
                        )
                    }
                },

                visible = visibility
            )
            Button(onClick = { onSignInButtonClick(userName) }) {
                Text(text = "Log in", fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                Text(text = "Don't have account?")
                Spacer(modifier = Modifier.width(5.dp))
                ClickableText(
                    text = AnnotatedString("Sign Up"),
                    onClick = { onSignUpTextClick() },
                    style = TextStyle(
                        color = PurpleGrey40
                    )
                )

            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "OR", fontSize = 14.sp)
            Text(text = "Sign in With", fontSize = 12.sp)
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth(0.6f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.facebook),
                    contentDescription = "",
                    modifier = Modifier.size(50.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = "",
                    modifier = Modifier.size(50.dp)
                )
            }
        }

    }
}




