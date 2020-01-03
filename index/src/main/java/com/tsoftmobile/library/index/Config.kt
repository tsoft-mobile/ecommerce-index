package com.tsoftmobile.library.index

import com.tsoftmobile.library.index.util.Share
import java.util.*

object Config {
    var SERVICE_BASE_URL = ""
    var TOKEN = ""

    fun getDefaultParams(): HashMap<String, Any> {
        val params = hashMapOf<String, Any>()
        params["token"] = TOKEN
        return params
    }

    //Banner Slide Time
    var bannerSlideTime: Int
        get() = Share.getInt("slide_time", 5)
        set(slideTime) = Share.setInt("slide_time", slideTime)


    //Banner Width
    var bannerWidth: Int
        get() = Share.getInt("banner_width", 100)
        set(bannerWidth) = Share.setInt("banner_width", bannerWidth)

    //Banner Height
    var bannerHeight: Int
        get() = Share.getInt("banner_height", 64)
        set(bannerWidth) = Share.setInt("banner_height", bannerWidth)

    var virguleChar: Int
        get() = Share.getInt("virgule_char", 2)
        set(virgule_char) = Share.setInt("virgule_char", virgule_char)

    var forceDealer = false

    var creditCardSpecialDiscount: Double = 0.0
    var hidePricesIfNoLogin: Boolean = false
    var hidePricesIfNoStock = true
    var hideDiscountPercentView = false
}