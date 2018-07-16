package com.example.soe_than.movietalkies.data.response

import android.graphics.Movie
import com.example.soe_than.movietalkies.data.Vo.TopRatedVo
import com.google.gson.annotations.SerializedName

data class TopRatedResponse(@SerializedName("results")
                       var topRatedVo: List<TopRatedVo>) {
}