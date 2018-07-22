package com.niravtukadiya.compat.biometric

import android.content.Context
import android.content.DialogInterface
import android.view.View

import com.an.biometric.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.view_bottom_sheet.*

/**
 * Created by Nirav Tukadiya on 22/07/18 8:58 PM.
 * nirav.tukadiya@gmail.com
 */
open class BiometricDialogV23 : BottomSheetDialog, View.OnClickListener {

    lateinit var mContext: Context

    private lateinit var biometricCallback: BiometricCallback

    constructor(context: Context) : super(context, R.style.BottomSheetDialogTheme) {
        this.mContext = context.applicationContext
        setDialogView()
    }

    constructor(context: Context, biometricCallback: BiometricCallback) : super(context, R.style.BottomSheetDialogTheme) {
        this.mContext = context.applicationContext
        this.biometricCallback = biometricCallback
        setDialogView()
    }

    constructor(context: Context, theme: Int) : super(context, theme) {}

    protected constructor(context: Context, cancelable: Boolean, cancelListener: DialogInterface.OnCancelListener) : super(context, cancelable, cancelListener) {}

    private fun setDialogView() {
        val bottomSheetView = layoutInflater.inflate(R.layout.view_bottom_sheet, null)
        setContentView(bottomSheetView)
        btn_cancel.setOnClickListener(this)
        updateLogo()
    }

    fun setTitle(title: String?) {
        item_title.text = title
    }

    fun updateStatus(status: String?) {
        item_status.text = status
    }

    fun setSubtitle(subtitle: String?) {
        item_subtitle.text = subtitle
    }

    fun setDescription(description: String?) {
        item_description.text = description
    }

    fun setButtonText(negativeButtonText: String?) {
        btn_cancel.text = negativeButtonText
    }

    private fun updateLogo() {
        try {
            val drawable = context.packageManager.getApplicationIcon(context.packageName)
            img_logo.setImageDrawable(drawable)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onClick(view: View) {
        dismiss()
        biometricCallback.onAuthenticationCancelled()
    }
}
