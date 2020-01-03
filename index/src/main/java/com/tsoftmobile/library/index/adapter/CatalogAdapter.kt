package com.tsoftmobile.library.index.adapter

import android.graphics.Paint
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tsoftmobile.library.index.Config
import com.tsoftmobile.library.index.R
import com.tsoftmobile.library.index.TSoftApplication
import com.tsoftmobile.library.index.databinding.ItemShowcaseCatalogProductBinding
import com.tsoftmobile.library.index.model.data.IndexItem
import com.tsoftmobile.library.index.util.addVat
import com.tsoftmobile.library.index.util.encodeTurkishCharactersInUrl
import com.tsoftmobile.library.index.util.screenWidth
import java.util.*


class CatalogAdapter(
    items: ArrayList<IndexItem.OptionsBean>,
    column: Int
) : RecyclerView.Adapter<CatalogAdapter.ViewHolder>() {

    private var items = ArrayList<IndexItem.OptionsBean>()
    private var priceSellD = 0.0
    private var priceNotDiscountedD = 0.0
    private var NumberOfColumn = 1
    private var itemWidth = 1.0
    private var titleTextSize: Int = 0
    private var priceTextSize: Int = 0
    private val TAG = javaClass.simpleName
    private var displayVat = false

    init {
        this.items = items
        this.NumberOfColumn = column
        this.itemWidth =
            if (items.size != NumberOfColumn) (NumberOfColumn + (NumberOfColumn / 8.0)) else NumberOfColumn.toDouble()
        when (NumberOfColumn) {
            1 -> {
                priceTextSize = 14
                titleTextSize = 16
            }
            2 -> {
                priceTextSize = 12
                titleTextSize = 14
            }
            3 -> {
                priceTextSize = 10
                titleTextSize = 12
            }
            4 -> {
                priceTextSize = 8
                titleTextSize = 10
            }
            else -> {
                priceTextSize = 8
                titleTextSize = 10
            }
        }
    }


    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        var binding: ItemShowcaseCatalogProductBinding? =
            DataBindingUtil.bind<ItemShowcaseCatalogProductBinding>(v)

        init {

            //Renk Ayarlamaları
//            binding?.discountPercent?.background = commonDiscountBackground
//            binding?.priceSell?.setTextColor(getAppMainColor())
            /*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                  binding.addToCart.imageTintList = ColorStateList.valueOf(getListAddToCartColor())
              }*/

            //boyutlandırmalar

            binding?.priceSell?.textSize = priceTextSize.toFloat()
            binding?.priceNotDiscounted?.textSize = (priceTextSize - 2).toFloat()
            binding?.title?.textSize = titleTextSize.toFloat()
            binding?.discountPercent?.textSize = priceTextSize.toFloat()

        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CatalogAdapter.ViewHolder {
        // create a new view
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_showcase_catalog_product, parent, false)
        // set the view's size, margins, paddings and layout parameters
        v.layoutParams = RecyclerView.LayoutParams(
            (screenWidth / itemWidth).toInt(),
            RecyclerView.LayoutParams.WRAP_CONTENT
        )   // width=screenSize/NumberOfColumn
        return ViewHolder(v)
    }


    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        val product = items.get(position)
        holder.binding?.data = product
        displayVat =
            product.display_vat == "1"



        if (displayVat)
            priceSellD = addVat(product.price_sell, product.vat)
        else
            priceSellD = product.price_sell


        priceNotDiscountedD = addVat(product.price_not_discounted, product.vat)


        if (product.is_discount_active == "0" || product.is_discount_active.isNullOrBlank()) {
            holder.binding?.discountPercent?.visibility = View.GONE
            holder.binding?.priceNotDiscounted?.visibility = View.INVISIBLE
        } else {
            if (Config.hideDiscountPercentView) {
                holder.binding?.discountPercent?.visibility = View.GONE
            } else {
                holder.binding?.discountPercent?.visibility = View.VISIBLE
                val discountPercentTxt = "%" + product.discount_percent
                holder.binding?.discountPercent?.text = discountPercentTxt
            }
            holder.binding?.priceNotDiscounted?.visibility = View.VISIBLE
            val priceNotDiscountedTxt = String.format(
                Locale("tr", "TR"),
                "%,." + Config.virguleChar + "f",
                priceNotDiscountedD
            ) + " " + product.target_currency
            holder.binding?.priceNotDiscounted?.text = priceNotDiscountedTxt
            holder.binding?.priceNotDiscounted?.paintFlags =
                holder.binding?.priceNotDiscounted!!.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }
        holder.binding?.title?.text = product.name
        val priceSellText = (String.format(
            Locale("tr", "TR"),
            "%,." + Config.virguleChar + "f",
            priceSellD
        ) + " " + product.target_currency) + if (!displayVat) " " + TSoftApplication.applicationContext().getString(
            R.string.vat
        ) else ""
        if (Config.creditCardSpecialDiscount > 0.0) { // vipbrands'a özel olarak yapıyoruz.
            holder.binding?.priceSell?.maxLines = 2
            holder.binding?.priceNotDiscounted?.visibility =
                View.GONE // müşteri indirimsiz fiyatın görünmesini istemedi.
            val creditCartDiscountPriceStr = String.format(
                Locale("tr", "TR"),
                "%,." + Config.virguleChar + "f",
                (priceSellD - (priceSellD * Config.creditCardSpecialDiscount / 100))
            ) + " " + product?.target_currency
            val priceTextWithCc = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(
                    "<p>COD: $priceSellText<br><font color='#e56b4e'><b>CC\u00A0\u00A0 : $creditCartDiscountPriceStr<b></font></p>",
                    Html.FROM_HTML_MODE_COMPACT
                )
            } else {
                Html.fromHtml("<p>COD: $priceSellText<br><font color='#e56b4e'><b>CC\u00A0\u00A0 : $creditCartDiscountPriceStr<b></font></p>")
            }
            holder.binding?.priceSell?.text = priceTextWithCc
        } else
            holder.binding?.priceSell?.text = priceSellText

        if (product.is_display_product == "1" || ((Config.forceDealer || Config.hidePricesIfNoLogin)) || (Config.hidePricesIfNoStock && !product.in_stock)) {   //is_display_product 1 ise  veya bayi zorlama aktif ise.
            holder.binding?.priceContainer?.visibility = View.INVISIBLE
        } else
            holder.binding?.priceContainer?.visibility = View.VISIBLE


        val imageUrl = product.image.encodeTurkishCharactersInUrl() ?: ""
        if (imageUrl.endsWith(".gif")) {
            holder.binding?.photo?.let { imageView ->
                Glide.with(imageView)
                    .asGif()
                    .load(imageUrl)
                    .fitCenter()
                    .override(holder.itemView.width,holder.itemView.height)
                    .into(imageView)
            }

        } else {
            holder.binding?.photo?.let { imageView ->
                imageView.layout(0,0,0,0)
                Glide.with(imageView)
                    .asBitmap()
                    .load(imageUrl)
                    .fitCenter()
                    .into(imageView)
            }
        }

    }


    override fun getItemCount(): Int {
        return items.size
    }
}