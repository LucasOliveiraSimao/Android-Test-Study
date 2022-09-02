package com.lucassimao.androidteststudy.data.api

import com.lucassimao.androidteststudy.domain.entity.PlaylistDetail
import com.lucassimao.androidteststudy.domain.entity.PlaylistRaw
import com.lucassimao.androidteststudy.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.times
import java.lang.RuntimeException

class PlaylistServiceTest : BaseUnitTest() {

    private lateinit var service: PlaylistService
    private val api: PlaylistAPI = mock()
    private val playlists: List<PlaylistRaw> = mock()
    private val playlistDetail: PlaylistDetail = mock()

    @Test
    fun shouldFetchPlaylists_FromApi(): Unit = runBlocking {
        service = PlaylistService(api)

        service.fetchPlaylists().first()

        verify(api, times(1)).fetchPlaylist()
    }

    @Test
    fun shouldConvertValues_forFlowResultAndEmitsThem(): Unit = runBlocking {
        mockSuccessCase()

        assertEquals(Result.success(playlists), service.fetchPlaylists().first())
    }

    @Test
    fun shouldEmitsErrorResult_whenNetworkFails(): Unit = runBlocking {
        mockErrorCase()

        assertEquals("Wrong",service.fetchPlaylists().first().exceptionOrNull()?.message)
    }

    @Test
    fun shouldGetPlaylistDetail_FromApi(): Unit = runBlocking {
        service = PlaylistService(api)

        service.getPlaylistDetails().first()

        verify(api, times(1)).getPlaylistDetail()
    }

    @Test
    fun shouldConvertValuesFromPlaylistDetail_forFlowResultAndEmitsThem(): Unit = runBlocking {
        mockPlaylistDetailSuccessCase()

        assertEquals(Result.success(playlistDetail), service.getPlaylistDetails().first())
    }

    @Test
    fun shouldEmitsErrorResultFromPlaylistDetail_whenNetworkFails(): Unit = runBlocking {
        mockPlaylistDetailErrorCase()

        assertEquals("Wrong",service.getPlaylistDetails().first().exceptionOrNull()?.message)
    }

    private fun mockPlaylistDetailErrorCase() = runBlocking{
        whenever(api.getPlaylistDetail()).thenThrow(RuntimeException("Wrong"))

        service = PlaylistService(api)
    }

    private fun mockErrorCase() = runBlocking {
        whenever(api.fetchPlaylist()).thenThrow(RuntimeException("Wrong"))

        service = PlaylistService(api)
    }

    private fun mockPlaylistDetailSuccessCase() = runBlocking {
        whenever(api.getPlaylistDetail()).thenReturn(playlistDetail)

        service = PlaylistService(api)
    }

    private fun mockSuccessCase() = runBlocking {
        whenever(api.fetchPlaylist()).thenReturn(playlists)

        service = PlaylistService(api)
    }
}