package com.nutrition.di

import com.nutrisport.manage_product.view.PhotoPicker
import org.koin.dsl.module

actual val targetModule = module {
    single<PhotoPicker> { PhotoPicker() }
}