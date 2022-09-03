package com.lucassimao.androidteststudy.data

import com.lucassimao.androidteststudy.data.api.PlaylistService
import com.lucassimao.androidteststudy.data.mapper.PlaylistMapper
import com.lucassimao.androidteststudy.domain.entity.PlaylistDetail
import com.lucassimao.androidteststudy.domain.entity.PlaylistModel
import com.lucassimao.androidteststudy.domain.repository.PlaylistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PlaylistRepositoryImpl @Inject constructor(
    private val service: PlaylistService,
    private val mapper: PlaylistMapper
) : PlaylistRepository {

    override suspend fun fetchPlaylist(): Flow<Result<List<PlaylistModel>>> {
        return service.fetchPlaylists().map {
            if (it.isSuccess)
                Result.success(mapper(it.getOrNull()!!))
            else
                Result.failure(it.exceptionOrNull()!!)

        }
    }

    override suspend fun getPlaylistDetail(): Flow<Result<PlaylistDetail>> {
        return service.getPlaylistDetails().map {
            if (it.isSuccess)
                Result.success(it.getOrNull()!!)
            else
                Result.failure(it.exceptionOrNull()!!)
        }
    }

}