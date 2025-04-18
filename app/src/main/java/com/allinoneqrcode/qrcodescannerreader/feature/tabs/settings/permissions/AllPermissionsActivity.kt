package com.allinoneqrcode.qrcodescannerreader.feature.tabs.settings.permissions

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.allinoneqrcode.qrcodescannerreader.R
import com.allinoneqrcode.qrcodescannerreader.extension.applySystemWindowInsets
import com.allinoneqrcode.qrcodescannerreader.feature.BaseActivity
import kotlinx.android.synthetic.main.activity_all_permissions.*

class AllPermissionsActivity : BaseActivity() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, AllPermissionsActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_permissions)
        root_view.applySystemWindowInsets(applyTop = true, applyBottom = true)
        toolbar.setNavigationOnClickListener { finish() }
    }
}