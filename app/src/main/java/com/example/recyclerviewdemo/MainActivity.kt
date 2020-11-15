package com.example.recyclerviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewdemo.adapter.DividerItemDecoration
import com.example.recyclerviewdemo.adapter.DividerItemDecoration.Companion.HORIZONTAL_LIST
import com.example.recyclerviewdemo.adapter.DividerItemDecoration.Companion.VERTICAL_LIST
import com.example.recyclerviewdemo.adapter.HomeAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRv()
    }

    private fun initRv() {
        mRv.apply {
            val linearLayoutManager = LinearLayoutManager(context)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            layoutManager = linearLayoutManager
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(DividerItemDecoration(context, VERTICAL_LIST))
            val list = ArrayList<String>()
            for (i in 0..100) {
                if (i < 20) {
                    list.add("fage")
                } else if (i > 20 ) {
                    list.add("hailiting")
                }
            }
            adapter = HomeAdapter(context, list)
        }
    }
}