package com.nutrition.checkout.data

import com.nutrition.shared.domain.CartItem
import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Serializable
data class Order @OptIn(ExperimentalUuidApi::class) constructor(
    val id: String = Uuid.Companion.random().toHexString(),
    val customerId: String,
    val items: List<CartItem>,
    val totalAmount: Double,
    val token: String? = null
)