package com.example.recyclerviewdemo

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.util.Base64
import android.util.Base64.encodeToString
import java.io.ByteArrayOutputStream
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream


object Base64Utils {

    /**
     * 将图片转换成Base64编码的字符串
     */
    fun imageToBase64(path: String?): String? {
        if (TextUtils.isEmpty(path)) {
            return null
        }
        var `is`: InputStream? = null
        var data: ByteArray? = null
        var result: String? = null
        try {
            `is` = FileInputStream(path)
            //创建一个字符流大小的数组。
            data = ByteArray(`is`.available())
            //写入数组
            `is`.read(data)
            //用默认的编码格式进行编码
            result = Base64.encodeToString(data, Base64.DEFAULT)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (null != `is`) {
                try {
                    `is`.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        return result
    }

    fun drawableToBitmap(drawable: Drawable): Bitmap? // drawable 转换成bitmap
    {
        val width = drawable.intrinsicWidth // 取drawable的长宽
        val height = drawable.intrinsicHeight
        val config =
            if (drawable.opacity != PixelFormat.OPAQUE) Bitmap.Config.ARGB_8888 else Bitmap.Config.RGB_565 // 取drawable的颜色格式
        val bitmap = Bitmap.createBitmap(width, height, config) // 建立对应bitmap
        val canvas = Canvas(bitmap) // 建立对应bitmap的画布
        drawable.setBounds(0, 0, width, height)
        drawable.draw(canvas) // 把drawable内容画到画布中
        return bitmap
    }

    /**
     * Bitmap转Base64
     */
    fun bitmap2StrByBase64(bit: Bitmap): String? {
        val bos = ByteArrayOutputStream()
        bit.compress(Bitmap.CompressFormat.JPEG, 40, bos) //参数100表示不压缩
        val bytes: ByteArray = bos.toByteArray()
        return encodeToString(bytes, Base64.DEFAULT)
    }
}