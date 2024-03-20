package io.issc.mod_ui

import android.app.Activity
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.RadioGroup.OnCheckedChangeListener
import android.widget.RatingBar
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AlertDialog
import com.google.android.material.chip.ChipGroup
import io.issc.mod_ui.databinding.ActivitySimpleComponentBinding
import java.util.concurrent.Future


class SimpleComponentActivity : AppCompatActivity() {
    lateinit var binding: ActivitySimpleComponentBinding
    lateinit var txt: TextView
    lateinit var btn: Button
    lateinit var seekBar:SeekBar
    lateinit var img:ImageView

    val taskResultList = ArrayList<Future<Any>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySimpleComponentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        txt = binding.txt
        btn = binding.btn

        img = binding.img
        seekBar = binding.seekbar

        btn.setOnClickListener {
            Toast.makeText(this, "测试信息", Toast.LENGTH_SHORT).show()
        }
        
        binding.btnReset.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("跳转确认")
                .setMessage("确认跳转么？")
                .setNegativeButton("取消", { dialogInterface, i ->
                    dialogInterface.dismiss()
                })
                .setPositiveButton("确认", { dialogInterface, i ->
                    dialogInterface.dismiss()
                })
                .create().show()
        }

        seekBar.setOnSeekBarChangeListener(object:OnSeekBarChangeListener{
            override fun onProgressChanged(v: SeekBar?, p: Int, fromUser: Boolean) {
//                Log.d("simple", progress.toString())
            }

            override fun onStartTrackingTouch(v: SeekBar?) {
//                TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(v: SeekBar?) {
//                TODO("Not yet implemented")
            }
        })

//        binding.chipGrp.setOnCheckedChangeListener(object : com.google.android.material.chip.ChipGroup.OnCheckedChangeListener{
//            /**
//             * Called when the checked chip has changed. When the selection is cleared, checkedId is [ ][View.NO_ID].
//             *
//             * @param group the group in which the checked chip has changed
//             * @param checkedId the unique identifier of the newly checked chip
//             */
//            override fun onCheckedChanged(group: ChipGroup, checkedId: Int) {
//                TODO("Not yet implemented")
//            }
//        })

        binding.radios.setOnCheckedChangeListener(object :OnCheckedChangeListener{
            override fun onCheckedChanged(p0: RadioGroup?, id: Int) {
//                TODO("Not yet implemented")
                if (id == R.id.radio_1) {
                    Log.d("simple", "selected option 1")
                }
                else if (id == R.id.radio_2) {
                    Log.d("simple", "selected option 2")
                }
            }
        })

//        binding.rate.setOnRatingBarChangeListener(object: RatingBar.OnRatingBarChangeListener)
    }
}