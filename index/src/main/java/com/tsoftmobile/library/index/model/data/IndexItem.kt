package com.tsoftmobile.library.index.model.data

import com.tsoftmobile.library.index.adapter.SocialMediaAdapter

class IndexItem  {
    val name: String? = null
    val type: String? = null
    val column: Int = 0
    val ratio: Double = 0.toDouble()
    val is_header_visible: Boolean = false
    val options: ArrayList<OptionsBean>? = null

    class OptionsBean{
        val name = ""
        val type = ""
        val type_id = ""
        val type_arr: ArrayList<TypeItem>? = ArrayList()
        val image = ""
        val id = ""
        val platform_id: String? = ""
        val is_new_product = ""
        val variant_id: String? = null
        val category_id: String? = null
        val category_ids: String? = null
        val price_sell = 0.0
        val price_buy = 0.0
        val price_not_discounted = 0.0
        val supplier_product_code: String? = null
        val currency_id: String? = null
        val vat: Int = 0
        val has_image: String? = null
        val has_variant: Int = 0
        val display_vat: String? = null
        val stock: String? = null
        val stock_unit_id: String? = null
        val product_code: String? = null
        val short_description: String? = null
        val is_discount_active: String? = null
        val is_display_discounted_active: String? = null
        val discount_percent: Int = 0
        val is_multiple_discount_active: Int = 0
        val category_name: String? = null
        val filter_category_id: String? = null
        val url: String? = null
        val in_stock: Boolean = false
        val is_display_product: String? = null
        val image_ratio: Float = 1f
        val currency: String? = null
        val target_currency: String? = null
        val icon: String? = null
        val can_add_cart_in_list: Boolean = false
        val brand: String? = null

        var favoriteActive: Boolean = false


        val platform: SocialMediaAdapter.SocialMediaType?
            get() {
                var type: SocialMediaAdapter.SocialMediaType? = null
                when (this.type) {
                    "facebook" -> type = SocialMediaAdapter.SocialMediaType.facebook
                    "google-plus" -> type = SocialMediaAdapter.SocialMediaType.google_plus
                    "instagram" -> type = SocialMediaAdapter.SocialMediaType.instagram
                    "pinterest" -> type = SocialMediaAdapter.SocialMediaType.pinterest
                    "twitter" -> type = SocialMediaAdapter.SocialMediaType.twitter
                    "youtube" -> type = SocialMediaAdapter.SocialMediaType.youtube
                    "tumblr" -> type = SocialMediaAdapter.SocialMediaType.tumblr
                    "whatsapp" -> type = SocialMediaAdapter.SocialMediaType.whatsapp
                    "snapchat" -> type = SocialMediaAdapter.SocialMediaType.snapchat
                    "skype" -> type = SocialMediaAdapter.SocialMediaType.skype
                    "linkedin" -> type = SocialMediaAdapter.SocialMediaType.linkedin
                }
                return type
            }
    }
}