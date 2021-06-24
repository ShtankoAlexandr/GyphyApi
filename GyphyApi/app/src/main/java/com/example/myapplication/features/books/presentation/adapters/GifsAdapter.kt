package com.example.myapplication.features.books.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.R
import com.example.myapplication.features.books.domain.model.Gif


class GifsAdapter : RecyclerView.Adapter<GifsAdapter.ViewHolder>() {
    private val mList: MutableList<Gif> = mutableListOf()

    fun addItems(list: MutableList<Gif>) {
        mList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.gif_item, parent, false)
    )

    override fun getItemCount() = mList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(postVM: Gif) {

            Glide.with(itemView.context)
                .asGif()
                .load(postVM.images.original.url)
                .apply(
                    RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop())
                .transition(DrawableTransitionOptions.withCrossFade(250))
                .into(itemView.findViewById<ImageView>(R.id.ivGif))
        }
    }
}
