package io.issc.mod_ui

import android.app.SearchManager.OnCancelListener
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.RadioGroup.OnCheckedChangeListener
import android.widget.RatingBar
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import io.issc.mod_ui.databinding.ActivitySimpleComponentBinding
import java.util.concurrent.Future


class SimpleComponentActivity : AppCompatActivity() {
    lateinit var binding: ActivitySimpleComponentBinding
    lateinit var txt: TextView
    lateinit var btn: Button
    lateinit var seekBar:SeekBar
    lateinit var img:ImageView

    val taskResultList = ArrayList<Future<Any>>()

    var selectedBtnIdx = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySimpleComponentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        txt = binding.txt
        btn = binding.btn

        img = binding.img
        seekBar = binding.seekbar

        btn.setOnClickListener {
//            Toast.makeText(this, "测试信息", Toast.LENGTH_SHORT).show()
            Snackbar.make(binding.root, "snack", Snackbar.LENGTH_SHORT).show()
        }

        binding.btnReset.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("跳转确认")
                .setMessage("确认跳转么？")
                .setOnCancelListener ( object : DialogInterface.OnCancelListener {
                    override fun onCancel(p0: DialogInterface?) {
                    }
                })
                .setNegativeButton("取消", { dialogInterface, i ->
//                    dialogInterface.dismiss()
                })
                .setPositiveButton("确认", { dialogInterface, i ->
//                    dialogInterface.dismiss()
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
                var p = v?.progress

            }
        })

        binding.rate.setOnRatingBarChangeListener(object : RatingBar.OnRatingBarChangeListener{
            override fun onRatingChanged(p0: RatingBar?, rating: Float, fromUser: Boolean) {
                rating
            }
        })

        binding.calendar.setOnDateChangeListener(object : OnDateChangeListener{
            override fun onSelectedDayChange(var1: CalendarView, year: Int, month: Int, monthDay: Int) {
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