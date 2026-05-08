package io.issc.mod_compose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.issc.mod_compose.ui.theme.Android_dev_tutorial_ktTheme

private enum class Screen {
    Gallery,
    Camera,
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("main", "runs here")
        enableEdgeToEdge()
        setContent {
            Android_dev_tutorial_ktTheme {
                var currentScreen by rememberSaveable { mutableStateOf(Screen.Gallery) }
                var pendingCapturedPhotoUri by remember { mutableStateOf<String?>(null) }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    when (currentScreen) {
                        Screen.Gallery -> WaterfallGalleryDemoScreen(
                            modifier = Modifier.padding(innerPadding),
                            pendingCapturedPhotoUri = pendingCapturedPhotoUri,
                            onCapturedPhotoShown = { pendingCapturedPhotoUri = null },
                            onOpenCamera = { currentScreen = Screen.Camera },
                        )

                        Screen.Camera -> CameraCaptureScreen(
                            modifier = Modifier.padding(innerPadding),
                            onBack = { currentScreen = Screen.Gallery },
                            onPhotoCaptured = { uri ->
                                pendingCapturedPhotoUri = uri
                                currentScreen = Screen.Gallery
                            },
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Android_dev_tutorial_ktTheme {
        WaterfallGalleryDemoScreen()
    }
}