package com.lucassimao.androidteststudy.presentation.fragment.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lucassimao.androidteststudy.databinding.PlaylistItemBinding
import com.lucassimao.androidteststudy.domain.entity.PlaylistModel

class PlaylistAdapter(
    private val onItemClick: (PlaylistModel) -> Unit
) : ListAdapter<PlaylistModel, PlaylistViewHolder>(PlaylistModel) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        return PlaylistViewHolder.from(parent, onItemClick)
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class PlaylistViewHolder(
    private val binding: PlaylistItemBinding,
    private val onItemClick: (PlaylistModel) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: PlaylistModel) {
        binding.apply {
            playlistName.text = item.name
            playlistCategory.text = item.category
            playlistImage.setImageResource(item.image)
            itemView.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    companion object {
        fun from(
            parent: ViewGroup,
            onItemClick: (PlaylistModel) -> Unit
        ): PlaylistViewHolder {
            return PlaylistViewHolder(
                PlaylistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                onItemClick
            )
        }
    }
}