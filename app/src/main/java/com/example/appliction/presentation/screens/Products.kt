package com.example.appliction.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.appliction.data.model.productModel.Product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayProducts(
    productsList: List<Product>,
    productsListRetrievedByToken: List<Product>,
    onUpdateButtonCLick: (Int) -> Unit,
    onDeleteButtonCLick: (Int) -> Unit,
    onAddButtonCLick: () -> Unit,
    onGetButtonClick:()->Unit
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Product List", fontSize = 15.sp, modifier = Modifier
                .background(
                    Color.DarkGray
                )
                .padding(12.dp)
                .fillMaxWidth()
        )
        LazyColumn(
            modifier = Modifier
                .weight(1F)
                .align(Alignment.Start)
                .fillMaxWidth()
        ) {
            items(productsList) {
                ListItem(
                    headlineContent = { Text(text = it.brand) },
                    supportingContent = { Text(text = it.description) },
                    trailingContent = { Text(text = AnnotatedString(it.stock.toString())) },
                    colors = ListItemDefaults.colors(
                        Color.Gray
                    ),
                    leadingContent = {
                        Image(
                            imageVector = Icons.Filled.Check,
                            contentDescription = ""
                        )
                    }
                )
                Spacer(modifier = Modifier.padding(5.dp))
            }

            items(productsListRetrievedByToken) {
                ListItem(
                    headlineContent = { Text(text = it.brand + " " + it.category) },
                    supportingContent = { Text(text = it.description) },
                    trailingContent = { Text(text = it.stock.toString()) },
                    colors = ListItemDefaults.colors(
                        Color.Gray
                    ),
                    leadingContent = {

                        Image(
                            modifier = Modifier.size(30.dp),
                            painter = rememberAsyncImagePainter(model = it.thumbnail),
                            contentDescription = ""
                        )
                    }
                )
                Spacer(modifier = Modifier.padding(5.dp))
            }
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(
                onClick = { onUpdateButtonCLick((0..10).random()) }
            ) {
                Text(text = "Update")
            }
            Button(
                onClick = { onDeleteButtonCLick((0..10).random()) }
            ) {
                Text(text = "Delete")
            }
            Button(
                onClick = { onAddButtonCLick() }
            ) {
                Text(text = "Add")
            }
            Button(
                onClick = { onGetButtonClick() }
            ) {
                Text(text = "Get")
            }
        }


    }
}