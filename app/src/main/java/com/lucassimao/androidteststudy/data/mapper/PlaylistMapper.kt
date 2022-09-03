package com.lucassimao.androidteststudy.data.mapper

import com.lucassimao.androidteststudy.R
import com.lucassimao.androidteststudy.domain.entity.PlaylistModel
import com.lucassimao.androidteststudy.domain.entity.PlaylistRaw
import javax.inject.Inject

class PlaylistMapper @Inject constructor() : Function1<List<PlaylistRaw>, List<PlaylistModel>> {

    override fun invoke(playlistRaw: List<PlaylistRaw>): List<PlaylistModel> {
        return playlistRaw.map {

            val image = when (it.category) {
                "rock" -> R.mipmap.rock
                else -> R.mipmap.playlist
            }

            PlaylistModel(it.id, it.name, it.category, image)
        }
    }

}