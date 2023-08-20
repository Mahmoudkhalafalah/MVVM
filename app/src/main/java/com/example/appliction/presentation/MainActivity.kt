package com.example.appliction.presentation//package com.example.appliction.domain.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appliction.presentation.screens.DisplayProducts
import com.example.appliction.presentation.screens.LoginPage
import com.example.appliction.presentation.screens.SignUp
import com.example.appliction.presentation.ui.theme.ApplictionTheme
import com.example.appliction.presentation.viewmodels.LoginPageViewModel
import com.example.appliction.presentation.viewmodels.ProductsScreenViewModel
import com.example.appliction.presentation.viewmodels.SignUpPageViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApplictionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val productsViewModel by viewModels<ProductsScreenViewModel>()
                    val mainNavController = rememberNavController()
                    val loginPageViewModel by viewModels<LoginPageViewModel>()
                    val signUpViewModel by viewModels<SignUpPageViewModel>()
                    LaunchedEffect(key1 = true) {
                        loginPageViewModel.found.collect {
                            if (it) {
                                Toast.makeText(this@MainActivity, "found", Toast.LENGTH_SHORT)
                                    .show()
                                mainNavController.navigate("Home Screen")
                            } else
                                Toast.makeText(this@MainActivity, "Not found", Toast.LENGTH_SHORT)
                                    .show()
                        }
                    }
                    LaunchedEffect(key1 = true) {
                        productsViewModel.trowError.collect {

                            Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT)
                                .show()

                        }
                    }
                    NavHost(navController = mainNavController, startDestination = "Login Screen") {
                        composable("Login Screen") {
                            LoginPage(
                                userName = loginPageViewModel.useName.value,
                                onUSerNameTextChange = {
                                    loginPageViewModel.changeUserNameTextFieldValue(
                                        it
                                    )
                                },
                                password = loginPageViewModel.password.value,
                                onPasswordTextChange = {
                                    loginPageViewModel.changePasswordTextFieldValue(
                                        it
                                    )
                                },
                                visibility = loginPageViewModel.visibility.value,
                                onTrailingIconClick = { loginPageViewModel.changePasswordVisibility() },
                                onSignInButtonClick = { userName ->
                                    loginPageViewModel.goToHomeScreen(userName)
                                },
                                onSignUpTextClick = { mainNavController.navigate("SignUp Screen") }
                            )
                        }
                        composable("SignUp Screen") {
                            SignUp(
                                name = signUpViewModel.userName.value,
                                onNameValueChange = {
                                    signUpViewModel.changeUserNameTextFieldValue(
                                        it
                                    )
                                },
                                password = signUpViewModel.password.value,
                                onPasswordValueChange = {
                                    signUpViewModel.changePasswordTextFieldValue(
                                        it
                                    )
                                },
                                email = signUpViewModel.email.value,
                                onEmailValueChange = { signUpViewModel.changeEmailTextFieldValue(it) },
                                onPasswordTrailingIconClick = { signUpViewModel.changePasswordVisibility() },
                                passwordVisibility = signUpViewModel.passwordVisibility.value,
                                repeatedPasswordVisibility = signUpViewModel.repeatedPasswordVisibility.value,
                                onSignUpButtonClick = { mainNavController.navigate("Login Screen") },
                                repeatedPassword = signUpViewModel.repeatedPassword.value,
                                onRepeatedPasswordValueChange = {
                                    signUpViewModel.changeRepeatPasswordTextFieldValue(
                                        it
                                    )
                                },
                                onRepeatPasswordTrailingIconClick = { signUpViewModel.changeRepeatedPasswordVisibility() },
                                year = signUpViewModel.year,
                                month = signUpViewModel.month,
                                day = signUpViewModel.day,
                                selectedDate = signUpViewModel.selectedDate,
                                expanded = signUpViewModel.expanded.value,
                                onDropDownMenuDismiss = { signUpViewModel.dismissDropDownMenu() },
                                context = this@MainActivity,
                                onDropDownItemClick = { signUpViewModel.selectItemFromMenu(it) },
                                onExpandedChange = { signUpViewModel.changeExpandState(it) },
                                onSignInTextClick = {
                                    signUpViewModel.registerUser(it)
                                    mainNavController.navigate("Login Screen")
                                }
                            )

                        }
                        composable("Home Screen") {
                            DisplayProducts(
                                productsList = productsViewModel.productsList.value,
                                productsListRetrievedByToken = productsViewModel.productsListWithAuth.value,
                                onUpdateButtonCLick = {},
                                onDeleteButtonCLick = {},
                                onAddButtonCLick = { productsViewModel.getProductsWithAuth() },
                                onGetButtonClick = { productsViewModel.getProducts() }

                            )
                        }
                    }


                }
            }
        }
    }
}

