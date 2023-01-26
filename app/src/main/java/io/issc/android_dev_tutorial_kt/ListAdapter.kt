package io.issc.android_dev_tutorial_kt

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class ListAdapter: RecyclerView.Adapter<ListAdapter.ListItemHolder>() {


    class ListItemHolder(itemView: View) :ViewHolder(itemView) {

        init {
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemHolder {
        TODO("Not yet implemented")
    }


    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ListItemHolder, position: Int) {
        TODO("Not yet implemented")
    }
}