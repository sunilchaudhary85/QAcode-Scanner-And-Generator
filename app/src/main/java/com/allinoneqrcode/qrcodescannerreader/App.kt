package com.allinoneqrcode.qrcodescannerreader

import androidx.multidex.MultiDexApplication
import com.allinoneqrcode.qrcodescannerreader.di.settings
import com.allinoneqrcode.qrcodescannerreader.usecase.Logger
import io.reactivex.plugins.RxJavaPlugins

class App : MultiDexApplication() {

    override fun onCreate() {
        handleUnhandledRxJavaErrors()
        applyTheme()
        super.onCreate()
    }

    private fun applyTheme() {
        settings.reapplyTheme()
    }

    private fun handleUnhandledRxJavaErrors() {
        RxJavaPlugins.setErrorHandler { error ->
            Logger.log(error)
        }
    }
}