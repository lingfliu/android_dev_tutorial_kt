package io.issc.android_dev_tutorial_kt

import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.CompoundButton.INVISIBLE
import android.widget.CompoundButton.OnCheckedChangeListener
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import io.issc.android_dev_tutorial_kt.databinding.ActivityDemoBinding

class DemoActivity: AppCompatActivity() {
    lateinit var btn:Button
    lateinit var binding: ActivityDemoBinding
    var cnt = 0
    lateinit var txt:TextView
    lateinit var img:ImageButton
    lateinit var switch:Switch
    lateinit var optList:RadioGroup
    lateinit var seekbar:SeekBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_demo)
//        btn = findViewById(R.id.btn_select)

        binding = ActivityDemoBinding.inflate(layoutInflater)
//        cnt = binding.cnt
        btn = binding.btnSelect
        txt = binding.txtCnt
        switch = binding.switchBtn
        switch.setOnCheckedChangeListener{p0, checked->
                if (checked) {
                    Log.i("demoacti", "switch on")
                    img.visibility = View.VISIBLE
                }
                else
                {
                    Log.i("demoacti", "switch off")
                    img.visibility = View.INVISIBLE
                }
        }

        optList = binding.optList

        optList.setOnCheckedChangeListener { p0, btnId ->
            if (btnId == R.id.opt_1) {

            } else if (btnId == R.id.opt_2) {

            } else if (btnId == R.id.opt_3) {

            } else {
                //DO nothing
            }
        }

        seekbar = binding.seekbar

        seekbar.setOnSeekBarChangeListener(object:OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                TODO("Not yet implemented")
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                TODO("Not yet implemented")
            }

        })

        img = binding.img

        var str = "hello"
        var bytes = str.toByteArray()
        img.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.size))
//        img.setImageResource(R.drawable.ic_launcher_background)

        btn.setOnClickListener{
            cnt ++
        }
    }
}