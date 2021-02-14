package com.example.soe_than.movietalkies.data.response

import com.example.soe_than.movietalkies.data.Vo.PopularVo
import com.google.gson.annotations.SerializedName

data class PopularResponse(
    @SerializedName("results")
    var popularVo: List<PopularVo>
)
