package com.lucassimao.androidteststudy.data

import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.IOException
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PlaylistAPITest {

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
        enqueueMockResponse("playlists.json")
        val response = api.fetchPlaylist().body()?.size
        val expected = 0

        assertNotEquals(expected, response)
    }

    @Test
    fun shouldReceivedPath_whenSendRequestFetchPlaylist() = runBlocking {
        enqueueMockResponse("playlists.json")
        fetchPlaylist()
        val response = server.takeRequest().path
        val expected = "/playlists.json"

        assertEquals(expected, response)
    }

    @Test
    fun shouldReceivedError_whenSendRequestFetchPlaylist() = runBlocking {
        enqueueMockResponse("playlists.json")
        fetchPlaylist()
        val response = server.takeRequest().failure
        val expected = IOException().message

        assertEquals(expected, response)
    }

    private suspend fun fetchPlaylist() {
        api.fetchPlaylist()
    }

    private fun enqueueMockResponse(fileName: String) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)
    }
}