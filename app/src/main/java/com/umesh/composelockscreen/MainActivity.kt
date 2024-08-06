package com.umesh.composelockscreen

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.biometric.BiometricPrompt
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness2
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.outlined.Brightness4
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.FragmentActivity
import com.umesh.composelockscreen.biometric.BiometricAuthManager
import com.umesh.composelockscreen.component.NumberKeypad
import com.umesh.composelockscreen.component.NumberOTPBox
import com.umesh.composelockscreen.ui.theme.ComposeLockScreenTheme
import com.umesh.composelockscreen.util.Utils.showToast

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            enableEdgeToEdge()
            var darkTheme by remember { mutableStateOf(false) }
            val iconId = if (darkTheme) Icons.Filled.Brightness2 else Icons.Outlined.Brightness4
            val rotationAngle by animateFloatAsState(
                targetValue = if (darkTheme) 180f else 0f,
                label = "angle"
            )

            var inputValue by remember {mutableStateOf("") }
            ComposeLockScreenTheme(darkTheme = darkTheme) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .systemBarsPadding(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Spacer(modifier = Modifier.height(30.dp))
                        Row(
                            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = stringResource(R.string.welcome_back),
                                color = MaterialTheme.colorScheme.onSurface,
                                fontSize = 35.sp,
                                fontWeight = FontWeight.Bold
                            )

                            IconButton(
                                onClick = { darkTheme = !darkTheme },
                                modifier = Modifier.size(35.dp)
                            ) {
                                Icon(
                                    modifier = Modifier.rotate(rotationAngle),
                                    imageVector = iconId,
                                    contentDescription = "theme"
                                )
                            }
                        }

                        Text(
                            text = stringResource(R.string.micky),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 35.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(R.string.sub_title),
                            color = Color.LightGray,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Spacer(modifier = Modifier.height(30.dp))

                        NumberOTPBox(enteredNumber = inputValue) { enteredValue ->
                            if (enteredValue.length <= 6) {
                                inputValue = enteredValue
                            }
                        }

                        Spacer(modifier = Modifier.height(30.dp))

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    showToast(getString(R.string.feature_under_development))
                                },
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = stringResource(R.string.forgot_transaction_pin),
                                color = Color.Blue,
                                style = MaterialTheme.typography.titleSmall
                            )
                        }

                        Spacer(modifier = Modifier.height(25.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    val biometricAuthManager = BiometricAuthManager(
                                        context = this@MainActivity,
                                        activity = this@MainActivity,
                                        authenticationCallback = object : BiometricPrompt.AuthenticationCallback() {
                                            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                                                super.onAuthenticationSucceeded(result)
                                                // Handle successful authentication
                                            }

                                            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                                                super.onAuthenticationError(errorCode, errString)
                                                // Handle error
                                            }

                                            override fun onAuthenticationFailed() {
                                                super.onAuthenticationFailed()
                                                // Handle failed authentication
                                            }
                                        }
                                    )

                                    // Trigger biometric authentication
                                    biometricAuthManager.authenticate()
                                },
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Face,
                                modifier = Modifier
                                    .width(90.dp)
                                    .height(90.dp),
                                contentDescription = "Face Id"
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = stringResource(R.string.continue_with_face_id),
                                color = MaterialTheme.colorScheme.onSurface,
                                style = MaterialTheme.typography.titleSmall
                            )
                        }

                        Spacer(modifier = Modifier.height(25.dp))

                        val annotatedString = AnnotatedString.Builder().apply {
                            withStyle(
                                style = SpanStyle(
                                    color = MaterialTheme.colorScheme.onSurface,
                                    fontStyle = FontStyle.Normal,
                                    fontSize = 15.sp
                                )
                            ) {
                                append(stringResource(R.string.not_micky))
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = Color.Blue,
                                    fontStyle = FontStyle.Normal,
                                    fontSize = 15.sp
                                )
                            ) {
                                append(stringResource(R.string.log_out))
                            }
                        }.toAnnotatedString()

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    showToast(getString(R.string.feature_under_development))
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = annotatedString,
                                color = Color.Black,
                                style = MaterialTheme.typography.titleSmall
                            )
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        NumberKeypad(
                            onNumberClick = { number ->
                                if (inputValue.length < 6) {
                                    inputValue += number
                                }
                            },
                            onDeleteClick = {
                                if (inputValue.isNotEmpty()) {
                                    inputValue = inputValue.dropLast(1)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

