package com.nutrition.di

import com.nutrition.auth.AuthViewModel
import com.nutrition.data.CustomerRepositoryImp
import com.nutrition.data.domain.CustomerRepository
import com.nutrition.home.HomeGraphViewModel
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val sharedModule = module {
    single<CustomerRepository> { CustomerRepositoryImp() }
    viewModelOf(::AuthViewModel)
    viewModelOf(::HomeGraphViewModel)
}
fun initializeKoin(
    config: (KoinApplication.() -> Unit)? = null
) {
    startKoin {
        config?.invoke(this)
        modules(sharedModule)
    }
}