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
import io.issc.android_dev_tutorial_kt.databinding.ActivityMainBinding
import io.issc.android_dev_tutorial_kt.databinding.ActivityNaviBinding
import io.issc.android_dev_tutorial_kt.databinding.ActivityPagerBinding
import io.issc.android_dev_tutorial_kt.databinding.ActivitySimpleComponentBinding
import java.util.concurrent.Future
import kotlin.random.Random


class NaviActivity : FragmentActivity() {
    lateinit var binding: ActivityNaviBinding
    var cnt = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNaviBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        binding.btnFrg1.setOnClickListener{
//            val fragment = binding.navHostFragment.getFragment<BaseFragment>()
//            val controller = fragment.findNavController()
//            if (fragment.signature == "FrgNav1"){
//            }
//            else if (fragment.signature == "FrgNav2") {
//                controller.navigate(R.id.action_frgNav2_to_frgNav1)
//            }
//            else if (fragment.signature == "FrgNav3") {
//                controller.navigate(R.id.action_frgNav3_to_frgNav1)
//            }
//        }
//
//        binding.btnFrg2.setOnClickListener{
//            val fragment = binding.navHostFragment.getFragment<Fragment>() as BaseFragment
//            val controller = fragment.findNavController()
//            if (fragment.signature == "FrgNav2") {
//            }
//            else if (fragment.signature == "FrgNav1") {
//                controller.navigate(R.id.action_frgNav1_to_frgNav2)
//            }
//            else if (fragment.signature == "FrgNav3") {
//                controller.navigate(R.id.action_frgNav3_to_frgNav2)
//            }
//        }
//
//        binding.btnFrg3.setOnClickListener{
//            val fragment = binding.navHostFragment.getFragment<BaseFragment>()
//            val controller = fragment.findNavController()
//            if (fragment.signature == "FrgNav3") {
//            }
//            else if (fragment.signature == "FrgNav2") {
//                controller.navigate(R.id.action_frgNav2_to_frgNav3)
//            }
//            else if (fragment.signature == "FrgNav1") {
//                controller.navigate(R.id.action_frgNav1_to_frgNav3)
//            }
//        }
//
        val caller = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == 1) {
                val data: Int = result.data?.getIntExtra("cnt", 0) ?: 0
                Toast.makeText(this, "cnt = $data", Toast.LENGTH_SHORT).show()
            }
        }
//        //新的页面启动并返回结果使用示例
        binding.btnFrg1.setOnClickListener {
            var intent = Intent(this, CalledActivity::class.java)
            intent.putExtra("cnt", cnt)
            caller.launch(intent)
        }

    }

}