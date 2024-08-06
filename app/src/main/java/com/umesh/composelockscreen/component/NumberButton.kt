package com.umesh.composelockscreen.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.umesh.composelockscreen.ui.theme.Grey

@Composable
fun NumberButton(modifier: Modifier = Modifier, number: String, onNumberClick: (String) -> Unit) {
    Box(
        modifier = modifier
            .padding(10.dp)
            .size(60.dp)
            .clip(CircleShape)
            .background(Grey)
            .clickable {
                onNumberClick(number)
            },
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = number,
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp
        )
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
@Composable
fun PreviewButton() {
    NumberButton(number = "3") {}
}