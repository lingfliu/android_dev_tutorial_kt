package io.issc.android_dev_tutorial_kt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.issc.android_dev_tutorial_kt.databinding.ActivityCoordinateBinding

class CoordinateActivity : AppCompatActivity() {
    lateinit var binding: ActivityCoordinateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoordinateBinding.inflate(layoutInflater)
        setContentView(binding.root)
   }

}