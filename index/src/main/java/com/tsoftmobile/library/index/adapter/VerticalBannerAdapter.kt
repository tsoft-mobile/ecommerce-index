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
import com.tsoftmobile.library.index.util.loadUrl
import java.util.*


class VerticalBannerAdapter(
    items: ArrayList<IndexItem.OptionsBean>,
    private val width_height: IntArray
) : RecyclerView.Adapter<VerticalBannerAdapter.ViewHolder>() {
    private var items = ArrayList<IndexItem.OptionsBean>()

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var image: ImageView = v.findViewById(R.id.image)

        init {
            val params = RelativeLayout.LayoutParams(width_height[0], width_height[1])
            image.layoutParams = params
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VerticalBannerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_showcase_single_banner, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: VerticalBannerAdapter.ViewHolder, position: Int) {
        holder.image.loadUrl(items[position].image)
        holder.image.setOnClickListener {
            val typeArr: ArrayList<TypeItem> = items[position].type_arr ?: ArrayList<TypeItem>()
            if (typeArr.isEmpty()) {
                typeArr.add(TypeItem(items[position].type, items[position].type_id))
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    init {
        this.items = items
    }

}
