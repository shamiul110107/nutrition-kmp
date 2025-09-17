package com.nutrition.data

import com.nutrisport.shared.util.RequestState
import com.nutrition.data.domain.CustomerRepository
import com.nutrition.shared.domain.Customer
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest

class CustomerRepositoryImp: CustomerRepository {
    override fun getCurrentUserId(): String? {
        return Firebase.auth.currentUser?.uid
    }

    override suspend fun createCustomer(
        user: FirebaseUser?,
        onSuccess: () -> Unit,
        onError: (String) -> Unit,
    ) {
        try {
            if (user != null) {
                val customerCollection = Firebase.firestore.collection("customers")
                val customer = Customer(
                    id = user.uid,
                    firstName = user.displayName?.split("")?.firstOrNull() ?: "Unknown",
                    lastName = user.displayName?.split("")?.lastOrNull() ?: "Unknown",
                    email = user.email ?: "Unknown",
                    )
                val customerExist = customerCollection.document(user.uid).get().exists
                if (customerExist) {
                    onSuccess()
                } else {
                    customerCollection.document(user.uid).set(customer)
                    onSuccess()
                }
            } else {
                onError("User is not available")
            }
        } catch(e: Exception) {
            onError("Error while creating a Customer: ${e.message}")
        }
    }

    override fun readCustomerFlow(): Flow<RequestState<Customer>> = channelFlow {
        try {
            val userId = getCurrentUserId()
            if (userId != null) {
                val database = Firebase.firestore
                database.collection("customers")
                    .document(userId)
                    .snapshots
                    .collectLatest { document ->
                        if (document.exists) {
                            val customer = Customer(
                                id = document.id,
                                firstName = document.get("firstName") as String,
                                lastName = document.get("lastName") as String,
                                email = document.get(field = "email"),
                                city = document.get(field = "city"),
                                postalCode = document.get(field = "postalCode"),
                                address = document.get(field = "address"),
                                phoneNumber = document.get(field = "phoneNumber"),
                                cart = document.get(field = "cart")
                            )
                            send(RequestState.Success(data = customer))
                        } else {
                            send(RequestState.Error("Queried customer document does not exist."))
                        }
                    }
            } else {
                send(RequestState.Error("User is not available."))
            }
        } catch (e: Exception) {
            send(RequestState.Error("Error while reading a Customer Information: ${e.message}"))
        }
    }

    override suspend fun updateCustomer(
        customer: Customer,
        onSuccess: () -> Unit,
        onError: (String) -> Unit,
    ) {
        try {
            val userId = getCurrentUserId()
            if (userId != null) {
                val firestore = Firebase.firestore
                val customerCollection = firestore.collection(collectionPath = "customers")

                val existingCustomer = customerCollection
                    .document(customer.id)
                    .get()
                if (existingCustomer.exists) {
                    customerCollection
                        .document(customer.id)
                        .update(
                            "firstName" to customer.firstName,
                            "lastName" to customer.lastName,
                            "city" to customer.city,
                            "postalCode" to customer.postalCode,
                            "address" to customer.address,
                            "phoneNumber" to customer.phoneNumber
                        )
                    onSuccess()
                } else {
                    onError("Customer not found.")
                }
            } else {
                onError("User is not available.")
            }
        } catch (e: Exception) {
            onError("Error while updating a Customer information: ${e.message}")
        }
    }

    override suspend fun signOut(): RequestState<Unit> {
        return try {
            Firebase.auth.signOut()
            RequestState.Success(data = Unit)
        } catch (e: Exception) {
            RequestState.Error("Error while signing out: ${e.message}")
        }
    }
}