package com.tsoftmobile.library.index.adapter

import android.view.View
import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.tsoftmobile.library.index.databinding.ItemShowcaseCatalogProductBinding
import com.tsoftmobile.library.index.model.data.IndexItem

class NewIndexAdapter(private val items: ArrayList<IndexItem>) :
    BaseQuickAdapter<IndexItem, BaseViewHolder>(items) {


    override fun convert(helper: BaseViewHolder, item: IndexItem) {
        val itemType = getItemViewType(helper.adapterPosition)
        if (itemType == 0) {
            val mHelper = helper as HorizontalViewHolder
            mHelper.binding?.discountPercent

        }

    }

    inner class CustomBaseIndexViewHolder(view: View) : BaseViewHolder(view) {

    }

    inner class HorizontalViewHolder(view: View) : BaseViewHolder(view) {
        val binding: ItemShowcaseCatalogProductBinding? =
            DataBindingUtil.bind<ItemShowcaseCatalogProductBinding>(view)
    }

}