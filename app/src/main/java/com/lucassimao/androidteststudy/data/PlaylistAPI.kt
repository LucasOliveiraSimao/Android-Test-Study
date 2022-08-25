package com.lucassimao.androidteststudy.data

import com.lucassimao.androidteststudy.domain.PlaylistRaw
import retrofit2.Response
import retrofit2.http.GET

interface PlaylistAPI {
    @GET("playlists.json")
    suspend fun fetchPlaylist(): Response<List<PlaylistRaw>>
}
