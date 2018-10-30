package com.niravtukadiya.compat.biometric.sample

import android.os.Bundle
import android.widget.Toast

import com.niravtukadiya.compat.biometric.BiometricCallback
import com.niravtukadiya.compat.biometric.BiometricCompat
import androidx.appcompat.app.AppCompatActivity
import com.niravtukadiya.compat.biometric.BiometricError
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Nirav Tukadiya on 22/07/18 8:58 PM.
 * nirav.tukadiya@gmail.com
 */
class MainActivity : AppCompatActivity(), BiometricCallback {
    override fun onPreConditionsFailed(error: BiometricError) {

        when(error){
            BiometricError.ON_SDK_NOT_SUPPORTED -> Toast.makeText(applicationContext, getString(R.string.biometric_error_sdk_not_supported), Toast.LENGTH_LONG).show()
            BiometricError.ON_BIOMETRIC_AUTH_NOT_SUPPORTED -> Toast.makeText(applicationContext, getString(R.string.biometric_error_hardware_not_supported), Toast.LENGTH_LONG).show()
            BiometricError.ON_BIOMETRIC_AUTH_NOT_AVAILABLE -> Toast.makeText(applicationContext, getString(R.string.biometric_error_fingerprint_not_available), Toast.LENGTH_LONG).show()
            BiometricError.ON_BIOMETRIC_AUTH_PERMISSION_NOT_GRANTED -> Toast.makeText(applicationContext, getString(R.string.biometric_error_permission_not_granted), Toast.LENGTH_LONG).show()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_authenticate.setOnClickListener {
            BiometricCompat.BiometricBuilder(this)
                    .setTitle(getString(R.string.biometric_title))
                    .setLayout(R.layout.custom_view_bottom_sheet)
                    .setSubtitle(getString(R.string.biometric_subtitle))
                    .setDescription(getString(R.string.biometric_description))
                    .setNegativeButtonText(getString(R.string.biometric_negative_button_text))
                    .build()
                    .authenticate(this)
        }
    }

    override fun onBiometricAuthenticationInternalError(error: String?) {
        Toast.makeText(applicationContext, error, Toast.LENGTH_LONG).show()
    }

    override fun onAuthenticationFailed() {
        //        Toast.makeText(getApplicationContext(), getString(R.string.biometric_failure), Toast.LENGTH_LONG).show();
    }

    override fun onAuthenticationCancelled() {
        Toast.makeText(applicationContext, getString(R.string.biometric_cancelled), Toast.LENGTH_LONG).show()
    }

    override fun onAuthenticationSuccessful() {
        Toast.makeText(applicationContext, getString(R.string.biometric_success), Toast.LENGTH_LONG).show()
    }

    override fun onAuthenticationHelp(helpCode: Int, helpString: CharSequence?) {
        //        Toast.makeText(getApplicationContext(), helpString, Toast.LENGTH_LONG).show();
    }

    override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
        //        Toast.makeText(getApplicationContext(), errString, Toast.LENGTH_LONG).show();
    }
}
