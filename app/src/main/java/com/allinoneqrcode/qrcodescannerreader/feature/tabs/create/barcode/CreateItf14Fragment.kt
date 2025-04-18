package com.allinoneqrcode.qrcodescannerreader.feature.tabs.create.barcode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.allinoneqrcode.qrcodescannerreader.R
import com.allinoneqrcode.qrcodescannerreader.extension.textString
import com.allinoneqrcode.qrcodescannerreader.feature.tabs.create.BaseCreateBarcodeFragment
import com.allinoneqrcode.qrcodescannerreader.model.schema.Other
import com.allinoneqrcode.qrcodescannerreader.model.schema.Schema
import kotlinx.android.synthetic.main.fragment_create_itf_14.*

class CreateItf14Fragment : BaseCreateBarcodeFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create_itf_14, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        edit_text.requestFocus()
        edit_text.addTextChangedListener {
            parentActivity.isCreateBarcodeButtonEnabled = edit_text.text.length % 2 == 0
        }
    }

    override fun getBarcodeSchema(): Schema {
        return Other(edit_text.textString)
    }
}