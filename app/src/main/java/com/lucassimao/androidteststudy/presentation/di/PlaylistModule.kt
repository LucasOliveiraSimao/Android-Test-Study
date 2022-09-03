package com.lucassimao.androidteststudy.presentation.di

import com.lucassimao.androidteststudy.data.api.PlaylistAPI
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

}