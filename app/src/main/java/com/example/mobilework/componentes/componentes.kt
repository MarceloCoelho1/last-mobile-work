package com.example.mobilework.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
@Composable
fun GameButton(
    image: Painter,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = CircleShape,
    backgroundColor: Color = Color.White,
    contentColor: Color = Color.Black
) {
    Button(
        onClick = onClick, // Ajuste aqui
        modifier = modifier
            .size(100.dp)
            .background(backgroundColor, shape),
        colors = ButtonDefaults.buttonColors(
            contentColor = contentColor
        ),
        shape = shape,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(painter = image, contentDescription = text, modifier = Modifier.size(40.dp))
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = text, fontSize = 14.sp)
        }
    }
}
