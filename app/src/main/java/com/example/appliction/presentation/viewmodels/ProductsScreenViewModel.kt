package com.example.appliction.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appliction.data.model.productModel.Product
import com.example.appliction.domain.model.GetProductUseCase
import com.example.appliction.domain.model.GetProductsWithAuthUseCase
import com.example.appliction.domain.model.GetTokenFromSharedPrefUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ProductsScreenViewModel(
    private val getProducts: GetProductUseCase = GetProductUseCase(),
    private val getProductsWithToken: GetProductsWithAuthUseCase = GetProductsWithAuthUseCase(),
    private val getToken: GetTokenFromSharedPrefUseCase = GetTokenFromSharedPrefUseCase(),
) : ViewModel() {

    val handler = CoroutineExceptionHandler { _, exception ->
        if(exception is HttpException){
            when(exception.code()){
                400->{
                    viewModelScope.launch {
                        _throwError.emit("bad response")
                    }
                }
                500->{
                    viewModelScope.launch {
                        _throwError.emit("internal server error")
                    }
                }
                401->{
                    viewModelScope.launch {
                        _throwError.emit("un authorized")
                    }

                }
                403->{
                    viewModelScope.launch {
                        _throwError.emit("Error")
                    }

                }


            }
        }

    }
    val productsList = mutableStateOf<List<Product>>(emptyList())
    val productsListWithAuth = mutableStateOf<List<Product>>(emptyList())
    private val _throwError = MutableSharedFlow<String>()
    val trowError = _throwError.asSharedFlow()
    private var token: String = ""
    fun getProducts() {
        viewModelScope.launch(handler) {
            productsList.value = getProducts.getProducts().products
        }
    }

    fun getProductsWithAuth() {
        viewModelScope.launch(handler) {
            token = getToken.getToken() ?: ""
            productsListWithAuth.value = getProductsWithToken.getProductsWithAuth(token).products
        }
    }
}