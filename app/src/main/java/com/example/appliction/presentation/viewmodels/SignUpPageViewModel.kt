package com.example.appliction.presentation.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.appliction.data.persestence.UserInfo
import com.example.appliction.domain.model.InsertUserUseCase
import java.util.Calendar

class SignUpPageViewModel(private val insert: InsertUserUseCase  = InsertUserUseCase()) :
    ViewModel() {

    private val _userName = mutableStateOf("")
    val userName = _userName

    private val _email = mutableStateOf("")
    val email = _email

    private val _password = mutableStateOf("")
    val password = _password

    private val _repeatedPassword = mutableStateOf("")
    val repeatedPassword = _repeatedPassword

    private val _passwordVisibility = mutableStateOf(false)
    val passwordVisibility = _passwordVisibility

    private val _repeatedPasswordVisibility = mutableStateOf(false)
    val repeatedPasswordVisibility = _repeatedPasswordVisibility

    private val _selectedItem = mutableStateOf("Select")
    val selectedItem = _selectedItem

    private val _expanded = mutableStateOf(false)
    val expanded = _expanded

    private val calender: Calendar = Calendar.getInstance()
    val year = calender.get(Calendar.YEAR)
    val month = calender.get(Calendar.MONTH) + 1
    val day = calender.get(Calendar.DAY_OF_MONTH)
    val selectedDate: MutableState<String> = mutableStateOf("$day/$month/$year")

    fun changeUserNameTextFieldValue(text: String) {
        _userName.value = text
    }

    fun changePasswordTextFieldValue(text: String) {
        _password.value = text
    }

    fun changeRepeatPasswordTextFieldValue(text: String) {
        _repeatedPassword.value = text
    }

    fun changeEmailTextFieldValue(text: String) {
        _email.value = text
    }

    fun changePasswordVisibility() {
        _passwordVisibility.value = _passwordVisibility.value.not()
    }

    fun changeRepeatedPasswordVisibility() {
        _repeatedPasswordVisibility.value = _repeatedPasswordVisibility.value.not()
    }

    fun changeExpandState(state: Boolean) {
        _expanded.value = state
    }

    fun registerUser(userInfo: UserInfo) {
        insert.insertUser(userInfo)
    }

    fun dismissDropDownMenu() {
        _expanded.value = false
    }

    fun selectItemFromMenu(item: String) {
        _expanded.value = false
        _selectedItem.value = item
    }


}