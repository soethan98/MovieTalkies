package com.example.soe_than.movietalkies.data.response

import android.graphics.Movie
import com.example.soe_than.movietalkies.data.Vo.PopularVo
import com.google.gson.annotations.SerializedName

class PopularResponse(@SerializedName("results")
                      var popularResponse: List<PopularVo>) {
}