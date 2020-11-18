package com.example.recyclerviewdemo

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.net.Uri
import android.os.Bundle
import android.webkit.WebChromeClient
import androidx.appcompat.app.AppCompatActivity
import com.github.lzyzsd.jsbridge.BridgeHandler
import com.github.lzyzsd.jsbridge.CallBackFunction
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        initRv()
//        initGridView()
//        initPuBuView()

        initWebView()
    }

    /**
     * 加载
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun initWebView() {
        mWebView.webChromeClient = WebChromeClient()
        mWebView.loadUrl("http://192.168.3.16:8080/")

        /**
         * 给JS 注册个方法
         */
        mWebView.registerHandler("updaloadImage", BridgeHandler { data, function ->
            function.onCallBack("updaloadImage exe, response data from Java")
        })


        /**
         * 调用JS的方法
         */
        val uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + resources.getResourcePackageName(R.drawable.mao) + "/" + resources.getResourceTypeName(R.drawable.mao) + "/" + resources.getResourceEntryName(R.drawable.mao))
        val base64ImageStr = Base64Utils.bitmap2StrByBase64(Base64Utils.drawableToBitmap(getDrawable(R.drawable.mao)!!)!!)
        val user = User("fage", base64ImageStr!!)
        mWebView.callHandler("getImageStr", Gson().toJson(user), CallBackFunction { })
    }

//    /**
//     * 瀑布流
//     */
//    private fun initPuBuView() {
//        mRv.apply {
//            val linearLayoutManager = StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL)
//            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
//            layoutManager = linearLayoutManager
//            itemAnimator = DefaultItemAnimator()
//            addItemDecoration(DividerItemDecoration(context, HORIZONTAL_LIST))
//            addItemDecoration(DividerItemDecoration(context, VERTICAL_LIST))
//            val list = ArrayList<String>()
//            for (i in 0..100) {
//                if (i < 20) {
//                    list.add("fage")
//                } else if (i > 20 ) {
//                    list.add("hailiting")
//                }
//            }
//            val mAdapter = HomePuBuAdapter(context, list)
//            adapter = mAdapter
//            mAdapter.setItemListener(object : OnItemClickListener{
//                override fun onItemClick(view: View, position: Int) {
//                    Toast.makeText(context, "Click$view$position", Toast.LENGTH_SHORT).show()
//                }
//
//                override fun onItemLongClick(view: View, position: Int) {
//                    Toast.makeText(context, "Long$view$position", Toast.LENGTH_SHORT).show()
//                }
//            })
//        }
//    }
//
//    /**
//     * GridView
//     */
//    private fun initGridView() {
//        mRv.apply {
//            val linearLayoutManager = StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL)
//            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
//            layoutManager = linearLayoutManager
//            itemAnimator = DefaultItemAnimator()
//            addItemDecoration(DividerItemDecoration(context, HORIZONTAL_LIST))
//            addItemDecoration(DividerItemDecoration(context, VERTICAL_LIST))
//            val list = ArrayList<String>()
//            for (i in 0..100) {
//                if (i < 20) {
//                    list.add("fage")
//                } else if (i > 20 ) {
//                    list.add("hailiting")
//                }
//            }
//            val mAdapter = HomeAdapter(context, list)
//            adapter = mAdapter
//            mAdapter.setItemListener(object : OnItemClickListener{
//                override fun onItemClick(view: View, position: Int) {
//                    Toast.makeText(context, "Click$view$position", Toast.LENGTH_SHORT).show()
//                }
//
//                override fun onItemLongClick(view: View, position: Int) {
//                    Toast.makeText(context, "Long$view$position", Toast.LENGTH_SHORT).show()
//                }
//            })
//        }
//    }
//
//
//    /**
//     * 列表
//     */
//    private fun initRv() {
//        mRv.apply {
//            val linearLayoutManager = LinearLayoutManager(context)
//            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
//            layoutManager = linearLayoutManager
//            itemAnimator = DefaultItemAnimator()
//            addItemDecoration(DividerItemDecoration(context, VERTICAL_LIST))
//            val list = ArrayList<String>()
//            for (i in 0..100) {
//                if (i < 20) {
//                    list.add("fage")
//                } else if (i > 20 ) {
//                    list.add("hailiting")
//                }
//            }
//            val mAdapter = HomeAdapter(context, list)
//            adapter = mAdapter
//            mAdapter.setItemListener(object : OnItemClickListener{
//                override fun onItemClick(view: View, position: Int) {
//                    Toast.makeText(context, "Click$view$position", Toast.LENGTH_SHORT).show()
//                }
//
//                override fun onItemLongClick(view: View, position: Int) {
//                    Toast.makeText(context, "Long$view$position", Toast.LENGTH_SHORT).show()
//                }
//            })
//        }
//    }
}