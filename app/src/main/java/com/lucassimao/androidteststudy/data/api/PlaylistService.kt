package com.lucassimao.androidteststudy.data.api

import com.lucassimao.androidteststudy.domain.entity.PlaylistDetail
import com.lucassimao.androidteststudy.domain.entity.PlaylistRaw
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlaylistService @Inject constructor(private val api: PlaylistAPI) {

    suspend fun fetchPlaylists(): Flow<Result<List<PlaylistRaw>>> {
        return flow { emit(Result.success(api.fetchPlaylist())) }
            .catch { emit(Result.failure(RuntimeException("Wrong"))) }
    }

    suspend fun getPlaylistDetails(): Flow<Result<PlaylistDetail>> {
        return flow { emit(Result.success(api.getPlaylistDetail())) }
            .catch { emit(Result.failure(RuntimeException("Wrong"))) }
    }

}