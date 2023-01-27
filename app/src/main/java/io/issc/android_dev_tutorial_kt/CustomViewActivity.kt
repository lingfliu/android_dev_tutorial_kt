package io.issc.android_dev_tutorial_kt

import android.animation.ObjectAnimator
import android.graphics.Bitmap
import android.media.MediaPlayer
import android.media.MediaPlayer.OnPreparedListener
import android.media.session.MediaSession.Token
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import io.issc.android_dev_tutorial_kt.databinding.ActivityComplexComponentBinding
import io.issc.android_dev_tutorial_kt.databinding.ActivityCustomViewBinding
import io.issc.android_dev_tutorial_kt.databinding.ActivityMainBinding
import io.issc.android_dev_tutorial_kt.databinding.ActivityPagerBinding
import io.issc.android_dev_tutorial_kt.databinding.ActivitySimpleComponentBinding
import java.util.concurrent.Future
import kotlin.random.Random


/**
 * 自定义View示例
 */
class CustomViewActivity : AppCompatActivity() {
    lateinit var binding: ActivityCustomViewBinding

    lateinit var iconTextView: IconTextView
    lateinit var arcView: ArcView

    lateinit var anime:ObjectAnimator

    val coroutineTester = CoroutineTester()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        iconTextView = binding.iconText

        //设置属性
//        iconTextView.config("TITLE", androidx.core.R.drawable.ic_call_answer_video)

        //arcview animation demo by ObjectAnimator
        arcView = binding.arc
        anime = ObjectAnimator.ofFloat(arcView, "endAngle", 0.0f, 270.0f)
        anime.duration = 1200
        anime.repeatCount = 10
        anime.repeatMode = ObjectAnimator.RESTART
        anime.start()

        //arcview animation demo by coroutine
//        coroutineTester.submit{
//            for (i in 0..270) {
//                Thread.sleep(10)
//                arcView.endAngle = i.toFloat()
//                arcView.invalidate()
//            }
//        }
//
//        arcView.setOnClickListener{
//            anime.start()
//        }

    }
}