package io.issc.android_dev_tutorial_kt

import android.animation.ObjectAnimator
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
import io.issc.android_dev_tutorial_kt.model.AppDatabase
import io.issc.android_dev_tutorial_kt.model.UserInfo
import java.io.File
import java.util.concurrent.Future
import kotlin.random.Random


/**
 * 自定义View示例
 */
class IoActivity : AppCompatActivity() {
    lateinit var binding: ActivityIoBinding
    lateinit var txtTitle:TextView
    lateinit var txtInfo:TextView
    lateinit var btnAdd:FloatingActionButton
    lateinit var listView:RecyclerView

    val coroutineTester = CoroutineTester()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        txtTitle = binding.title
        txtInfo = binding.info
        btnAdd = binding.btnAction
        listView = binding.listView


        //preferences demo
        var preferences = getSharedPreferences("io.issc.android_dev_tutorial_kt", MODE_PRIVATE)

        preferences.edit().putString("title", "Io Demo App").apply()
        preferences.edit().putString("info", "Sharedpreference").apply()

        //file r/w demo
        val extDir = applicationContext.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)?.absolutePath
        val file = File(extDir, "test.json")
        if (!file.exists()) {
            file.createNewFile()
            file.writeBytes("test".toByteArray())
        }
        else {
            val str = file.readBytes().toString()
            Log.d("toacti", str)
        }

        //room demo
        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "user_info").allowMainThreadQueries().fallbackToDestructiveMigration().build()
        val userInfoDao = db.userInfoDao()

        Thread({
            userInfoDao.save(UserInfo(null, "admin", "admin", "admin"))
            val uinfo = userInfoDao.findByName("admin")
            Log.d("IoActivity", "user info: " + uinfo.name)
        }).start()
    }
}