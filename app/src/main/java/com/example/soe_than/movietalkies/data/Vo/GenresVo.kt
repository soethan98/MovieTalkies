package com.example.soe_than.movietalkies.data.Vo

import com.google.gson.annotations.SerializedName

data class GenresVo(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)
