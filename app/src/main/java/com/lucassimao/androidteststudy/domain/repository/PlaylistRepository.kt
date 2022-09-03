package com.lucassimao.androidteststudy.domain.repository

import com.lucassimao.androidteststudy.domain.entity.PlaylistDetail
import com.lucassimao.androidteststudy.domain.entity.PlaylistModel
import kotlinx.coroutines.flow.Flow

interface PlaylistRepository {
    suspend fun fetchPlaylist(): Flow<Result<List<PlaylistModel>>>
    suspend fun getPlaylistDetail(): Flow<Result<PlaylistDetail>>
}