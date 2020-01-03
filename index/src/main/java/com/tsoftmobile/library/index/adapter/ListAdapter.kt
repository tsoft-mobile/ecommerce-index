package com.tsoftmobile.library.index.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tsoftmobile.library.index.R
import com.tsoftmobile.library.index.model.data.IndexItem
import com.tsoftmobile.library.index.model.data.TypeItem
import java.util.*


class ListAdapter(items: ArrayList<IndexItem.OptionsBean>) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    private var items = ArrayList<IndexItem.OptionsBean>()

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var title: TextView
        var arrow: ImageView
        var container: RelativeLayout

        init {
            title = v.findViewById<View>(R.id.title) as TextView
            arrow = v.findViewById<View>(R.id.arrow_icon) as ImageView
            container = v.findViewById(R.id.container) as RelativeLayout
        }
    }

    init {
        this.items = items
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListAdapter.ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.item_showcase_list, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = items[position].name
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

