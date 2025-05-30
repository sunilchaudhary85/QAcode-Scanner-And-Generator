package com.allinoneqrcode.qrcodescannerreader.feature.barcode.save

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import com.allinoneqrcode.qrcodescannerreader.R
import com.allinoneqrcode.qrcodescannerreader.di.barcodeImageGenerator
import com.allinoneqrcode.qrcodescannerreader.di.barcodeImageSaver
import com.allinoneqrcode.qrcodescannerreader.di.permissionsHelper
import com.allinoneqrcode.qrcodescannerreader.extension.applySystemWindowInsets
import com.allinoneqrcode.qrcodescannerreader.extension.showError
import com.allinoneqrcode.qrcodescannerreader.extension.unsafeLazy
import com.allinoneqrcode.qrcodescannerreader.feature.BaseActivity
import com.allinoneqrcode.qrcodescannerreader.model.Barcode
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_save_barcode_as_image.*

class SaveBarcodeAsImageActivity : BaseActivity() {

    companion object {
        private const val REQUEST_PERMISSIONS_CODE = 101
        private val PERMISSIONS = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)

        private const val BARCODE_KEY = "BARCODE_KEY"

        fun start(context: Context, barcode: Barcode) {
            val intent = Intent(context, SaveBarcodeAsImageActivity::class.java).apply {
                putExtra(BARCODE_KEY, barcode)
            }
            context.startActivity(intent)
        }
    }

    private val barcode by unsafeLazy {
        intent?.getSerializableExtra(BARCODE_KEY) as? Barcode ?: throw IllegalArgumentException("No barcode passed")
    }

    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_barcode_as_image)
        supportEdgeToEdge()
        initToolbar()
        initFormatSpinner()
        initSaveButton()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (permissionsHelper.areAllPermissionsGranted(grantResults)) {
            saveBarcode()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }

    private fun supportEdgeToEdge() {
        root_view.applySystemWindowInsets(applyTop = true, applyBottom = true)
    }

    private fun initToolbar() {
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun initFormatSpinner() {
        spinner_save_as.adapter = ArrayAdapter.createFromResource(
            this, R.array.activity_save_barcode_as_image_formats, R.layout.item_spinner
        ).apply {
            setDropDownViewResource(R.layout.item_spinner_dropdown)
        }
    }

    private fun initSaveButton() {
        button_save.setOnClickListener {
            requestPermissions()
        }
    }

    private fun requestPermissions() {
        permissionsHelper.requestPermissions(this, PERMISSIONS, REQUEST_PERMISSIONS_CODE)
    }

    private fun saveBarcode() {
        val saveFunc = when (spinner_save_as.selectedItemPosition) {
            0 -> {
                barcodeImageGenerator
                    .generateBitmapAsync(barcode, 640, 640, 2)
                    .flatMapCompletable { barcodeImageSaver.savePngImageToPublicDirectory(this, it, barcode) }
            }
            1 -> {
                barcodeImageGenerator
                    .generateSvgAsync(barcode, 640, 640, 2)
                    .flatMapCompletable { barcodeImageSaver.saveSvgImageToPublicDirectory(this, it, barcode) }
            }
            else -> return
        }

        showLoading(true)

        saveFunc
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { showBarcodeSaved() },
                { error ->
                    showLoading(false)
                    showError(error)
                }
            )
            .addTo(disposable)
    }

    private fun showLoading(isLoading: Boolean) {
        progress_bar_loading.isVisible = isLoading
        scroll_view.isVisible = isLoading.not()
    }

    private fun showBarcodeSaved() {
        Toast.makeText(this, R.string.activity_save_barcode_as_image_file_name_saved, Toast.LENGTH_LONG).show()
        finish()
    }
}