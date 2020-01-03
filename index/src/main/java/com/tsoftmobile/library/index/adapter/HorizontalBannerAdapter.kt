package com.tsoftmobile.library.index.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.tsoftmobile.library.index.R
import com.tsoftmobile.library.index.model.data.IndexItem
import com.tsoftmobile.library.index.model.data.TypeItem
import com.tsoftmobile.library.index.util.getWidthHeightWithColumn
import com.tsoftmobile.library.index.util.loadUrl
import java.util.*


class HorizontalBannerAdapter(
    items: List<IndexItem.OptionsBean>,
    private val ratio: Double,
    private val NumberOfColumn: Int
) : RecyclerView.Adapter<HorizontalBannerAdapter.ViewHolder>() {
    private var items: List<IndexItem.OptionsBean> = ArrayList()

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var image: ImageView = v.findViewById(R.id.image)
    }

    init {
        this.items = items
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HorizontalBannerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_showcase_single_banner, parent, false)
        val params = RelativeLayout.LayoutParams(
            getWidthHeightWithColumn(ratio, NumberOfColumn)!![0],
            getWidthHeightWithColumn(ratio, NumberOfColumn)!![1]
        )
        v.layoutParams = params
        return ViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: HorizontalBannerAdapter.ViewHolder,
        position: Int
    ) {
        holder.image.loadUrl(items[position].image)
        holder.image.setOnClickListener {
            val typeArr: ArrayList<TypeItem> = items[position].type_arr ?: ArrayList<TypeItem>()
            if (typeArr.isEmpty()) {
                typeArr.add(TypeItem(items[position].type, items[position].type_id))
            }
            //(activity as ProductShowcaseActivity).startTypeArrParse(items = typeArr, previosPage = PreviousPage.Index, productListHeader = items[position].name)
        }
    }


    fun changeData(items: List<IndexItem.OptionsBean>) {
        this.items = items
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return items.size
    }
}
