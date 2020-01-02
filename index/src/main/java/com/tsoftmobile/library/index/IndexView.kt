package com.tsoftmobile.library.index

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import com.tsoftmobile.library.index.databinding.LayoutIndexViewBinding


class IndexView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val TAG = javaClass.simpleName
    private val mContext = getContext()
    var mLayout: Int = R.layout.layout_index_view
    private var mLayoutInflater: LayoutInflater = LayoutInflater.from(mContext)
    private var binding: LayoutIndexViewBinding? = null


    init {
        setLayout()
    }

    fun setLayout() {
        binding = DataBindingUtil.inflate(mLayoutInflater, mLayout, this, true)
    }

}