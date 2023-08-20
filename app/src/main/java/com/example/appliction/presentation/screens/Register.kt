package com.example.appliction.presentation.screens

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import com.example.appliction.R
import com.example.appliction.data.persestence.UserInfo
import com.example.appliction.presentation.customcomponents.CustomOutlinedTextField
import com.example.appliction.presentation.customcomponents.DropDownList
import com.example.appliction.presentation.customcomponents.showDatePicker

//@Preview(showBackground = true)
@Composable
fun SignUp(
    name: String,
    onNameValueChange: (String) -> Unit,
    password: String,
    onPasswordValueChange: (String) -> Unit,
    email: String,
    onEmailValueChange: (String) -> Unit,
    onPasswordTrailingIconClick: () -> Unit,
    passwordVisibility: Boolean,
    repeatedPasswordVisibility: Boolean,
    onSignUpButtonClick: () -> Unit,
    repeatedPassword: String,
    onRepeatedPasswordValueChange: (String) -> Unit,
    onRepeatPasswordTrailingIconClick: () -> Unit,
    year: Int,
    month: Int,
    day: Int,
    selectedDate: MutableState<String>,
    expanded: Boolean,
    selectedItem: String = "gender",
    onExpandedChange: (Boolean) -> Unit,
    onDropDownMenuDismiss: () -> Unit,
    onDropDownItemClick: (String) -> Unit,
    dropDownList: List<String> = listOf("Male", "Female", "Other"),
    context: Context,
    onSignInTextClick: (UserInfo) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Sign Up", fontSize = 20.sp)
        CustomOutlinedTextField(
            text = name,
            onTextChange = { onNameValueChange(it) },
            label = "User Name",
            placeholder = "User Name"
        )
        CustomOutlinedTextField(
            text = email,
            onTextChange = { onEmailValueChange(it) },
            label = "Email",
            placeholder = "Email address"
        )
        CustomOutlinedTextField(
            text = selectedDate.value,
            onTextChange = {},
            readOnly = true,
            label = "Birth Date",
            placeholder = selectedDate.value,
            trailingIcon = {
                IconButton(onClick = {
                    showDatePicker(
                        selectedDate = selectedDate,
                        year = year,
                        month = month,
                        day = day,
                        context = context
                    )
                }) {
                    Icon(
                        imageVector = Icons.Filled.DateRange,
                        contentDescription = ""
                    )
                }
            }
        )
        DropDownList(
            expanded = expanded,
            selectedItem = selectedItem,
            onExpandedChange = { onExpandedChange(it) },
            onDropDownMenuDismiss = { onDropDownMenuDismiss() },
            onDropDownItemClick = { onDropDownItemClick(it) },
            dropDownList = dropDownList,
            label = "Gender"
        )
        CustomOutlinedTextField(
            text = password,
            onTextChange = { onPasswordValueChange(it) },
            label = "Password",
            placeholder = "Password",
            trailingIcon = {
                IconButton(onClick = { onPasswordTrailingIconClick() }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.password_eye),
                        contentDescription = ""
                    )
                }
            },

            visible = passwordVisibility
        )
        CustomOutlinedTextField(
            text = repeatedPassword,
            onTextChange = { onRepeatedPasswordValueChange(it) },
            label = "Repeat Password",
            placeholder = "Password",
            trailingIcon = {
                IconButton(onClick = { onRepeatPasswordTrailingIconClick() }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.password_eye),
                        contentDescription = ""
                    )
                }
            },
            visible = repeatedPasswordVisibility
        )

        Button(onClick = { onSignUpButtonClick() }) {
            Text(text = "Sign Up", fontSize = 15.sp)
        }
        Spacer(modifier = Modifier.height(15.dp))
        Text(text = AnnotatedString("Log In"), modifier = Modifier.clickable {
            onSignInTextClick(
                UserInfo(
                    name = name,
                    birthDate = selectedDate.value,
                    password = password,
                    email = email
                )
            )
        })
    }
}
