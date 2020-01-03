package com.tsoftmobile.library.index.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import com.tsoftmobile.library.index.Config
import com.tsoftmobile.library.index.R
import com.tsoftmobile.library.index.TSoftApplication
import com.tsoftmobile.library.index.model.data.IndexItem
import com.tsoftmobile.library.index.util.getWidthHeightWithColumn
import kotlin.collections.ArrayList

class IndexAdapter(list: ArrayList<IndexItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val TAG = "NewIndexAdapter"
    private var list = ArrayList<IndexItem>()
    private val mContext = TSoftApplication.applicationContext()

    fun replaceData(items: ArrayList<IndexItem>) {
        this.list = items
        notifyDataSetChanged()
    }

    init {
        this.list = list
    }

    private inner class VerticalTypeViewHolder internal constructor(v: View) :
        RecyclerView.ViewHolder(v) {
        // each data MyItem is just a string in this case
        var title: TextView
        var recyclerView: RecyclerView

        init {
            title = v.findViewById<View>(R.id.title) as TextView
            recyclerView = v.findViewById<View>(R.id.recyclerview) as RecyclerView
            //set TypeFace
//            title.typeface = TypeFace.showCaseBannerText()

            //color
//            title.setTextColor(appColorsAndBackgrounds.getIndexTitleColor())
        }
    }

    private inner class HorizontalTypeViewHolder internal constructor(v: View) :
        RecyclerView.ViewHolder(v) {
        // each data MyItem is just a string in this case
        internal val title: TextView
        internal val viewPager: ViewPager
        internal val circlePageIndicator: WormDotsIndicator

        init {
            title = v.findViewById<View>(R.id.title) as TextView
            viewPager = v.findViewById<View>(R.id.view_pager) as ViewPager
            circlePageIndicator = v.findViewById<View>(R.id.pagerIndicator) as WormDotsIndicator
//            title.typeface = TypeFace.showCaseBannerText()
            //color
//            title.setTextColor(appColorsAndBackgrounds.getIndexTitleColor())
        }
    }

    private inner class CatalogTypeViewHolder internal constructor(v: View) :
        RecyclerView.ViewHolder(v) {
        // each data MyItem is just a string in this case
        internal val title: TextView = v.findViewById<View>(R.id.title) as TextView
        internal val recyclerView: RecyclerView =
            v.findViewById<View>(R.id.recyclerview) as RecyclerView

        init {
            //set TypeFace
//            title.typeface = TypeFace.showCaseBannerText()
            //color
//            title.setTextColor(appColorsAndBackgrounds.getIndexTitleColor())
        }
    }

    private inner class ListTypeViewHolder internal constructor(v: View) :
        RecyclerView.ViewHolder(v) {
        // each data MyItem is just a string in this case
        internal val title: TextView = v.findViewById<View>(R.id.title) as TextView
        internal val recyclerView: RecyclerView =
            v.findViewById<View>(R.id.recyclerview) as RecyclerView

        init {
            //set TypeFace
//            title.typeface = TypeFace.showCaseBannerText()
            //color
//            title.setTextColor(appColorsAndBackgrounds.getIndexTitleColor())
        }
    }

    private inner class SocialMediaTypeViewHolder internal constructor(v: View) :
        RecyclerView.ViewHolder(v) {
        // each data MyItem is just a string in this case
        internal val title: TextView
        internal val recyclerView: RecyclerView
        internal val recyclerviewContainer: FrameLayout
        private val container: RelativeLayout


        init {
            title = v.findViewById<View>(R.id.title) as TextView
            recyclerView = v.findViewById<View>(R.id.recyclerview) as RecyclerView
            container = v.findViewById<View>(R.id.container) as RelativeLayout
            recyclerviewContainer = v.findViewById<FrameLayout>(R.id.recyclerview_container)
            //set TypeFace
            val params = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(4, 4, 4, 4)    // sosyal medya ekran ayarları.
            itemView.layoutParams = params
//            title.typeface = TypeFace.showCaseBannerText()
            container.setBackgroundResource(R.drawable.white_outline_stroke)
            //color
//            title.setTextColor(appColorsAndBackgrounds.getIndexTitleColor())
        }
    }

    private inner class CatalogBalloonsTypeViewHolder internal constructor(v: View) :
        RecyclerView.ViewHolder(v) {
        // each data MyItem is just a string in this case
        internal val title: TextView = v.findViewById<View>(R.id.title) as TextView
        internal val recyclerView: RecyclerView =
            v.findViewById<View>(R.id.recyclerview) as RecyclerView

        init {
            // recyclerview tüm ekranı kaplasın.
            recyclerView.layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            )
            //set TypeFace
//            title.typeface = TypeFace.showCaseBannerText()
            //color
//            title.setTextColor(appColorsAndBackgrounds.getIndexTitleColor())

        }
    }


    override fun getItemViewType(position: Int): Int {
        var current_position = 0
        when (list[position].type) {
            "vertical" -> current_position = VerticalShowCaseViewtype
            "horizontal" -> current_position = HorizontalShowCaseViewtype
            "catalog" -> current_position = CatalogShowCaseViewtype
            "list" -> current_position = ListShowCaseViewtype
            "socialmedia" -> current_position = SocialMediaShowCaseViewtype
            "catalog_balloons" -> current_position = CatalogBalloonsCaseViewtype
        }
        return current_position
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder: RecyclerView.ViewHolder
        val inflater = LayoutInflater.from(viewGroup.context)
        when (viewType) {
            VerticalShowCaseViewtype -> {
                val v1 =
                    inflater.inflate(R.layout.item_showcase_base_recyclerview, viewGroup, false)
                viewHolder = VerticalTypeViewHolder(v1)
            }
            HorizontalShowCaseViewtype -> {
                val v2 = inflater.inflate(R.layout.item_showcase_base_viewpager, viewGroup, false)
                viewHolder = HorizontalTypeViewHolder(v2)
            }

            CatalogShowCaseViewtype -> {
                val v3 =
                    inflater.inflate(R.layout.item_showcase_base_recyclerview, viewGroup, false)
                viewHolder = CatalogTypeViewHolder(v3)
            }

            ListShowCaseViewtype -> {
                val v4 =
                    inflater.inflate(R.layout.item_showcase_base_recyclerview, viewGroup, false)
                viewHolder = ListTypeViewHolder(v4)
            }

            SocialMediaShowCaseViewtype -> {
                val v5 =
                    inflater.inflate(R.layout.item_showcase_base_recyclerview, viewGroup, false)
                viewHolder = SocialMediaTypeViewHolder(v5)
            }

            CatalogBalloonsCaseViewtype -> {
                val v6 =
                    inflater.inflate(R.layout.item_showcase_base_recyclerview, viewGroup, false)
                viewHolder = CatalogBalloonsTypeViewHolder(v6)
            }

            else -> {
                val v = inflater.inflate(R.layout.item_showcase_base_recyclerview, viewGroup, false)
                viewHolder = CatalogTypeViewHolder(v)
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val linearLayoutManager: LinearLayoutManager
        val gridLayoutManager: GridLayoutManager
        var NumberOfColumn = 1

        when (holder.itemViewType) {

            HorizontalShowCaseViewtype   //title visible or hide ok.
            -> {
                NumberOfColumn = if (list[position].column == 0) 1 else list[position].column
                val ratio = list[position].ratio
                val bannersAdapter = BannersAdapter(
                    list[position].options
                        ?: ArrayList(), NumberOfColumn, ratio
                )
                val horizontalTypeViewHolder = holder as IndexAdapter.HorizontalTypeViewHolder
                if (list[position].is_header_visible && !list[position].name!!.isEmpty()) {
                    horizontalTypeViewHolder.title.visibility = View.VISIBLE
                    horizontalTypeViewHolder.title.text = list[position].name
                } else {
                    horizontalTypeViewHolder.title.visibility = View.GONE
                }
                val params = FrameLayout.LayoutParams(
                    getWidthHeightWithColumn(
                        ratio * NumberOfColumn,
                        1
                    )!![0], getWidthHeightWithColumn(ratio * NumberOfColumn, 1)!![1]
                )
                horizontalTypeViewHolder.viewPager.layoutParams = params
                horizontalTypeViewHolder.viewPager.adapter = bannersAdapter
                horizontalTypeViewHolder.circlePageIndicator.setViewPager(horizontalTypeViewHolder.viewPager)
                horizontalTypeViewHolder.circlePageIndicator.visibility =
                    if (horizontalTypeViewHolder.viewPager.adapter?.count ?: 0 > 1) View.VISIBLE else View.GONE

                if (Config.bannerSlideTime != 0) {  // belirtilen index ise kaydırmayı aktifleştir.
                    if (runnable == null) {
                        runnable = object : Runnable {
                            override fun run() {
                                var pos = horizontalTypeViewHolder.viewPager.currentItem
                                if (pos == horizontalTypeViewHolder.viewPager.adapter?.count ?: 0 - 1)
                                // burası önemli. sayıyı adapterden alıyoruz.
                                    pos = 0
                                else
                                    pos++
                                horizontalTypeViewHolder.viewPager.currentItem = pos
//                                ProductShowcaseActivity.handler!!.postDelayed(
//                                    this,
//                                    (Config.bannerSlideTime * 1000).toLong()
//                                )
                            }
                        }

//                        ProductShowcaseActivity.handler!!.postDelayed(
//                            runnable,
//                            (CustomerValues.bannerSlideTime * 1000).toLong()
//                        )
//                        horizontalTypeViewHolder.viewPager.setOnTouchListener { v, event ->
//                            val action = event.action
//                            when (action) {
//                                MotionEvent.ACTION_MOVE -> {
//                                    ProductShowcaseActivity.handler!!.removeCallbacks(runnable)
//                                }
//                                MotionEvent.ACTION_UP -> {
//                                    ProductShowcaseActivity.handler!!.postDelayed(
//                                        runnable,
//                                        (CustomerValues.bannerSlideTime * 1000).toLong()
//                                    )
//                                }
//                            }
//                            false
//                        }
                    }
                }
            }
            VerticalShowCaseViewtype   //title visible or hide ok.
            -> {
                NumberOfColumn = if (list[position].column == 0) 1 else list[position].column
                val ratio3 =
                    list[position].ratio  // alt adapterdeki ekran genişlik yüksekliği ayarlanıyor.
                val verticalBannerAdapter = VerticalBannerAdapter(
                    list[position].options as ArrayList<IndexItem.OptionsBean>,
                    getWidthHeightWithColumn(ratio3, NumberOfColumn) ?: intArrayOf()
                )
                val verticalTypeViewHolder = holder as IndexAdapter.VerticalTypeViewHolder
                if (list[position].is_header_visible && !list[position].name!!.isEmpty()) {
                    verticalTypeViewHolder.title.visibility = View.VISIBLE
                    verticalTypeViewHolder.title.text = list[position].name
                } else {
                    verticalTypeViewHolder.title.visibility = View.GONE
                }

                gridLayoutManager = GridLayoutManager(mContext, NumberOfColumn)
                verticalTypeViewHolder.recyclerView.setHasFixedSize(true)
                verticalTypeViewHolder.recyclerView.layoutManager = gridLayoutManager
                verticalTypeViewHolder.recyclerView.adapter = verticalBannerAdapter
            }

            CatalogShowCaseViewtype //title visible or hide ok.
            -> {
                NumberOfColumn = if (list[position].column == 0) 1 else list[position].column
                val catalogTypeViewHolder = holder as CatalogTypeViewHolder
                if (list[position].is_header_visible && !list[position].name!!.isEmpty()) {
                    catalogTypeViewHolder.title.visibility = View.VISIBLE
                    catalogTypeViewHolder.title.text = list[position].name
                } else {
                    catalogTypeViewHolder.title.visibility = View.GONE
                }
                val showCaseCatalogAdapter = CatalogAdapter(
                    list[position].options as ArrayList<IndexItem.OptionsBean>,
                    NumberOfColumn
                )
                linearLayoutManager = LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false)
                catalogTypeViewHolder.recyclerView.setHasFixedSize(true)
                catalogTypeViewHolder.recyclerView.layoutManager = linearLayoutManager
                catalogTypeViewHolder.recyclerView.adapter = showCaseCatalogAdapter

            }

            ListShowCaseViewtype  //title visible or hide ok.
            -> {
                NumberOfColumn = if (list[position].column == 0) 1 else list[position].column
                val listAdapter = ListAdapter(
                    list[position].options as ArrayList<IndexItem.OptionsBean>
                )
                val listTypeViewHolder = holder as IndexAdapter.ListTypeViewHolder
                if (list[position].is_header_visible && !list[position].name!!.isEmpty()) {
                    listTypeViewHolder.title.visibility = View.VISIBLE
                    listTypeViewHolder.title.text = list[position].name
                } else {
                    listTypeViewHolder.title.visibility = View.GONE
                }
                gridLayoutManager = GridLayoutManager(mContext, NumberOfColumn)
                listTypeViewHolder.recyclerView.setHasFixedSize(true)
                listTypeViewHolder.recyclerView.layoutManager = gridLayoutManager
                listTypeViewHolder.recyclerView.adapter = listAdapter
            }

            SocialMediaShowCaseViewtype // tamamlandı
            -> {
                val socialMediaAdapter =
                    SocialMediaAdapter(list[position].options ?: arrayListOf())
                val socialMediaTypeViewHolder = holder as IndexAdapter.SocialMediaTypeViewHolder
                if (!list[position].name!!.isEmpty() && list[position].is_header_visible) {
                    socialMediaTypeViewHolder.title.visibility = View.VISIBLE
                    socialMediaTypeViewHolder.title.text = list[position].name
                } else {
                    socialMediaTypeViewHolder.title.visibility = View.GONE
                }
                linearLayoutManager =
                    object : LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false) {
                        override fun canScrollHorizontally(): Boolean {
                            return false
                        }
                    }
                socialMediaTypeViewHolder.recyclerView.setHasFixedSize(true)
                socialMediaTypeViewHolder.recyclerView.layoutManager = linearLayoutManager
                socialMediaTypeViewHolder.recyclerView.adapter = socialMediaAdapter
//                socialMediaTypeViewHolder.recyclerviewContainer.setBackgroundColor(
//                    getSocialMediaBackgroundColor()
//                )
            }

            CatalogBalloonsCaseViewtype -> {
                val itemOfPosition = list[position]
                val catalogBalloonsViewHolder = holder as IndexAdapter.CatalogBalloonsTypeViewHolder
                if (itemOfPosition.name?.isNotEmpty() == true && itemOfPosition.is_header_visible) {
                    catalogBalloonsViewHolder.title.visibility = View.VISIBLE
                    catalogBalloonsViewHolder.title.text = itemOfPosition.name
                } else
                    catalogBalloonsViewHolder.title.visibility = View.GONE
                val newShowCaseCatalogBalloonsAdapter = CatalogBalloonsAdapter(
                    itemOfPosition.options
                        ?: arrayListOf()
                )
                linearLayoutManager = LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false)
                catalogBalloonsViewHolder.recyclerView.setHasFixedSize(true)
                catalogBalloonsViewHolder.recyclerView.layoutManager = linearLayoutManager
                catalogBalloonsViewHolder.recyclerView.adapter = newShowCaseCatalogBalloonsAdapter
                //  val controller: LayoutAnimationController = AnimationUtils.loadLayoutAnimation(activity, R.anim.layout_animation_slide_in_right)
                // catalogBalloonsViewHolder.recyclerView.layoutAnimation = controller
                //  catalogBalloonsViewHolder.recyclerView.scheduleLayoutAnimation()
                //  newShowCaseCatalogBalloonsAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    private inner class BannersAdapter(
        private val banner_list: List<IndexItem.OptionsBean>,
        NumberOfColumn: Int,
        ratio: Double
    ) : PagerAdapter() {
        private var NumberOfColumn = 1
        private var ratio = 3.0
        private val main_count = 0

        private val total_page_count: Int
        internal lateinit var adapter: HorizontalBannerAdapter

        init {
            this.NumberOfColumn = NumberOfColumn
            this.ratio = ratio
            total_page_count =
                if (banner_list.size % NumberOfColumn == 0) banner_list.size / NumberOfColumn else banner_list.size / NumberOfColumn + 1  // tam bölünmüyorsa 1 fazlasını alacak.
        }

        private fun getCurrentList(position: Int): List<IndexItem.OptionsBean> { //done
            val currentList = ArrayList<IndexItem.OptionsBean>()
            for (i in 0 until NumberOfColumn) {
                if (position * NumberOfColumn + i < banner_list.size)
                    currentList.add(banner_list[position * NumberOfColumn + i])
            }
            return currentList
        }

        override fun getCount(): Int {
            return total_page_count
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object` as RelativeLayout
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val inflater =
                mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val itemView = inflater.inflate(R.layout.item_viewpager_recyclerview, container, false)
            val recyclerView = itemView.findViewById<View>(R.id.recyclerview) as RecyclerView

            adapter = HorizontalBannerAdapter(
                getCurrentList(position),
                ratio,
                NumberOfColumn
            )
            val layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = adapter

            (container as ViewPager).addView(itemView)
            return itemView
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            (container as ViewPager).removeView(`object` as RelativeLayout)
        }
    }

    companion object {
        private val VerticalShowCaseViewtype = 1
        private val HorizontalShowCaseViewtype = 2
        private val CatalogShowCaseViewtype = 3
        private val ListShowCaseViewtype = 4
        private val SocialMediaShowCaseViewtype = 5
        private val CatalogBalloonsCaseViewtype = 6
        var runnable: Runnable? = null
    }


}