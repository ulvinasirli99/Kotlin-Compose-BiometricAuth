package com.umesh.composelockscreen.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomDot(modifier: Modifier = Modifier, isFilled: Boolean) {
    Box(
        modifier = modifier
            .padding(10.dp)
            .size(20.dp)
            .background(if (isFilled) MaterialTheme.colorScheme.primary else Color.LightGray, CircleShape)
    )
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewCustomDot() {
    CustomDot(isFilled = false)
}