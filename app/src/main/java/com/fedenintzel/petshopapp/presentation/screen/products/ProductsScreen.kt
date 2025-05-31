package com.fedenintzel.petshopapp.presentation.screen.products

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fedenintzel.petshopapp.presentation.viewmodel.ProductsViewModel

@Composable
fun ProductsScreen(viewModel: ProductsViewModel = viewModel()) {
    val products by viewModel.products.collectAsState()
    val error by viewModel.error.collectAsState()

    if (error != null) {
        Text("Error: $error", color = MaterialTheme.colorScheme.error)
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ) {
            items(products) { product ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(product.title, style = MaterialTheme.typography.titleMedium)
                        Text("Precio: \$${product.price}", style = MaterialTheme.typography.bodyMedium)
                        Text(product.description, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}

