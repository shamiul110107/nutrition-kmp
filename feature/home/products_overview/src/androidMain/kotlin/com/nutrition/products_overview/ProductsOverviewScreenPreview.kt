package com.nutrition.products_overview

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun ProductsOverviewScreenPreview() {
    MaterialTheme {
        ProductsOverviewScreen(
            navigateToDetails = { productId ->
                // Preview navigation - just print for demo
                println("Navigate to product details: $productId")
            }
        )
    }
}
