package com.tsoftmobile.library.index.model.response

import com.tsoftmobile.library.index.model.data.IndexItem

class IndexResponse {
    val success: Boolean = false
    val data: DataBean? = null
    class DataBean {
        val app_main_color: String? = null
        val app_secondary_color: String? = null
        val app_sidebar_background_color: String? = null
        val app_sidebar_tint_color: String? = null
        val app_license = true
        val force_update = false
        val slider_index: Int = 0
        val slider_duration: Int = 0
        val list: List<IndexItem>? = null
        val cart_total_count: Double? = null
    }
}