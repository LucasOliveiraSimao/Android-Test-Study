package com.lucassimao.androidteststudy.presentation.di

import com.lucassimao.androidteststudy.data.PlaylistRepositoryImpl
import com.lucassimao.androidteststudy.data.api.PlaylistAPI
import com.lucassimao.androidteststudy.data.api.PlaylistService
import com.lucassimao.androidteststudy.data.mapper.PlaylistMapper
import com.lucassimao.androidteststudy.domain.repository.PlaylistRepository
import com.lucassimao.androidteststudy.presentation.fragment.PlaylistViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class PlaylistModule {

    @Provides
    fun providePlaylistApi(retrofit: Retrofit): PlaylistAPI =
        retrofit.create(PlaylistAPI::class.java)

    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun providePlaylistService(api: PlaylistAPI): PlaylistService = PlaylistService(api)

    @Provides
    fun providePlaylistMapper(): PlaylistMapper = PlaylistMapper()

    @Provides
    fun providePlaylistRepository(
        service: PlaylistService,
        mapper: PlaylistMapper
    ): PlaylistRepository = PlaylistRepositoryImpl(service, mapper)

    @Provides
    fun providePlaylistViewModel(
        repository: PlaylistRepository
    ): PlaylistViewModel = PlaylistViewModel(repository)

}