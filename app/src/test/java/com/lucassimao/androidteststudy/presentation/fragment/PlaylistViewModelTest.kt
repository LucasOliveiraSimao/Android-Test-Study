package com.lucassimao.androidteststudy.presentation.fragment

import com.lucassimao.androidteststudy.domain.entity.PlaylistDetail
import com.lucassimao.androidteststudy.domain.entity.PlaylistModel
import com.lucassimao.androidteststudy.domain.repository.PlaylistRepository
import com.lucassimao.androidteststudy.utils.BaseUnitTest
import com.lucassimao.androidteststudy.utils.captureValues
import com.lucassimao.androidteststudy.utils.getValueForTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class PlaylistViewModelTest : BaseUnitTest() {

    private val repository: PlaylistRepository = mock()
    private val playlist = mock<List<PlaylistModel>>()
    private val playlistDetail = mock<PlaylistDetail>()
    private val exception = RuntimeException("Something wrong")

    @Test
    fun shouldFetchPlaylist_fromRepository(): Unit = runBlocking {
        val viewModel = mockSuccessfulCase()

        viewModel.playlist.getValueForTest()

        verify(repository, times(1)).fetchPlaylist()
    }

    @Test
    fun shouldEmitsPlaylist_fromRepository(): Unit = runBlocking {
        val viewModel = mockSuccessfulCase()

        assertEquals(Result.success(playlist), viewModel.playlist.getValueForTest())
    }

    @Test
    fun shouldEmitError_whenReceiveError() {
        val viewModel = mockErrorCase()

        assertEquals(exception, viewModel.playlist.getValueForTest()!!.exceptionOrNull())
    }

    @Test
    fun shouldShowSpinner_whileLoading() = runBlocking {
        val viewModel = mockSuccessfulCase()

        viewModel.loader.captureValues {
            viewModel.playlist.getValueForTest()

            assertEquals(true, values[0])
        }
    }

    @Test
    fun shouldCloseLoader_afterPlaylistLoad() = runBlocking {
        val viewModel = mockSuccessfulCase()

        viewModel.loader.captureValues {
            viewModel.playlist.getValueForTest()

            assertEquals(false, values.last())
        }
    }

    @Test
    fun shouldCloseLoader_afterError() = runBlocking {
        val viewModel = mockErrorCase()

        viewModel.loader.captureValues {
            viewModel.playlist.getValueForTest()

            assertEquals(false, values.last())
        }
    }

    @Test
    fun shouldGetPlaylistDetail_fromRepository(): Unit = runBlocking {
        val viewModel = mockSuccessfulCasePlaylistDetail()

        viewModel.getPlaylistDetail.getValueForTest()

        verify(repository, times(1)).getPlaylistDetail()
    }

    @Test
    fun shouldEmitsPlaylistDetail_fromRepository(): Unit = runBlocking {
        val viewModel = mockSuccessfulCasePlaylistDetail()

        assertEquals(Result.success(playlistDetail), viewModel.getPlaylistDetail.getValueForTest())
    }

    @Test
    fun shouldEmitError_whenReceiveErrorFromPlaylistDetail() {
        val viewModel = mockErrorCasePlaylistDetail()

        assertEquals(exception, viewModel.getPlaylistDetail.getValueForTest()!!.exceptionOrNull())
    }

    @Test
    fun shouldShowSpinner_whileLoadingPlaylistDetail() = runBlocking {
        val viewModel = mockSuccessfulCasePlaylistDetail()

        viewModel.loader.captureValues {
            viewModel.getPlaylistDetail.getValueForTest()

            assertEquals(true, values[0])
        }
    }

    @Test
    fun shouldCloseLoader_afterPlaylistDetailLoad() = runBlocking {
        val viewModel = mockSuccessfulCasePlaylistDetail()

        viewModel.loader.captureValues {
            viewModel.getPlaylistDetail.getValueForTest()

            assertEquals(false, values.last())
        }
    }

    @Test
    fun shouldCloseLoader_afterErrorFromPlaylistDetail() = runBlocking {
        val viewModel = mockErrorCasePlaylistDetail()

        viewModel.loader.captureValues {
            viewModel.getPlaylistDetail.getValueForTest()

            assertEquals(false, values.last())
        }
    }

    private fun mockErrorCasePlaylistDetail(): PlaylistViewModel {
        runBlocking {
            whenever(repository.getPlaylistDetail()).thenReturn(
                flow {
                    emit(Result.failure(exception))
                }
            )
        }
        return PlaylistViewModel(repository)
    }

    private fun mockErrorCase(): PlaylistViewModel {
        runBlocking {
            whenever(repository.fetchPlaylist()).thenReturn(
                flow {
                    emit(Result.failure(exception))
                }
            )
        }
        return PlaylistViewModel(repository)
    }

    private fun mockSuccessfulCasePlaylistDetail(): PlaylistViewModel {
        runBlocking {
            whenever(repository.getPlaylistDetail()).thenReturn(
                flow {
                    emit(Result.success(playlistDetail))
                }
            )
        }
        return PlaylistViewModel(repository)
    }

    private fun mockSuccessfulCase(): PlaylistViewModel {
        runBlocking {
            whenever(repository.fetchPlaylist()).thenReturn(
                flow {
                    emit(Result.success(playlist))
                }
            )
        }
        return PlaylistViewModel(repository)
    }

}