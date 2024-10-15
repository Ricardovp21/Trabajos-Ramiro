package com.example.bandera

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxSize
import com.example.bandera.ui.theme.BanderaTheme
import android.view.LayoutInflater

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BanderaTheme {
                MyActivityMainView(Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun MyActivityMainView(modifier: Modifier = Modifier) {
    AndroidView(
        factory = { context ->
            val inflater = LayoutInflater.from(context)

            inflater.inflate(R.layout.banderamexico, null)
        },
        modifier = modifier
    )
}
