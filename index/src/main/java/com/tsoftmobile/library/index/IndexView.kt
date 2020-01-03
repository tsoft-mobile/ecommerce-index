package com.tsoftmobile.library.index

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tsoftmobile.library.index.adapter.IndexAdapter
import com.tsoftmobile.library.index.databinding.LayoutIndexViewBinding
import com.tsoftmobile.library.index.model.Resource
import com.tsoftmobile.library.index.model.Status
import com.tsoftmobile.library.index.model.data.IndexItem
import com.tsoftmobile.library.index.model.response.IndexResponse
import com.tsoftmobile.library.index.repository.IndexRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class IndexView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private val TAG = "IndexView"
    private val indexRepository = IndexRepository()
    private val mContext = getContext()
    private var mLayout: Int = R.layout.layout_index_view
    private var mLayoutInflater: LayoutInflater = LayoutInflater.from(mContext)
    private var binding: LayoutIndexViewBinding? = null


    private var adapter = IndexAdapter(arrayListOf())

    var token: String? = null
        set(value) {
            field = value
            Config.TOKEN = value ?: ""
        }
    var url: String? = null
        set(value) {
            field = value
            Config.SERVICE_BASE_URL = value ?: ""
        }

    init {
        binding = DataBindingUtil.inflate(mLayoutInflater, mLayout, this, true)
        binding?.recyclerView?.setHasFixedSize(true)
        binding?.recyclerView?.layoutManager =
            LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)
        binding?.recyclerView?.adapter = adapter
        if (!url.isNullOrEmpty() && !token.isNullOrEmpty()) {
            fetchIndex()
        }
    }

    @SuppressLint("CheckResult")
    fun fetchIndex() {
        indexRepository.fetchIndex()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                onIndexResult(it)
            }
    }

    open fun onIndexResult(result: Resource<IndexResponse>) {
        if (result.status == Status.SUCCESS && result.data?.success == true) {
            val list = ArrayList<IndexItem>()
            result.data.data?.list?.forEach { indexItem ->
                indexItem.options?.let { options ->
                    if (options.isNotEmpty())
                        list.add(indexItem)
                }
            }
            adapter?.replaceData(list)
        }
    }

}