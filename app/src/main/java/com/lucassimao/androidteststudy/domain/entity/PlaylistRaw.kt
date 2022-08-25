package com.lucassimao.androidteststudy.domain.entity

import com.google.gson.annotations.SerializedName

data class PlaylistRaw(
    @SerializedName("category")
    val category: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)