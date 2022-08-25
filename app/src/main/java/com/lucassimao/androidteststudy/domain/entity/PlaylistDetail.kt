package com.lucassimao.androidteststudy.domain.entity

import com.google.gson.annotations.SerializedName

data class PlaylistDetail(
    @SerializedName("details")
    val details: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)