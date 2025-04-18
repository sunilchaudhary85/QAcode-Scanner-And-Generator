package com.allinoneqrcode.qrcodescannerreader.extension

fun Int?.orZero(): Int {
    return this ?: 0
}