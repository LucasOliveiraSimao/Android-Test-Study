package com.lucassimao.androidteststudy.presentation.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.lucassimao.androidteststudy.domain.repository.PlaylistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PlaylistViewModel @Inject constructor(
    private val repository: PlaylistRepository
) : ViewModel() {

    val loader = MutableLiveData<Boolean>()

    val playlist = liveData {
        loader.postValue(true)
        emitSource(repository.fetchPlaylist()
            .onEach { loader.postValue(false) }
            .asLiveData())
    }

    val getPlaylistDetail = liveData {
        loader.postValue(true)
        emitSource(repository.getPlaylistDetail()
            .onEach { loader.postValue(false) }
            .asLiveData())
    }

}