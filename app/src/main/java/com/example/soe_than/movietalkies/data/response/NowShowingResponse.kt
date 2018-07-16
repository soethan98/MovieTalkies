package com.example.soe_than.movietalkies.data.response

import android.graphics.Movie
import com.example.soe_than.movietalkies.data.Vo.NowShowingVo
import com.google.gson.annotations.SerializedName

data class NowShowingResponse(@SerializedName("results")
                         var nowShowingVo: List<NowShowingVo>) {
}