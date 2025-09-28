package com.nutrition.products_overview.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nutrition.shared.domain.Product
import com.nutrition.shared.domain.ProductCategory

@Preview(showBackground = true)
@Composable
fun MainProductCardPreview() {
    MaterialTheme {
        MainProductCard(
            product = Product(
                id = "preview-1",
                title = "Whey Protein Premium",
                description = "High-quality whey protein powder for muscle building and recovery.",
                thumbnail = "https://via.placeholder.com/300x200",
                category = "Protein",
                weight = 1000,
                price = 49.99,
                isPopular = true,
                isDiscounted = false,
                isNew = true
            ),
            isLarge = false,
            onClick = { productId ->
                println("Clicked product: $productId")
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainProductCardLargePreview() {
    MaterialTheme {
        MainProductCard(
            product = Product(
                id = "preview-2",
                title = "Creatine Monohydrate",
                description = "Pure creatine monohydrate powder for enhanced strength and power.",
                thumbnail = "https://via.placeholder.com/300x200",
                category = "Creatine",
                weight = 500,
                price = 24.99,
                isPopular = false,
                isDiscounted = true,
                isNew = false
            ),
            isLarge = true,
            onClick = { productId ->
                println("Clicked product: $productId")
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainProductCardAccessoriesPreview() {
    MaterialTheme {
        MainProductCard(
            product = Product(
                id = "preview-3",
                title = "Shaker Bottle",
                description = "High-quality shaker bottle with mixing ball for perfect protein shakes.",
                thumbnail = "https://via.placeholder.com/300x200",
                category = "Accessories",
                weight = null, // Accessories don't have weight
                price = 12.99,
                isPopular = false,
                isDiscounted = false,
                isNew = false
            ),
            isLarge = false,
            onClick = { productId ->
                println("Clicked product: $productId")
            }
        )
    }
}
