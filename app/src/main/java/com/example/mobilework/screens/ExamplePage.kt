package com.example.mobilework.screens

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mobilework.R

@Composable
fun ExamplePage() {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var displayName by remember { mutableStateOf("Example page") }
    var nameSubmitted by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var useCount by remember { mutableStateOf(mapOf("Pedra" to 0, "Papel" to 0, "Tesoura" to 0)) }

    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        imageUri = uri
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = displayName,
            textAlign = TextAlign.Center,
            fontSize = 36.sp,
            lineHeight = 40.sp
        )
        Spacer(modifier = Modifier.height(16.dp))

        if (!nameSubmitted) {
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Enter your name") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                displayName = name.text
                nameSubmitted = true
            }) {
                Text("Submit")
            }
        } else {
            Button(onClick = { launcher.launch("image/*") }) {
                Text("Select Image")
            }
            Spacer(modifier = Modifier.height(16.dp))
            imageUri?.let {
                val bitmap = getBitmapFromUri(context, it)
                bitmap?.let { btm ->
                    Image(bitmap = btm.asImageBitmap(), contentDescription = null, modifier = Modifier.size(200.dp))
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("Quantas vezes usou:")
            Text("- Pedra: ${useCount["Pedra"]}")
            Text("- Papel: ${useCount["Papel"]}")
            Text("- Tesoura: ${useCount["Tesoura"]}")
        }
    }
}

fun getBitmapFromUri(context: Context, uri: Uri): Bitmap? {
    return try {
        val inputStream = context.contentResolver.openInputStream(uri)
        BitmapFactory.decodeStream(inputStream)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}
