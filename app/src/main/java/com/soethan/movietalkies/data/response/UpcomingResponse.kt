package com.soethan.movietalkies.data.response

import com.soethan.movietalkies.data.Vo.UpComingVo
import com.google.gson.annotations.SerializedName

data class UpcomingResponse(@SerializedName("results")
                            var upComingVo: List<UpComingVo>) {
}