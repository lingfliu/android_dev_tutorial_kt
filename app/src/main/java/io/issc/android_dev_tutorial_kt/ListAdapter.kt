package io.issc.android_dev_tutorial_kt

import android.animation.ObjectAnimator
import android.util.Log
import android.view.*
import android.view.GestureDetector.OnGestureListener
import android.view.View.OnTouchListener
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class ListAdapter(val dataList: List<Contact>): RecyclerView.Adapter<ListAdapter.ListItemHolder>() {

    interface OnOpListener {
        fun onRemove(data:Contact)
    }

    var opListener:OnOpListener? = null

    class ListItemHolder(itemView: View) :ViewHolder(itemView) {

        var txtName = itemView.findViewById<TextView>(R.id.txt_name)
        var txtNum = itemView.findViewById<TextView>(R.id.txt_num)
        var btn = itemView.findViewById<ImageButton>(R.id.btn_remove)
        var rootView = itemView.findViewById<ViewGroup>(R.id.container)
        var gestureDetector: GestureDetector
        lateinit var data:Contact

        init {
            gestureDetector = GestureDetector(rootView.context, object:OnGestureListener{
                override fun onDown(p0: MotionEvent): Boolean {
                    return false
                }

                override fun onShowPress(p0: MotionEvent) {
                }

                override fun onSingleTapUp(p0: MotionEvent): Boolean {
                    return false
                }

                override fun onScroll(e1: MotionEvent, e2: MotionEvent, dx: Float, dy: Float): Boolean {
                    return true
                }

                override fun onLongPress(p0: MotionEvent) {
                }

                override fun onFling(p0: MotionEvent, p1: MotionEvent, vx: Float, vy: Float): Boolean {
                    Log.d("adapter", "fling " + vx.toString())
                    if (vx > 10) {
                        val anime = ObjectAnimator.ofFloat(rootView, "translationX", rootView.translationX, 0.0f)
                        anime.duration= 200
                        anime.start()
                    }
                    else if (vx < -10) {
                        val anime = ObjectAnimator.ofFloat(rootView, "translationX", rootView.translationX, -btn.width.toFloat())
                        anime.duration= 200
                        anime.start()
                    }
                    return true
                }
            })

            rootView.setOnTouchListener(object : OnTouchListener{
                override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                    event?.let { gestureDetector.onTouchEvent(it) }
                    return true
                }
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)

        return ListItemHolder(v)
    }


    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ListItemHolder, position: Int) {
        holder.txtName.text = dataList.get(position).name
        holder.txtNum.text = dataList.get(position).num
        holder.data = dataList.get(position)
        holder.btn.setOnClickListener {
            //TODO: position update
            opListener?.onRemove(holder.data)
        }
    }
}