package com.tsoftmobile.library.index.adapter

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.tsoftmobile.library.index.R
import com.tsoftmobile.library.index.TSoftApplication
import com.tsoftmobile.library.index.adapter.SocialMediaAdapter.SocialMediaType.*
import com.tsoftmobile.library.index.model.IndexItemType
import com.tsoftmobile.library.index.model.data.IndexItem
import com.tsoftmobile.library.index.util.encodeTurkishCharactersInUrl
import com.tsoftmobile.library.index.util.getWidthHeightWithColumn
import com.tsoftmobile.library.index.util.isInteger
import com.tsoftmobile.library.index.util.loadUrl

class SocialMediaAdapter(
    items: ArrayList<IndexItem.OptionsBean>
) :
    RecyclerView.Adapter<SocialMediaAdapter.ViewHolder?>() {
    private var items: List<IndexItem.OptionsBean> = ArrayList<IndexItem.OptionsBean>()
    private val TAG = "SocialMediaAdapter"
    private var params: RelativeLayout.LayoutParams

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        // each data MyItem is just a string in this case
        var image: ImageView

        init {
            image = v.findViewById<View>(R.id.social_media_image) as ImageView
            image.setLayoutParams(params)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder { // create a new view
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_showcase_socialmedia, parent, false)
        // set the view's size, margins, paddings and layout parameters
        v.layoutParams = params
        return ViewHolder(v)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) { // - get element from your dataset at this position
// - replace the contents of the view with that element
        if (items[position].platform != null) {
            if (items[position].icon != null) {
                val url: String = items[position].icon.encodeTurkishCharactersInUrl() ?: ""
                holder.image.loadUrl(url)
            } else holder.image.setImageResource(items[position].platform?.socialMediaIcon!!)
        }
        holder.image.setOnClickListener(View.OnClickListener {
            when (items[position].platform) {
                facebook -> {
                    TSoftApplication.rxBus.send(
                        TSoftApplication.RxEvents.onItemClick(
                            IndexItemType.FACEBOOK,
                            items[position]
                        )
                    )
                }
                google_plus -> {

                }
                instagram -> {
                    TSoftApplication.rxBus.send(
                        TSoftApplication.RxEvents.onItemClick(
                            IndexItemType.INSTAGRAM,
                            items[position]
                        )
                    )

                }
                pinterest -> {

                }
                twitter -> {
                    TSoftApplication.rxBus.send(
                        TSoftApplication.RxEvents.onItemClick(
                            IndexItemType.TWITTER,
                            items[position]
                        )
                    )
                }
                youtube -> {
                    TSoftApplication.rxBus.send(
                        TSoftApplication.RxEvents.onItemClick(
                            IndexItemType.YOUTUBE,
                            items[position]
                        )
                    )
                }
                tumblr -> {

                }
                linkedin -> {
                    TSoftApplication.rxBus.send(
                        TSoftApplication.RxEvents.onItemClick(
                            IndexItemType.LINKEDIN,
                            items[position]
                        )
                    )
                }
            }
        })
    }


    override fun getItemCount(): Int {
        return items.size
    }

    enum class SocialMediaType(val socialMediaIcon: Int) {
        facebook(R.drawable.ic_facebook), google_plus(R.drawable.ic_google_plus), instagram(R.drawable.ic_instagram), pinterest(
            R.drawable.ic_pinterest
        ),
        twitter(R.drawable.ic_twitter), youtube(R.drawable.ic_youtube), tumblr(R.drawable.ic_tumblr), whatsapp(
            R.drawable.ic_whatsapp
        ),
        skype(R.drawable.ic_skype), snapchat(R.drawable.ic_snapchat), linkedin(R.drawable.ic_linkedin);

    }

    init {
        this.items = items
        params = RelativeLayout.LayoutParams(
            getWidthHeightWithColumn(1.0, 10).get(0),
            getWidthHeightWithColumn(1.0, 10).get(1)
        )
    }
}
