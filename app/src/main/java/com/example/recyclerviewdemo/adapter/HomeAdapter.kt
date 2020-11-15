package com.example.recyclerviewdemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewdemo.R

class HomeAdapter(private val mContext: Context, private val list: List<String>) : RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tv!!.text = list[position]
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tv: TextView? = null

        init {
            tv = view.findViewById(R.id.textView)
        }
    }
}
