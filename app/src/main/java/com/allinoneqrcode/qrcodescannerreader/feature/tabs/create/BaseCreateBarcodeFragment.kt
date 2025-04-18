package com.allinoneqrcode.qrcodescannerreader.feature.tabs.create

import androidx.fragment.app.Fragment
import com.allinoneqrcode.qrcodescannerreader.extension.*
import com.allinoneqrcode.qrcodescannerreader.model.Contact
import com.allinoneqrcode.qrcodescannerreader.model.schema.Other
import com.allinoneqrcode.qrcodescannerreader.model.schema.Schema

abstract class BaseCreateBarcodeFragment : Fragment() {
    protected val parentActivity by unsafeLazy { requireActivity() as CreateBarcodeActivity }

    open val latitude: Double? = null
    open val longitude: Double? = null

    open fun getBarcodeSchema(): Schema = Other("")
    open fun showPhone(phone: String) {}
    open fun showContact(contact: Contact) {}
    open fun showLocation(latitude: Double?, longitude: Double?) {}
}