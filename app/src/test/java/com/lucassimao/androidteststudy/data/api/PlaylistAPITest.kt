package com.lucassimao.androidteststudy.data.api

import com.lucassimao.androidteststudy.utils.BaseMockServerTest
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import okio.IOException
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PlaylistAPITest : BaseMockServerTest() {

    private lateinit var api: PlaylistAPI
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        api = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PlaylistAPI::class.java)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun shouldReceivedPlaylist_whenSendRequestFetchPlaylist() = runBlocking {
        enqueueMockResponse(server, "playlist.json")
        val response = api.fetchPlaylist().body()?.size
        val expected = 0

        assertNotEquals(expected, response)
    }

    @Test
    fun shouldReceivedPath_whenSendRequestFetchPlaylist() = runBlocking {
        enqueueMockResponse(server, "playlist.json")
        fetchPlaylist(api)
        val response = server.takeRequest().path
        val expected = "/playlist.json"

        assertEquals(expected, response)
    }

    @Test
    fun shouldReceivedError_whenSendRequestFetchPlaylist() = runBlocking {
        enqueueMockResponse(server, "playlist.json")
        fetchPlaylist(api)
        val response = server.takeRequest().failure
        val expected = IOException().message

        assertEquals(expected, response)
    }

    @Test
    fun shouldReceivedPlaylistDetails_whenSendRequestGetPlaylistDetail() = runBlocking {
        enqueueMockResponse(server, "playlist_detail.json")
        val response = api.getPlaylistDetail().body()
        val expected = null

        assertNotEquals(expected, response)
    }

    @Test
    fun shouldReceivedPath_whenSendRequestGetPlaylistDetail() = runBlocking {
        enqueueMockResponse(server, "playlist_detail.json")
        getPlaylistDetails(api)
        val response = server.takeRequest().path
        val expected = "/playlist_detail.json"

        assertEquals(expected, response)
    }

    @Test
    fun shouldReceivedError_whenSendRequestGetPlaylistDetail() = runBlocking {
        enqueueMockResponse(server, "playlist_detail.json")
        getPlaylistDetails(api)
        val response = server.takeRequest().failure
        val expected = IOException().message

        assertEquals(expected, response)
    }

}