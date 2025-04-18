package com.allinoneqrcode.qrcodescannerreader.extension

fun Long?.orZero(): Long {
    return this ?: 0L
}