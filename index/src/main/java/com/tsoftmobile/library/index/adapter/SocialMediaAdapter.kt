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
                    val facebookIntent = Intent(Intent.ACTION_VIEW)
                    val _word: String = items[position].type_id
                    val _id: String = items[position].platform_id ?: ""
                    var url = ""
                    url = if (_id.isEmpty() || _id == " ") _word else _id
                    val facebookUrl = openFacebook(url)
                    facebookIntent.data = Uri.parse(facebookUrl)
                    TSoftApplication.applicationContext().startActivity(facebookIntent)
                }
                google_plus -> {
                    var google_plus_page_name: String = items[position].type_id
                    if (google_plus_page_name.substring(
                            0,
                            1
                        ) != "+"
                    ) // eğer başına + eklenmemişse
                        google_plus_page_name = "+$google_plus_page_name"
                    openGPlus(google_plus_page_name)
                }
                instagram -> {
                    val instagram_page_name: String = items[position].type_id
                    openInstagram(instagram_page_name)
                }
                pinterest -> openPinterest(items[position].type_id)
                twitter -> openTwitter(items[position].type_id)
                youtube -> openYoutube(items[position].type_id)
                tumblr -> openTumblr(items[position].type_id)
                linkedin -> openLinkedin(items[position].type_id)
            }
        })
    }

    private fun openFacebook(PageName: String): String { // facebook sayfası açmak için.
        val FACEBOOK_URL = "https://www.facebook.com/$PageName"
        val packageManager = TSoftApplication.applicationContext().packageManager
        return try {
            val versionCode =
                packageManager.getPackageInfo("com.facebook.katana", 0).versionCode
            if (versionCode >= 3002850) { //newer versions of fb app
                if (PageName.isInteger()) { // id kontrolu yapıp bakılacak.string ise facewebmodal ile aç.
                    "fb://page/$PageName"
                } else "fb://facewebmodal/f?href=$FACEBOOK_URL"
            } else { //older versions of fb app
                "fb://page/$PageName"
            }
        } catch (e: PackageManager.NameNotFoundException) {
            FACEBOOK_URL //normal web url
        }
    }

    private fun openGPlus(profile: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            val packageManager = TSoftApplication.applicationContext().packageManager
            try {
                if (packageManager.getPackageInfo("com.google.android.apps.plus", 0) != null) {
                    intent.setPackage("com.google.android.apps.plus")
                    intent.data = Uri.parse("https://plus.google.com/$profile")
                }
            } catch (e: PackageManager.NameNotFoundException) { // paket yok ise class name e göre ata ve aç.
                intent.setClassName(
                    "com.google.android.apps.plus",
                    "com.google.android.apps.plus.phone.UrlGatewayActivity"
                )
                intent.putExtra("customAppUri", profile)
                e.printStackTrace()
            }
            TSoftApplication.applicationContext().startActivity(intent)
        } catch (e: ActivityNotFoundException) { // class ta yoksa webde aç.
            TSoftApplication.applicationContext().startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://plus.google.com/$profile/posts")
                )
            )
        }
    }

    private fun openInstagram(pageName: String) {
        val uri = Uri.parse("http://instagram.com/_u/$pageName")
        val instaIntent = Intent(Intent.ACTION_VIEW, uri)
        instaIntent.setPackage("com.instagram.android")
        try {
            TSoftApplication.applicationContext().startActivity(instaIntent)
        } catch (e: ActivityNotFoundException) {
            TSoftApplication.applicationContext().startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://instagram.com/$pageName")
                )
            )
        }
    }

    private fun openPinterest(pageName: String) {
        try {
            TSoftApplication.applicationContext().startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("pinterest://www.pinterest.com/$pageName")
                )
            )
        } catch (e: Exception) {
            TSoftApplication.applicationContext().startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.pinterest.com/$pageName")
                )
            )
        }
    }

    private fun openTwitter(pageName: String) {
        try {
            TSoftApplication.applicationContext().startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("twitter://user?screen_name=$pageName")
                )
            )
        } catch (e: Exception) {
            TSoftApplication.applicationContext().startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://twitter.com/#!/$pageName")
                )
            )
        }
    }

    private fun openYoutube(pageName: String) {
        var intent: Intent? = null
        try {
            intent = Intent(Intent.ACTION_VIEW)
            intent.setPackage("com.google.android.youtube")
            intent.data = Uri.parse("https://www.youtube.com/$pageName")
            TSoftApplication.applicationContext().startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.youtube.com/$pageName")
            TSoftApplication.applicationContext().startActivity(intent)
        }
    }

    private fun openTumblr(pageName: String) {
        try {
            TSoftApplication.applicationContext().startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("tumblr://x-callback-url/blog?blogName=$pageName")
                )
            )
        } catch (e: Exception) {
            TSoftApplication.applicationContext().startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://$pageName.tumblr.com/")
                )
            )
        }
    }

    private fun openLinkedin(pageName: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setPackage("com.linkedin.android")
            intent.data = Uri.parse("https://www.linkedin.com/$pageName")
            TSoftApplication.applicationContext().startActivity(intent)
        } catch (e: Exception) {
            TSoftApplication.applicationContext().startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.linkedin.com/$pageName")
                )
            )
        }
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
