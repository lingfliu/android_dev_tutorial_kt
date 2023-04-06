package io.issc.android_dev_tutorial_kt

import android.animation.ObjectAnimator
import android.hardware.Sensor
import android.util.Log
import android.view.*
import android.view.GestureDetector.OnGestureListener
import android.view.View.OnTouchListener
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class SensorListAdapter(val dataList: List<Sensor>): RecyclerView.Adapter<SensorListAdapter.ListItemHolder>() {

    class ListItemHolder(itemView: View) :ViewHolder(itemView) {

        var txtName = itemView.findViewById<TextView>(R.id.txt_name)
        var txtVal = itemView.findViewById<TextView>(R.id.txt_val)
        lateinit var data:Sensor
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.item_sensor_list, parent, false)

        return ListItemHolder(v)
    }


    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ListItemHolder, position: Int) {
        holder.txtName.text = dataList.get(position).name
        holder.txtVal.text = ""
        holder.data = dataList.get(position)
    }
}