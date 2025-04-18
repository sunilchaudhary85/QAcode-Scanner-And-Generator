package com.allinoneqrcode.qrcodescannerreader.extension

import androidx.appcompat.app.AppCompatActivity
import com.allinoneqrcode.qrcodescannerreader.feature.common.dialog.ErrorDialogFragment

fun AppCompatActivity.showError(error: Throwable?) {
    val errorDialog =
        ErrorDialogFragment.newInstance(
            this,
            error
        )
    errorDialog.show(supportFragmentManager, "")
}