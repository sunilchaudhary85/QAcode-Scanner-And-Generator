package com.allinoneqrcode.qrcodescannerreader.extension

fun Double?.orZero(): Double {
    return this ?: 0.0
}