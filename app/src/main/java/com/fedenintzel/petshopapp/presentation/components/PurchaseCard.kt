package com.fedenintzel.petshopapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fedenintzel.petshopapp.domain.model.Cart

@Composable
fun PurchaseCard(cart: Cart) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Compra del: ${cart.timestamp?.toDate()?.toLocaleString()}")
            Text("Total: $${cart.total}")
            Spacer(Modifier.height(8.dp))
            cart.products.forEach {
                Text("- ${it.title} x${it.quantity}")
            }
        }
    }
}
