package com.allinoneqrcode.qrcodescannerreader.feature.tabs.create.qr

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.allinoneqrcode.qrcodescannerreader.R
import com.allinoneqrcode.qrcodescannerreader.extension.isNotBlank
import com.allinoneqrcode.qrcodescannerreader.extension.textString
import com.allinoneqrcode.qrcodescannerreader.feature.tabs.create.BaseCreateBarcodeFragment
import com.allinoneqrcode.qrcodescannerreader.model.schema.Schema
import com.allinoneqrcode.qrcodescannerreader.model.schema.Url
import kotlinx.android.synthetic.main.fragment_create_qr_code_url.*

class CreateQrCodeUrlFragment : BaseCreateBarcodeFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create_qr_code_url, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showUrlPrefix()
        handleTextChanged()
    }

    override fun getBarcodeSchema(): Schema {
        return Url(edit_text.textString)
    }

    private fun showUrlPrefix() {
        val prefix = "https://"
        edit_text.apply {
            setText(prefix)
            setSelection(prefix.length)
            requestFocus()
        }
    }

    private fun handleTextChanged() {
        edit_text.addTextChangedListener {
            parentActivity.isCreateBarcodeButtonEnabled = edit_text.isNotBlank()
        }
    }
}