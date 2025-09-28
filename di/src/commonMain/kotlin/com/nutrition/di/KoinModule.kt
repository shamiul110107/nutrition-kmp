package com.nutrition.di

import com.nutrisport.manage_product.viewModel.ManageProductViewModel
import com.nutrition.admin_panel.AdminPanelViewModel
import com.nutrition.auth.AuthViewModel
import com.nutrition.cart.CartViewModel
import com.nutrition.categories.presentation.category_search.CategorySearchViewModel
import com.nutrition.checkout.CheckoutViewModel
import com.nutrition.checkout.data.OrderRepositoryImpl
import com.nutrition.checkout.data.PaypalApi
import com.nutrition.checkout.domain.OrderRepository
import com.nutrition.data.AdminRepositoryImpl
import com.nutrition.data.CustomerRepositoryImpl
import com.nutrition.data.ProductRepositoryImpl
import com.nutrition.data.domain.AdminRepository
import com.nutrition.data.domain.CustomerRepository
import com.nutrition.data.domain.ProductRepository
import com.nutrition.details.component.DetailsViewModel
import com.nutrition.home.HomeGraphViewModel
import com.nutrition.payment_completed.PaymentViewModel
import com.nutrition.products_overview.ProductsOverviewViewModel
import com.nutrition.profile.viewModel.ProfileViewModel
import com.nutrition.shared.util.IntentHandler
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val sharedModule = module {
    single<CustomerRepository> { CustomerRepositoryImpl() }
    single<AdminRepository> { AdminRepositoryImpl() }
    single<ProductRepository> { ProductRepositoryImpl() }
    single<OrderRepository> { OrderRepositoryImpl(get()) }
    single<IntentHandler> { IntentHandler() }
    single<PaypalApi> { PaypalApi() }
    viewModelOf(::AuthViewModel)
    viewModelOf(::HomeGraphViewModel)
    viewModelOf(::ProfileViewModel)
    viewModelOf(::ManageProductViewModel)
    viewModelOf(::AdminPanelViewModel)
    viewModelOf(::ProductsOverviewViewModel)
    viewModelOf(::DetailsViewModel)
    viewModelOf(::CartViewModel)
    viewModelOf(::CategorySearchViewModel)
    viewModelOf(::CheckoutViewModel)
    viewModelOf(::PaymentViewModel)
}
fun initializeKoin(
    config: (KoinApplication.() -> Unit)? = null
) {
    startKoin {
        config?.invoke(this)
        modules(sharedModule)
    }
}