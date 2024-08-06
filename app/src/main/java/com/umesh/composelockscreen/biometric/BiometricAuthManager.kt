package com.umesh.composelockscreen.biometric

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

class BiometricAuthManager(
    private val context: Context,
    activity: FragmentActivity,
    authenticationCallback: BiometricPrompt.AuthenticationCallback
) {

    private val biometricPrompt: BiometricPrompt
    private val promptInfo: BiometricPrompt.PromptInfo

    init {
        val executor = ContextCompat.getMainExecutor(context)
        biometricPrompt = BiometricPrompt(activity, executor, authenticationCallback)

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric login for MyApp")
            .setSubtitle("Log in using your biometric credential")
            .setNegativeButtonText("Use account password")
            .setConfirmationRequired(false)
            .build()
    }

    fun authenticate() {
        when {
            isBiometricAvailable(BIOMETRIC_STRONG) -> {
                biometricPrompt.authenticate(promptInfo)
            }

            isBiometricAvailable(BiometricManager.Authenticators.BIOMETRIC_WEAK) -> {
                biometricPrompt.authenticate(promptInfo)
            }

            else -> {
                // Handle cases where no biometrics are available
            }
        }
    }

    private fun isBiometricAvailable(authenticators: Int): Boolean {
        val biometricManager = BiometricManager.from(context)
        return biometricManager.canAuthenticate(authenticators) == BiometricManager.BIOMETRIC_SUCCESS
    }
}