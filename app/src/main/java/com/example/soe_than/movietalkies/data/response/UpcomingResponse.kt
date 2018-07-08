package com.example.soe_than.movietalkies.data.response

import android.graphics.Movie
import com.example.soe_than.movietalkies.data.Vo.UpComingVo
import com.google.gson.annotations.SerializedName

data class UpcomingResponse(@SerializedName("results")
                            var upComingVo: List<UpComingVo>) {
}