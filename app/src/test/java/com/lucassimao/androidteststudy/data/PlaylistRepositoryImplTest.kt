package com.lucassimao.androidteststudy.data

import com.lucassimao.androidteststudy.data.api.PlaylistService
import com.lucassimao.androidteststudy.data.mapper.PlaylistMapper
import com.lucassimao.androidteststudy.domain.entity.PlaylistDetail
import com.lucassimao.androidteststudy.domain.entity.PlaylistModel
import com.lucassimao.androidteststudy.domain.entity.PlaylistRaw
import com.lucassimao.androidteststudy.domain.repository.PlaylistRepository
import com.lucassimao.androidteststudy.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class PlaylistRepositoryImplTest : BaseUnitTest() {

    private val service: PlaylistService = mock()
    private val mapper: PlaylistMapper = mock()
    private val playlists = mock<List<PlaylistModel>>()
    private val playlistsRaw = mock<List<PlaylistRaw>>()
    private val playlistDetail = mock<PlaylistDetail>()
    private val exception = RuntimeException("Something wrong")

    @Test
    fun shouldFetchPlaylist_fromService(): Unit = runBlocking {
        val repository = PlaylistRepositoryImpl(service, mapper)

        repository.fetchPlaylist()

        verify(service, times(1)).fetchPlaylists()
    }

    @Test
    fun shouldEmitMappedPlaylist_fromService(): Unit = runBlocking {
        val repository = mockSuccessfulCase()

        assertEquals(playlists, repository.fetchPlaylist().first().getOrNull())
    }

    @Test
    fun shouldEmitPlaylistDetail_fromService(): Unit = runBlocking {
        val repository = mockSuccessfulCasePlaylistDetail()

        assertEquals(playlistDetail, repository.getPlaylistDetail().first().getOrNull())
    }

    @Test
    fun shouldPropagateErrors(): Unit = runBlocking {
        val repository = mockFailureCase()

        assertEquals(exception, repository.fetchPlaylist().first().exceptionOrNull())
    }

    @Test
    fun shouldDelegateBusinessLogic_toMapper(): Unit = runBlocking {
        val repository = mockSuccessfulCase()

        repository.fetchPlaylist().first()

        verify(mapper, times(1)).invoke(playlistsRaw)
    }

    @Test
    fun shouldGetPlaylistDetail_fromService(): Unit = runBlocking {
        val repository = PlaylistRepositoryImpl(service, mapper)

        repository.getPlaylistDetail()

        verify(service, times(1)).getPlaylistDetails()
    }

    @Test
    fun shouldPropagateErrors_FromPlaylistDetail(): Unit = runBlocking {
        val repository = mockFailureCasePlaylistDetail()

        assertEquals(exception, repository.getPlaylistDetail().first().exceptionOrNull())
    }

    private fun mockFailureCasePlaylistDetail(): PlaylistRepository {
        runBlocking {
            whenever(service.getPlaylistDetails()).thenReturn(
                flow {
                    emit(Result.failure(exception))
                }
            )
        }
        return PlaylistRepositoryImpl(service,mapper)
    }

    private fun mockFailureCase(): PlaylistRepository {
        runBlocking {
            whenever(service.fetchPlaylists()).thenReturn(
                flow {
                    emit(Result.failure(exception))
                }
            )
        }
        return PlaylistRepositoryImpl(service,mapper)
    }

    private fun mockSuccessfulCasePlaylistDetail(): PlaylistRepository {
        runBlocking {
            whenever(service.getPlaylistDetails()).thenReturn(
                flow {
                    emit(Result.success(playlistDetail))
                }
            )
        }

        whenever(mapper.invoke(playlistsRaw)).thenReturn(playlists)

        return PlaylistRepositoryImpl(service, mapper)
    }

    private fun mockSuccessfulCase(): PlaylistRepository {
        runBlocking {
            whenever(service.fetchPlaylists()).thenReturn(
                flow {
                    emit(Result.success(playlistsRaw))
                }
            )
        }

        whenever(mapper.invoke(playlistsRaw)).thenReturn(playlists)

        return PlaylistRepositoryImpl(service, mapper)
    }
}