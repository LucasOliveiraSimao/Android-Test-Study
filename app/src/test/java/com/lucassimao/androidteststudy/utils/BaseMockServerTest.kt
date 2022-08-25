package com.lucassimao.androidteststudy.utils

import com.lucassimao.androidteststudy.data.api.PlaylistAPI
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source

open class BaseMockServerTest {

    suspend fun fetchPlaylist(api: PlaylistAPI) {
        api.fetchPlaylist()
    }

    suspend fun getPlaylistDetails(api: PlaylistAPI) {
        api.getPlaylistDetail()
    }

    fun enqueueMockResponse(server: MockWebServer, fileName: String) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)
    }

}