package com.soethan.movietalkies.data.response

import com.soethan.movietalkies.data.Vo.TopRatedVo
import com.google.gson.annotations.SerializedName

data class TopRatedResponse(@SerializedName("results")
                       var topRatedVo: List<TopRatedVo>) {
}