package io.issc.android_dev_tutorial_kt

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.core.content.res.ResourcesCompat

class ArcView(context: Context, attr: AttributeSet?) : View(context, attr) {

    var paint:Paint

    var startAngle:Float
    var endAngle:Float = 0.0f
        get() = field
        set(value) {
            field = value
            this.invalidate()
        }

    init {
        paint = Paint()
        paint.color = Color.parseColor("#643122")
        paint.strokeWidth = 3.0f

        val ta = context.obtainStyledAttributes(attr, R.styleable.ArcView)
        startAngle = ta.getFloat(R.styleable.ArcView_startAngle, 0.0f)
        endAngle = ta.getFloat(R.styleable.ArcView_endAngle, 0.0f)
        ta.recycle()
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawArc(0.0f,0.0f, width.toFloat(), height.toFloat(), startAngle, endAngle, true, paint)
    }
}