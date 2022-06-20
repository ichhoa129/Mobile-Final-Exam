package com.developerdepository.wallpaper.Common

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.developerdepository.wallpaper.R
import kotlinx.android.synthetic.main.list_single_wallpaper_item.view.*

public class WallpapersListAdapter(
    var wallpapersList: List<Wallpaper>,
    val clickListener: (Wallpaper) -> Unit
) : RecyclerView.Adapter<WallpapersListAdapter.WallpapersViewHolder>() {

    class WallpapersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(wallpapers: Wallpaper, clickListener: (Wallpaper) -> Unit) {
            //Load Image
            Glide.with(itemView.context).asBitmap().load(wallpapers.small).centerCrop().listener(
                object : RequestListener<Bitmap> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Bitmap?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        itemView.list_wallpaper_progress.hide()
                        return false
                    }

                }
            ).into(itemView.list_wallpaper_img)
            //Click Listener
            itemView.list_wallpaper_item.setOnClickListener {
                clickListener(wallpapers)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpapersViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_single_wallpaper_item, parent, false)
        return WallpapersViewHolder(view)
    }

    override fun getItemCount(): Int {
        return wallpapersList.size
    }

    override fun onBindViewHolder(holder: WallpapersViewHolder, position: Int) {
        (holder as WallpapersViewHolder).bind(wallpapersList[position], clickListener)
    }

}