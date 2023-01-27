package io.issc.android_dev_tutorial_kt

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.core.content.res.ResourcesCompat

//implement surfaceholder callback
class ArcSurfView(context: Context, attr: AttributeSet?) : SurfaceView(context, attr), SurfaceHolder.Callback {

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
        paint.color = Color.parseColor("blue")
        paint.strokeWidth = 5.0f
        paint.style = Paint.Style.STROKE

        val ta = context.obtainStyledAttributes(attr, R.styleable.ArcView)
        startAngle = ta.getFloat(R.styleable.ArcView_startAngle, 0.0f)
        endAngle = ta.getFloat(R.styleable.ArcView_endAngle, 0.0f)
        ta.recycle()
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawArc(0.0f,0.0f, width.toFloat(), height.toFloat(), startAngle, endAngle, true, paint)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        showAnimation()
    }

    fun showAnimation() {
        val thread = Thread{
            while (true) {
                val canvas = holder.lockCanvas()
                if (canvas == null) {
                    Thread.sleep(2)
                    continue
                }
                canvas.drawColor(Color.WHITE)
                //show animation

                endAngle += 4.0f
                Thread.sleep(2)
                if (endAngle > 360.0f) {
                    endAngle = 0.0f
                }
                canvas.drawArc(0.0f,0.0f, width.toFloat()-5, height.toFloat()-5, startAngle, endAngle, false, paint)
                holder.unlockCanvasAndPost(canvas)
            }
        }
        thread.start()
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        showAnimation()
    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
    }
}