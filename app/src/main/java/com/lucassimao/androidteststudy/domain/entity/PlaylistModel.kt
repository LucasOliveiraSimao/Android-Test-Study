package com.lucassimao.androidteststudy.domain.entity

import androidx.recyclerview.widget.DiffUtil

data class PlaylistModel(
    val id: String,
    val name: String,
    val category: String,
    val image: Int
) {
    companion object : DiffUtil.ItemCallback<PlaylistModel>() {
        override fun areItemsTheSame(oldItem: PlaylistModel, newItem: PlaylistModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PlaylistModel, newItem: PlaylistModel): Boolean {
            return oldItem == newItem
        }
    }
}