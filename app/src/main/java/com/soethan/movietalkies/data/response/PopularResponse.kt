package com.soethan.movietalkies.data.response

import com.soethan.movietalkies.data.Vo.PopularVo
import com.google.gson.annotations.SerializedName

data class PopularResponse(@SerializedName("results")
                      var popularVo: List<PopularVo>) {
}