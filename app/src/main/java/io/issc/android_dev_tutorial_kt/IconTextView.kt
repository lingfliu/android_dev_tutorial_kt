package io.issc.android_dev_tutorial_kt

import android.content.Context
import android.graphics.Bitmap
import android.media.Image
import android.media.ImageReader
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import org.w3c.dom.Text

class IconTextView: ConstraintLayout {


    constructor(context:Context):super(context) {
        LayoutInflater.from(context).inflate(R.layout.widget_icontext, this)
    }
    constructor(context:Context, attr:AttributeSet?):super(context, attr) {
        LayoutInflater.from(context).inflate(R.layout.widget_icontext, this)
    }
    constructor(context: Context, attr: AttributeSet?, @AttrRes defStylAttr: Int):super(context, attr, defStylAttr) {
        LayoutInflater.from(context).inflate(R.layout.widget_icontext, this)
    }

    lateinit var title: String
    var icon: Bitmap? = null

    fun config(title:String, icon:Bitmap) {
        this.title = title
        this.icon = icon

        findViewById<TextView>(R.id.title).text = title
        findViewById<ImageView>(R.id.icon).setImageBitmap(icon)
    }

    fun config(title:String, iconId:Int) {
        findViewById<TextView>(R.id.title).text = title
        findViewById<ImageView>(R.id.icon).setImageDrawable(ResourcesCompat.getDrawable(resources, iconId, null))
    }
}