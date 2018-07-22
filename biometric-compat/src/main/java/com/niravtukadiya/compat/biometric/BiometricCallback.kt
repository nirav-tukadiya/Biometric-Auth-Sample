package com.niravtukadiya.compat.biometric

/**
 * Created by Nirav Tukadiya on 22/07/18 8:58 PM.
 * nirav.tukadiya@gmail.com
 */

@Deprecated("This will be updated in upcoming versions.")
interface BiometricCallback {

    fun onSdkVersionNotSupported()

    fun onBiometricAuthenticationNotSupported()

    fun onBiometricAuthenticationNotAvailable()

    fun onBiometricAuthenticationPermissionNotGranted()

    fun onBiometricAuthenticationInternalError(error: String?)

    fun onAuthenticationFailed()

    fun onAuthenticationCancelled()

    fun onAuthenticationSuccessful()

    fun onAuthenticationHelp(helpCode: Int, helpString: CharSequence?)

    fun onAuthenticationError(errorCode: Int, errString: CharSequence?)
}
