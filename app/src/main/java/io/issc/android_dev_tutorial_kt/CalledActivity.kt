package io.issc.android_dev_tutorial_kt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import io.issc.android_dev_tutorial_kt.databinding.ActivityCalledBinding
import io.issc.android_dev_tutorial_kt.databinding.ActivityMainBinding
import io.issc.android_dev_tutorial_kt.databinding.ActivityNaviBinding
import io.issc.android_dev_tutorial_kt.databinding.ActivityPagerBinding
import io.issc.android_dev_tutorial_kt.databinding.ActivitySimpleComponentBinding
import java.util.concurrent.Future
import kotlin.random.Random


/**
 * 被调用的Activity
 */
class CalledActivity : AppCompatActivity() {
    lateinit var binding: ActivityCalledBinding

    lateinit var btnBack:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalledBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.let {
            val cnt = it.getInt("cnt")
            Toast.makeText(this, "cnt = $cnt", Toast.LENGTH_SHORT).show()
        }

//        var cnt = intent.getIntExtra("cnt", 0)
//        Toast.makeText(this, "cnt = $cnt", Toast.LENGTH_SHORT).show()

        btnBack = binding.btnBack

        //get request code
//        var requestCode = intent.getIntExtra("requestCode", 0)

        btnBack.setOnClickListener{
            var intent = Intent()
            intent.putExtra("cnt", 10)

            setResult(1, intent)


            finish()
        }

    }

}