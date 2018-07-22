package com.niravtukadiya.compat.biometric

import android.annotation.TargetApi
import android.content.Context
import android.content.DialogInterface
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.CancellationSignal

/**
 * Created by Nirav Tukadiya on 22/07/18 8:58 PM.
 * nirav.tukadiya@gmail.com
 */
class BiometricCompat protected constructor(biometricBuilder: BiometricBuilder) : BiometricCompatV23() {


    init {
        this.context = biometricBuilder.context
        this.title = biometricBuilder.title
        this.subtitle = biometricBuilder.subtitle
        this.description = biometricBuilder.description
        this.negativeButtonText = biometricBuilder.negativeButtonText
    }


    fun authenticate(biometricCallback: BiometricCallback) {

        if (title == null) {
            biometricCallback.onBiometricAuthenticationInternalError("Biometric Dialog title cannot be null")
        }


        if (subtitle == null) {
            biometricCallback.onBiometricAuthenticationInternalError("Biometric Dialog subtitle cannot be null")
        }


        if (description == null) {
            biometricCallback.onBiometricAuthenticationInternalError("Biometric Dialog description cannot be null")
        }

        if (negativeButtonText == null) {
            biometricCallback.onBiometricAuthenticationInternalError("Biometric Dialog negative button text cannot be null")
        }


        if (!BiometricUtils.isSdkVersionSupported) {
            biometricCallback.onSdkVersionNotSupported()
        }

        if (!BiometricUtils.isPermissionGranted(context)) {
            biometricCallback.onBiometricAuthenticationPermissionNotGranted()
        }

        if (!BiometricUtils.isHardwareSupported(context)) {
            biometricCallback.onBiometricAuthenticationNotSupported()
        }

        if (!BiometricUtils.isFingerprintAvailable(context)) {
            biometricCallback.onBiometricAuthenticationNotAvailable()
        }

        displayBiometricDialog(biometricCallback)
    }


    private fun displayBiometricDialog(biometricCallback: BiometricCallback) {
        if (BiometricUtils.isBiometricPromptEnabled) {
            displayBiometricPrompt(biometricCallback)
        } else {
            displayBiometricPromptV23(biometricCallback)
        }
    }


    @TargetApi(Build.VERSION_CODES.P)
    private fun displayBiometricPrompt(biometricCallback: BiometricCallback) {
        BiometricPrompt.Builder(context)
                .setTitle(title)
                .setSubtitle(subtitle)
                .setDescription(description)
                .setNegativeButton(negativeButtonText, context.mainExecutor, DialogInterface.OnClickListener { dialogInterface, i -> biometricCallback.onAuthenticationCancelled() })
                .build()
                .authenticate(CancellationSignal(), context.mainExecutor,
                        BiometricCallbackV28(biometricCallback))
    }


    class BiometricBuilder(val context: Context) {

        var title: String? = null
        var subtitle: String? = null
        var description: String? = null
        var negativeButtonText: String? = null

        fun setTitle(title: String): BiometricBuilder {
            this.title = title
            return this
        }

        fun setSubtitle(subtitle: String): BiometricBuilder {
            this.subtitle = subtitle
            return this
        }

        fun setDescription(description: String): BiometricBuilder {
            this.description = description
            return this
        }


        fun setNegativeButtonText(negativeButtonText: String): BiometricBuilder {
            this.negativeButtonText = negativeButtonText
            return this
        }

        fun build(): BiometricCompat {
            return BiometricCompat(this)
        }
    }
}
