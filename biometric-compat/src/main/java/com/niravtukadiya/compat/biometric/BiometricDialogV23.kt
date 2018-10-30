package com.niravtukadiya.compat.biometric

import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialog

/**
 * Created by Nirav Tukadiya on 22/07/18 8:58 PM.
 * nirav.tukadiya@gmail.com
 */
open class BiometricDialogV23 : BottomSheetDialog, View.OnClickListener {

    lateinit var mContext: Context
    var layoutRes: Int = R.layout.bc_view_bottom_sheet

    private lateinit var biometricCallback: BiometricCallback

    var bc_btn_cancel: Button? = null
    var bc_item_title: TextView? = null
    var bc_item_status: TextView? = null
    var bc_item_subtitle: TextView? = null
    var bc_item_description: TextView? = null
    var bc_img_logo: ImageView? = null

    constructor(context: Context) : super(context, R.style.BottomSheetDialogTheme) {
        this.mContext = context.applicationContext
        setDialogView()
    }

    constructor(context: Context, biometricCallback: BiometricCallback, layout: Int? = R.layout.bc_view_bottom_sheet) : super(context, R.style.BottomSheetDialogTheme) {
        this.mContext = context.applicationContext
        this.layoutRes = if (layout!=null && layout != 0) layout else R.layout.bc_view_bottom_sheet
        this.biometricCallback = biometricCallback
        setDialogView()
    }

    constructor(context: Context, theme: Int) : super(context, theme) {}

    protected constructor(context: Context, cancelable: Boolean, cancelListener: DialogInterface.OnCancelListener) : super(context, cancelable, cancelListener) {}

    private fun setDialogView() {

        val bottomSheetView: View = layoutInflater.inflate(layoutRes, null)
        setContentView(bottomSheetView)
        initViews(bottomSheetView)
        bc_btn_cancel?.setOnClickListener(this)
        updateLogo()
    }

    private fun initViews(bottomSheetView: View) {

        bc_btn_cancel = bottomSheetView.findViewById(R.id.bc_btn_cancel)
        bc_item_title = bottomSheetView.findViewById(R.id.bc_item_title)
        bc_item_status = bottomSheetView.findViewById(R.id.bc_item_status)
        bc_item_subtitle = bottomSheetView.findViewById(R.id.bc_item_subtitle)
        bc_item_description = bottomSheetView.findViewById(R.id.bc_item_description)
        bc_img_logo = bottomSheetView.findViewById(R.id.bc_img_logo)
    }

    fun setTitle(title: String?) {
        bc_item_title?.text = title
    }

    fun updateStatus(status: String?) {
        bc_item_status?.text = status
    }

    fun setSubtitle(subtitle: String?) {
        bc_item_subtitle?.text = subtitle
    }

    fun setDescription(description: String?) {
        bc_item_description?.text = description
    }

    fun setButtonText(negativeButtonText: String?) {
        bc_btn_cancel?.text = negativeButtonText
    }

    private fun updateLogo() {
        try {
            val drawable = context.packageManager.getApplicationIcon(context.packageName)
            bc_img_logo?.setImageDrawable(drawable)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onClick(view: View) {
        dismiss()
        biometricCallback.onAuthenticationCancelled()
    }
}
