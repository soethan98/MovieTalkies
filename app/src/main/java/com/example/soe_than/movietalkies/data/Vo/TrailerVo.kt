package com.example.soe_than.movietalkies.data.Vo

import com.google.gson.annotations.SerializedName

data class TrailerVo(@SerializedName("id")
                     val id: String,

                     @SerializedName("key")
                     val key: String,

                     @SerializedName("name")
                     val name: String,

                     @SerializedName("site")
                     val site: String,

                     @SerializedName("size")
                     val size: Int,

                     @SerializedName("type")
                     val type: String)