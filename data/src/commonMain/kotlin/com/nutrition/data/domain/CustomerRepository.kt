package com.nutrition.data.domain

import com.nutrisport.shared.util.RequestState
import com.nutrition.shared.domain.Customer
import dev.gitlive.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface CustomerRepository {
    fun getCurrentUserId(): String?
    suspend fun createCustomer(
        user: FirebaseUser?,
        onSuccess: () -> Unit,
        onError: (String) -> Unit,
    )
    suspend fun updateCustomer(
        customer: Customer,
        onSuccess: () -> Unit,
        onError: (String) -> Unit,
    )

    fun readCustomerFlow(): Flow<RequestState<Customer>>
    suspend fun signOut(): RequestState<Unit>
}