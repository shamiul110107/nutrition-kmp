package com.nutrition.di

import com.nutrisport.manage_product.viewModel.ManageProductViewModel
import com.nutrition.admin_panel.AdminPanelViewModel
import com.nutrition.auth.AuthViewModel
import com.nutrition.data.AdminRepositoryImpl
import com.nutrition.data.CustomerRepositoryImp
import com.nutrition.data.domain.AdminRepository
import com.nutrition.data.domain.CustomerRepository
import com.nutrition.home.HomeGraphViewModel
import com.nutrition.profile.viewModel.ProfileViewModel
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val sharedModule = module {
    single<CustomerRepository> { CustomerRepositoryImp() }

    single<AdminRepository> { AdminRepositoryImpl() }
    viewModelOf(::AdminPanelViewModel)

    viewModelOf(::AuthViewModel)
    viewModelOf(::HomeGraphViewModel)
    viewModelOf(::ProfileViewModel)
    viewModelOf(::ManageProductViewModel)
}
fun initializeKoin(
    config: (KoinApplication.() -> Unit)? = null
) {
    startKoin {
        config?.invoke(this)
        modules(sharedModule)
    }
}