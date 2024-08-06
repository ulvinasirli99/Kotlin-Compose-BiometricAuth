package com.umesh.composelockscreen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun NumberOTPBox(modifier: Modifier = Modifier, enteredNumber: String, onTextChange: (String) -> Unit) {
    CompositionLocalProvider(
        LocalTextInputService provides null
    ) {
        BasicTextField(
            value = enteredNumber,
            onValueChange = onTextChange,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            cursorBrush = SolidColor(Color.Transparent),
            visualTransformation = VisualTransformation.None,
            modifier = modifier.fillMaxWidth(),
            decorationBox = {
                Row(
                    modifier = modifier.wrapContentWidth(),
                    horizontalArrangement = Arrangement.Absolute.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    for (i in 0 until 6) {
                        CustomDot(isFilled = i < enteredNumber.length)
                    }
                }
            }
        )
    }
}