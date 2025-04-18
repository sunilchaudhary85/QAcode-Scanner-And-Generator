package com.allinoneqrcode.qrcodescannerreader.extension

fun Boolean?.orFalse(): Boolean {
    return this ?: false
}