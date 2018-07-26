package com.example.soe_than.movietalkies.data.response

import com.example.soe_than.movietalkies.data.Vo.TrailerVo
import com.google.gson.annotations.SerializedName

data class TrailerResponse(@SerializedName("id")
                           val id: Int,

                           @SerializedName("results")
                           val results: List<TrailerVo>) {
}