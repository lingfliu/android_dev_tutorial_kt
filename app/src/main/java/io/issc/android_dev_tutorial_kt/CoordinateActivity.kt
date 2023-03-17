package io.issc.android_dev_tutorial_kt

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AlertDialogLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import io.issc.android_dev_tutorial_kt.ListAdapter.OnOpListener
import io.issc.android_dev_tutorial_kt.databinding.ActivityCoordinateBinding

/**
 * RecyclerView，AlertDialog，Toast，CoordinatorLayout用例演示
 */
class CoordinateActivity : AppCompatActivity() {
    lateinit var binding: ActivityCoordinateBinding

    val contactList = ArrayList<Contact>()

    lateinit var listAdapter: ListAdapter

    lateinit var btnAdd:FloatingActionButton

    lateinit var img:ImageView


    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoordinateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        img = binding.img

        img.setOnClickListener{
            var dialogBuilder = AlertDialog.Builder(applicationContext)

            dialogBuilder.setTitle("跳转确认")
                .setMessage("确认跳转么？")

            dialogBuilder.create().show()

        }
        val bar = binding.bar

        val listView = binding.listView

        listAdapter = ListAdapter(contactList)

        listView.adapter = listAdapter
        listView.layoutManager = LinearLayoutManager(this, VERTICAL, false)

        listAdapter.notifyDataSetChanged()

        dataMock()
        listAdapter.opListener = object:OnOpListener{
            override fun onRemove(data: Contact) {
                Log.d("main", "removing " + data.toString())
                var cnt = 0
                for (contact in contactList) {
                    if (contact.equals(data)) {
                        contactList.remove(data)
                        listAdapter.notifyItemRemoved(cnt)
                        break
                    }
                    cnt ++
                }
            }

        }

        btnAdd = binding.btnAction
        btnAdd.setOnClickListener {
            Snackbar.make(findViewById(R.id.wrapper), R.string.info_shown, Snackbar.LENGTH_LONG).show()
//            val v = LayoutInflater.from(this).inflate(R.layout.dialog_add_contact, null)
//            val dialog = AlertDialog.Builder(this)
//                .setView(v)
//                .create()
//
//            v.findViewById<Button>(R.id.btn_confirm).setOnClickListener {
//                val name = v.findViewById<EditText>(R.id.input_name).text.toString()
//                val num = v.findViewById<EditText>(R.id.input_num).text.toString()
//                if (name.isEmpty() || num.isEmpty()) {
//                    Toast.makeText(applicationContext, "姓名与号码均不能为空", Toast.LENGTH_SHORT).show()
//                } else {
//                    dialog.dismiss()
//                    contactList.add(Contact(name, num))
//                    listAdapter.notifyItemInserted(contactList.size - 1)
//                }
//            }
//            v.findViewById<Button>(R.id.btn_cancel).setOnClickListener {
//                dialog.dismiss()
//            }
//
//            dialog.show()
//            dialog.window?.setLayout(
//                windowManager.currentWindowMetrics.bounds.width() - 64,
//                windowManager.currentWindowMetrics.bounds.height() - 500
//            )
        }

    }

    fun dataMock() {
        for (i in 1..30) {
            contactList.add(Contact("Alice"+i.toString(), i.toString()))
            listAdapter.notifyItemInserted(contactList.size-1)
        }
    }

}