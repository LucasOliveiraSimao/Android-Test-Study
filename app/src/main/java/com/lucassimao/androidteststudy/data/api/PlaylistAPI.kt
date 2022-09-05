package com.lucassimao.androidteststudy.data.api

import com.lucassimao.androidteststudy.domain.entity.PlaylistDetail
import com.lucassimao.androidteststudy.domain.entity.PlaylistRaw
import retrofit2.Response
import retrofit2.http.GET

interface PlaylistAPI {
    @GET("playlists.json")
    suspend fun fetchPlaylist(): List<PlaylistRaw>

    @GET("playlist_detail.json")
    suspend fun getPlaylistDetail(): PlaylistDetail
}