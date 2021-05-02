package com.soethan.movietalkies.data.response

import com.soethan.movietalkies.data.Vo.TrailerVo
import com.google.gson.annotations.SerializedName

data class TrailerResponse(@SerializedName("id")
                           val id: Int,

                           @SerializedName("results")
                           val results: List<TrailerVo>) {
}