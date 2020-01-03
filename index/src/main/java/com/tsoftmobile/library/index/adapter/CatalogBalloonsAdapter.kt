package com.tsoftmobile.library.index.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tsoftmobile.library.index.R
import com.tsoftmobile.library.index.model.data.IndexItem
import com.tsoftmobile.library.index.model.data.TypeItem
import com.tsoftmobile.library.index.util.encodeTurkishCharactersInUrl
import com.tsoftmobile.library.index.util.screenWidth


class CatalogBalloonsAdapter(
    private val items: List<IndexItem.OptionsBean>
) : RecyclerView.Adapter<CatalogBalloonsAdapter.ViewHolder>() {


    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var title: TextView
        var image: ImageView

        init {
            title = v.findViewById<View>(R.id.title) as TextView
            image = v.findViewById<ImageView>(R.id.image) as ImageView
//            title.typeface = TypeFace.OpenSansSemiBold()

            // colors
//            title.setTextColor(appColorsAndBackgrounds.getIndexBalloonTextColor())
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CatalogBalloonsAdapter.ViewHolder {
        val itemWidth = (screenWidth / (4.5))
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_showcase_catalog_balloons, parent, false)
        v.layoutParams =
            RelativeLayout.LayoutParams(itemWidth.toInt(), RelativeLayout.LayoutParams.WRAP_CONTENT)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = items[position].name

        val image_url = items[position].image.encodeTurkishCharactersInUrl()
        Glide.with(holder.image)
            .load(image_url)
            .apply(RequestOptions.fitCenterTransform())
            .into(holder.image)

        holder.itemView.setOnClickListener {
            val typeArr: ArrayList<TypeItem> = items[position].type_arr ?: ArrayList<TypeItem>()
            if (typeArr.isEmpty()) {
                typeArr.add(TypeItem(items[position].type, items[position].type_id))

            }

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
