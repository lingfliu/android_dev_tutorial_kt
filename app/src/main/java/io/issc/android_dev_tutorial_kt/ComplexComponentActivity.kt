package io.issc.android_dev_tutorial_kt

import android.content.Context
import android.content.DialogInterface
import android.content.DialogInterface.OnClickListener
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AlertDialog
import io.issc.android_dev_tutorial_kt.databinding.ActivityComplexComponentBinding


class ComplexComponentActivity : AppCompatActivity() {
    lateinit var binding: ActivityComplexComponentBinding

    lateinit var spinner:Spinner
    lateinit var webView: WebView
    var selectList = ArrayList<String>()
    var dialog: AlertDialog? = null

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

        var dialogBuilder = AlertDialog.Builder(this)
//        dialogBuilder.setTitle("跳转确认")
//            .setMessage("确认跳转么？")
//            .setNegativeButton("取消", DialogInterface.OnClickListener{dialogInterface, i ->
//                dialogInterface.dismiss()
//            })
//            .setPositiveButton("确认", DialogInterface.OnClickListener{dialogInterface, i ->
//               dialogInterface.dismiss()
//            })
//            .create().show()

//        dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener{dialogInterface, i->
//            dialogInterface.dismiss()
//        }).create().show()

        var layout = layoutInflater.inflate(R.layout.dialog_add_contact, null)
        var btn = layout.findViewById<Button>(R.id.btn_confirm)
        btn.setOnClickListener{
            var name = layout.findViewById<EditText>(R.id.input_name).text.toString()
            var num = layout.findViewById<EditText>(R.id.input_num).text.toString()
        }
        var btnCancel =layout.findViewById<Button>(R.id.btn_cancel)
        btnCancel.setOnClickListener{
           //TODO: dismiss dialog
            dialog?.dismiss()
        }
        dialog = dialogBuilder.setView(layout).create()
        dialog?.show()


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