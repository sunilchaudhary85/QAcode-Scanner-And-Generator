package com.allinoneqrcode.qrcodescannerreader.extension

import com.allinoneqrcode.qrcodescannerreader.model.Barcode
import com.google.zxing.Result

fun Result.equalTo(barcode: Barcode?): Boolean {
    return barcodeFormat == barcode?.format && text == barcode?.text
}