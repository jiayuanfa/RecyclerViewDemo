package com.example.recyclerviewdemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewdemo.R

class HomeAdapter(private val mContext: Context, private val list: List<String>) : RecyclerView.Adapter<HomeAdapter.MyViewHolder>(),
    View.OnClickListener, View.OnLongClickListener {

    private var mListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(mContext).inflate(R.layout.item_home, parent, false)
        view.setOnClickListener(this)
        view.setOnLongClickListener(this)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tv!!.text = list[position]
        holder.itemView.tag = position
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tv: TextView? = null

        init {
            tv = view.findViewById(R.id.textView)
        }
    }

    fun setItemListener(listener: OnItemClickListener) {
        mListener = listener
    }

    override fun onClick(p0: View?) {
        mListener?.onItemClick(p0!!, p0.tag as Int)
    }

    override fun onLongClick(p0: View?): Boolean {
        mListener?.onItemLongClick(p0!!, p0.tag as Int)
        return true
    }
}
