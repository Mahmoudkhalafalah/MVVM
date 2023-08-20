package com.example.appliction.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appliction.domain.model.CheckForUserUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LoginPageViewModel(private val findUser: CheckForUserUseCase = CheckForUserUseCase()) :
    ViewModel() {

    private val _userName = mutableStateOf("")
    val useName = _userName

    private val _found = MutableSharedFlow<Boolean>()
    val found = _found.asSharedFlow()
    private val _password = mutableStateOf("")
    val password = _password

    private val _visibility = mutableStateOf(false)
    val visibility = _visibility

    fun changeUserNameTextFieldValue(text: String) {
        _userName.value = text
    }

    fun changePasswordTextFieldValue(text: String) {
        _password.value = text
    }

    fun changePasswordVisibility() {
        _visibility.value = _visibility.value.not()
    }

    fun goToHomeScreen(name: String) {
        viewModelScope.launch {
            _found.emit(findUser.findUser(name))
        }
    }

}