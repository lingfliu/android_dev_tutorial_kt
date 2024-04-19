package io.issc.android_dev_tutorial_kt

import android.animation.ObjectAnimator
import android.content.pm.PackageManager.PERMISSION_DENIED
import android.graphics.Bitmap
import android.media.MediaPlayer
import android.media.MediaPlayer.OnPreparedListener
import android.media.session.MediaSession.Token
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.FileUtils
import android.util.Log
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import io.issc.android_dev_tutorial_kt.databinding.ActivityComplexComponentBinding
import io.issc.android_dev_tutorial_kt.databinding.ActivityCustomViewBinding
import io.issc.android_dev_tutorial_kt.databinding.ActivityIoBinding
import io.issc.android_dev_tutorial_kt.databinding.ActivityMainBinding
import io.issc.android_dev_tutorial_kt.databinding.ActivityPagerBinding
import io.issc.android_dev_tutorial_kt.databinding.ActivitySimpleComponentBinding
import io.issc.android_dev_tutorial_kt.model.*
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.utils.EmptyContent.headers
import io.ktor.http.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File
import java.util.concurrent.Future
import kotlin.random.Random


/**
 * 自定义View示例
 */
class IoActivity : AppCompatActivity() {
    lateinit var binding: ActivityIoBinding
    lateinit var btn:Button

    val coroutineTester = CoroutineTester()

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.i("ioacti", "onRequestPermissionsResult"+ " " + grantResults[0].toString())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btn = binding.btn

        btn.setOnClickListener{

            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PERMISSION_DENIED) {
                requestPermissions(arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)
            }
            else {
                Log.i("ioacti", "permission granted")
            }

////
//            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PERMISSION_DENIED) {
//                requestPermissions(arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)
//            }

            //file r/w demo
            val extDir = applicationContext.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)?.absolutePath
            val file = File(extDir, "test3.json")
            if (!file.exists()) {
                file.createNewFile()
            }
            file.writeBytes("test 1234".toByteArray())
            file.writeText("test 1234", Charsets.UTF_8)
            val str = file.readLines()[0]
            Log.d("ioacti", ""+str)
        }


        //preferences demo
        val preferences = getSharedPreferences("io.issc.android_dev_tutorial_kt", MODE_PRIVATE)

        val isFirstOpen = preferences.getBoolean("isFirstOpen", true)
        if (isFirstOpen) {
            preferences.edit().putBoolean("isFirstOpen", false).apply()
        }

        //room demo
        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "user_info")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
        val userInfoDao = db.userInfoDao()

        MainScope().launch {
            userInfoDao.save(UserInfo(null, "admin", "admin", "admin"))
            userInfoDao.update(UserInfo(1, "admin", "admin", "admin"))

            val uinfo = userInfoDao.findByName("admin")
            userInfoDao.deleteById(uinfo.id!!)

            Log.d("IoActivity", "user info: " + uinfo.name)
        }.start()
//        Thread({
//
//        }).start()

    }
}