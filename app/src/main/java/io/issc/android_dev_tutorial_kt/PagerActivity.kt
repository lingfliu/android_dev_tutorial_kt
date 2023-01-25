package io.issc.android_dev_tutorial_kt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import io.issc.android_dev_tutorial_kt.databinding.ActivityMainBinding
import io.issc.android_dev_tutorial_kt.databinding.ActivityPagerBinding
import io.issc.android_dev_tutorial_kt.databinding.ActivitySimpleComponentBinding
import java.util.concurrent.Future
import kotlin.random.Random


class PagerActivity : FragmentActivity() {
    lateinit var binding: ActivityPagerBinding
    var clickCount = ClickCount(1)
    lateinit var pager : ViewPager2
    lateinit var tab: TabLayout
    val tabNames = arrayListOf("1", "2", "3")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.clickCount = clickCount

        tab = binding.tabs


        pager = binding.pager

        pager.adapter = object : FragmentStateAdapter(this) {
           override fun getItemCount(): Int {
               return 3
            }
            override fun createFragment(position: Int): Fragment {
                if (position == 0) {
                    return FragmentP1()
                }
                else if (position == 1) {
                    return FragmentP2()
                }
                else if (position == 2) {
                    return FragmentP3()
                }
                else {
                    return FragmentP1()
                }
            }
        }

        for (i in 1..3) {
            tab.addTab(tab.newTab().setText(i.toString()))
        }

        TabLayoutMediator(tab, pager) { tab, position -> tab.text = tabNames.get(position)}
    }

}