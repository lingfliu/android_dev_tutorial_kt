package io.issc.android_dev_tutorial_kt

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
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import io.issc.android_dev_tutorial_kt.databinding.ActivityComplexComponentBinding
import io.issc.android_dev_tutorial_kt.databinding.ActivityMainBinding
import io.issc.android_dev_tutorial_kt.databinding.ActivityPagerBinding
import io.issc.android_dev_tutorial_kt.databinding.ActivitySimpleComponentBinding
import java.util.concurrent.Future
import kotlin.random.Random


class ComplexComponentActivity : AppCompatActivity() {
    lateinit var binding: ActivityComplexComponentBinding

    lateinit var spinner:Spinner
    lateinit var webView: WebView
    var selectList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComplexComponentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        spinner = binding.select

        selectList.add("1")
        selectList.add("2")
        selectList.add("3")
        spinner.adapter = ArrayAdapter(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, selectList)

        spinner.onItemSelectedListener = object:OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, v: View?, position: Int, id: Long) {
                Log.d("main", "selected " + selectList.get(position))
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        webView = binding.web

        //添加client保持在webview内部渲染页面
        webView.webViewClient = object:WebViewClient(){
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
            }

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return super.shouldOverrideUrlLoading(view, request)
            }
        }

        webView.loadUrl("http://www.163.com")

    }
}