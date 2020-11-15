package com.example.recyclerviewdemo.adapter

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.nio.charset.IllegalCharsetNameException

/**
 * 绘制分割线
 * 本质是onDraw方法重绘
 */
class DividerItemDecoration(context: Context, orientation: Int) : RecyclerView.ItemDecoration() {

    private val attrs: IntArray = intArrayOf(android.R.attr.listDivider)

    companion object {
       const val HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL
       const val VERTICAL_LIST = LinearLayoutManager.VERTICAL
    }

    private var mDivider: Drawable? = null
    private var mOrientation: Int? = null

    /**
     * 拿到Drawable
     */
    init {
        val a: TypedArray = context.obtainStyledAttributes(attrs)
        mDivider = a.getDrawable(0)
        a.recycle()
        setOrientation(orientation)
    }

    /**
     * 设置水平或者垂直的分割线
     */
    private fun setOrientation(orientation: Int) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw IllegalCharsetNameException("invalid orientation!")
        }

        mOrientation = orientation
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (mOrientation == VERTICAL_LIST) {
            drawVertical(c, parent)
        } else {
            drawHorizontal(c, parent)
        }
    }

    /**
     * 绘制水平列表的分割线
     * 对于每个Item来说是竖线
     */
    private fun drawHorizontal(c: Canvas, parent: RecyclerView) {

        // 拿到顶部
        val top = parent.paddingTop

        // 拿到Bottom 高 - 下边距
        val bottom = parent.height - parent.paddingBottom

        val childCount = parent.childCount

        for (i in 0 until childCount) {
            val child = parent[i]

            val params = child.layoutParams as RecyclerView.LayoutParams

            // 左起点= View的右边-右边距
            val left = child.right + params.rightMargin
            val right = left + mDivider!!.intrinsicWidth
            mDivider!!.setBounds(left, top, right, bottom)
            mDivider!!.draw(c)
        }
    }

    /**
     * 绘制垂直列表的分割线
     * 对于每个item来说是横线
     * Demo: parent
     * top: 0
     * right: 100
     * paddingRight: 15
     * width: 100
     * height: 40
     */
    private fun drawVertical(c: Canvas, parent: RecyclerView) {
        // 左边: 从最左边开始 0
        val left = parent.paddingLeft + 40

        // 右边：宽度减去内边距 100-0 = 100
        val right = parent.width - parent.paddingRight
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent[i]
            val params = child.layoutParams as RecyclerView.LayoutParams

            // 顶部 子视图的底部（40）+ 下边距0 = 40
            val top = child.bottom + params.bottomMargin

            // 底部 顶部 + 分割线的高度
            val bottom = top + mDivider!!.intrinsicHeight
            mDivider!!.setBounds(left, top, right, bottom)
            mDivider!!.draw(c)
        }
    }

    /**
     * 设置内边距
     */
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (mOrientation == VERTICAL_LIST) {
            outRect.set(0,0,0,mDivider!!.intrinsicHeight)
        } else {
            outRect.set(0,0,mDivider!!.intrinsicWidth, 0)
        }
    }
}