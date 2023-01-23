package io.issc.android_dev_tutorial_kt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioGroup
import android.widget.RadioGroup.OnCheckedChangeListener
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import com.google.android.material.chip.ChipGroup
import io.issc.android_dev_tutorial_kt.databinding.ActivityMainBinding
import io.issc.android_dev_tutorial_kt.databinding.ActivitySimpleComponentBinding
import java.util.concurrent.Future
import kotlin.random.Random


class SimpleComponentActivity : AppCompatActivity() {
    lateinit var binding: ActivitySimpleComponentBinding
    lateinit var txt: TextView
    lateinit var btn: Button
    var clickCount = ClickCount(1)

    val taskResultList = ArrayList<Future<Any>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySimpleComponentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        txt = binding.txt
        btn = binding.btn
        binding.clickCount = clickCount

        btn.setOnClickListener {
            clickCount.cnt = (clickCount.cnt.toInt()+1).toString()
        }

        binding.seekbar.setOnSeekBarChangeListener(object:OnSeekBarChangeListener{
            override fun onProgressChanged(v: SeekBar?, progress: Int, fromUser: Boolean) {
                Log.d("simple", progress.toString())
            }

            override fun onStartTrackingTouch(v: SeekBar?) {
//                TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(v: SeekBar?) {
//                TODO("Not yet implemented")
            }
        })

        binding.chipGrp.setOnCheckedChangeListener(object : com.google.android.material.chip.ChipGroup.OnCheckedChangeListener{
            /**
             * Called when the checked chip has changed. When the selection is cleared, checkedId is [ ][View.NO_ID].
             *
             * @param group the group in which the checked chip has changed
             * @param checkedId the unique identifier of the newly checked chip
             */
            override fun onCheckedChanged(group: ChipGroup?, checkedId: Int) {
//                TODO("Not yet implemented")
            }
        })

        binding.radios.setOnCheckedChangeListener(object :OnCheckedChangeListener{
            override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
//                TODO("Not yet implemented")
                Log.d("simple", "selected ${d}".format(p1))
            }
        })
    }
}