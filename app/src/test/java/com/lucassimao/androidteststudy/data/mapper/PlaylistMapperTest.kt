package com.lucassimao.androidteststudy.data.mapper

import com.lucassimao.androidteststudy.R
import com.lucassimao.androidteststudy.domain.entity.PlaylistRaw
import com.lucassimao.androidteststudy.utils.BaseUnitTest
import org.junit.Assert.assertEquals
import org.junit.Test

class PlaylistMapperTest : BaseUnitTest() {

    private val mapper = PlaylistMapper()
    private val playlistRaw = PlaylistRaw("1", "name", "category")
    private val playlist = mapper(listOf(playlistRaw))[0]
    private val playlistRawRock = PlaylistRaw("1", "name", "rock")
    private val playlistRock = mapper(listOf(playlistRawRock))[0]

    @Test
    fun shouldKeepSameId() {
        assertEquals(playlistRaw.id, playlist.id)
    }

    @Test
    fun shouldKeepSameName() {
        assertEquals(playlistRaw.name, playlist.name)
    }

    @Test
    fun shouldKeepSameCategory() {
        assertEquals(playlistRaw.category, playlist.category)
    }

    @Test
    fun shouldMapDefaultImage_whenCategoryNotRock() {
        assertEquals(R.mipmap.playlist, playlist.image)
    }

    @Test
    fun shouldMapRockImage_whenCategoryIsRock() {
        assertEquals(R.mipmap.rock, playlistRock.image)
    }

}