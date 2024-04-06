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
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.BounceInterpolator
import android.view.animation.RotateAnimation
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
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

    var progress = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        iconTextView = binding.iconText

        //设置属性
//        iconTextView.config("TITLE", androidx.core.R.drawable.ic_call_answer_video)


        var ani1 = ScaleAnimation(1.0f, 2.0f, 1.0f, 2.0f)
//        ani1.duration = 1000
//        ani1.repeatCount = 10
//        ani1.repeatMode = Animation.REVERSE
//        ani1.startOffset = 100
//        val anix = AlphaAnimation(0.1f,1.1f)
//        RotateAnimation(0.0f, 180.0f, 0.5f, 0.5f)
//        TranslateAnimation(0.0f,1.0f,0.0f,1.0f)
//        iconTextView.startAnimation(ani1)

//        var ani2 = RotateAnimation(0.0f, 180.0f, 0.5f, 0.5f)
//        ani.duration = 1000
//        ani.repeatCount = 1

//        var aniSet = AnimationSet(true)
//        aniSet.addAnimation(ani1)
//        aniSet.addAnimation(ani2)

//        aniSet.duration = 1000
//        aniSet.repeatCount = 2

//        aniSet.interpolator = BounceInterpolator()

//        iconTextView.setOnClickListener{
//            iconTextView.startAnimation(aniSet)
//        }

//        RotateAnimation()
//        TranslateAnimation()
//        AlphaAnimation()

//        iconTextView.setOnClickListener{
//            val ani = ObjectAnimator.ofFloat(iconTextView, "scaleX", iconTextView.scaleX, iconTextView.scaleX*1.2f)
//            ani.duration = 400
//            ani.start()
//        }

        //arcview animation demo by ObjectAnimator
        arcView = binding.arc
//        arcView.setOnClickListener{
//            anime = ObjectAnimator.ofFloat(arcView, "endAngle", progress, progress+20.0f)
//            anime.duration = 600
//            anime.repeatCount = 0
//            anime.repeatMode = ObjectAnimator.RESTART
//            anime.start()
//            progress += 20.0f
//        }

//        iconTextView.setOnClickListener{
//            coroutineTester.submit{
//                for (i in 0..12) {
//                    Thread.sleep(50)
//                    iconTextView.scaleX = 1+i*0.1f
//                    iconTextView.scaleY = 1+i*0.1f
//                }
//            }
//        }


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