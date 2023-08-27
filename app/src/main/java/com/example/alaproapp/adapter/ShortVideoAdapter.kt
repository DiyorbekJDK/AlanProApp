package com.example.alaproapp.adapter

import android.graphics.drawable.Drawable

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.alaproapp.databinding.FullVideoItemLayoutBinding
import com.example.alaproapp.databinding.ShortVideoLayoutBinding
import com.example.alaproapp.model.Item

class ShortVideoAdapter : ListAdapter<Item, ShortVideoAdapter.YoutubeViewHolder>(DiffCallBack()) {
    lateinit var onClick: (Item) -> Unit

    private class DiffCallBack : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YoutubeViewHolder {
        return YoutubeViewHolder(
            ShortVideoLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: YoutubeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class YoutubeViewHolder(private val binding: ShortVideoLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            with(binding) {
                Glide.with(shortVideoPrevu)
                    .load(item.snippet.thumbnails.high.url)
                    .addListener(listener {
                        binding.progressBar2.isVisible = false
                    })
                    .into(shortVideoPrevu)
                videoName.text = item.snippet.title
                dateText.text =
                    "${item.snippet.channelTitle}"
            }
            itemView.setOnClickListener {
                onClick(item)
            }
        }
    }

    private fun listener(onSuccess: () -> Unit) = object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            Log.d("@@@", e?.message.toString())
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            onSuccess()
            return false
        }
    }
}