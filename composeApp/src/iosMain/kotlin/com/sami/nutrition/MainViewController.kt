package com.sami.nutrition

import androidx.compose.ui.window.ComposeUIViewController
import com.nutrition.di.initializeKoin

fun MainViewController() = ComposeUIViewController(
    configure = { initializeKoin() }
) {
    App()
}