package io.issc.layout

import android.app.ActivityManager
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.issc.layout.databinding.ActivityLayoutConstraintBinding
import io.issc.layout.ui.theme.Android_dev_tutorial_ktTheme

class MainActivity : ComponentActivity() {
    lateinit var binding: ActivityLayoutConstraintBinding
    var clickCount = ClickCount(0)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setContentView(R.layout.activity_layout_constraint)
//        var btn = this.findViewById<Button>(R.id.btn)
//        var btn_reset = this.findViewById<Button>(R.id.btn_reset)

        binding = ActivityLayoutConstraintBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cnt = clickCount

        binding.btn.setOnClickListener {
            clickCount.add()
        }



//        binding.btn.setOnClickListener {
//            clickCount.add()
//        }

    }
}