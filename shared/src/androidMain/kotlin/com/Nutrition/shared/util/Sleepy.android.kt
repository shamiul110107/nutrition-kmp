package com.nutrition.shared.util

actual fun threadSleep(millis: Long) {
    Thread.sleep(millis)
}