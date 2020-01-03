package com.tsoftmobile.library.index.util

import android.content.Context
import android.graphics.Point
import android.os.Build
import android.view.WindowManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.tsoftmobile.library.index.TSoftApplication
import java.io.UnsupportedEncodingException
import java.net.URLEncoder

val screenWidth: Int by lazy {
    val wm =
        TSoftApplication.applicationContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val display = wm.defaultDisplay
    val size = Point()
    display.getSize(size)
    size.x
}

fun addVat(money: Double, vat: Int): Double {
    var total = 0.0
    total = money * (vat / 100.00) + money
    return total
}

fun String?.encodeTurkishCharactersInUrl(): String? { // todo linkler neden açılmıyor http de  güzelce bak.
    var url = this
    val list =
        arrayOf("ü", "ç", "ı", "ö", "ğ", "ş", " ", "Ü", "Ç", "İ", "Ö", "Ğ", "Ş")
    for (i in list.indices) {
        try {
            url = url?.replace(
                list[i],
                URLEncoder.encode(list[i], "UTF-8").replace("+", "%20")
            )
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
    }
    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
        url = url?.replace("https", "http")
    }
    return url
}


//TODO load yapılacak
fun ImageView.loadUrl(url: String): ImageView {
    Glide.with(this)
        .load(url)
        .fitCenter()
        .into(this)
    return this
}

fun getWidthHeightWithColumn(
    ratio: Double,
    column: Int
): IntArray {
    val screen_width: Double = screenWidth.toDouble()
    val width = screen_width.toInt() / column
    val height = (width / ratio).toInt()
    return intArrayOf(width, height)
}

fun String.isInteger(): Boolean {
    for (i in 0 until this.length) {
        try {
            this.substring(i, i + 1).toInt()
        } catch (e: NumberFormatException) {
            return false
        } catch (e: NullPointerException) {
            return false
        }
    }
    return true
}
