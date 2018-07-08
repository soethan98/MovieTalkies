package com.example.soe_than.movietalkies.data.response

import android.graphics.Movie
import com.example.soe_than.movietalkies.data.Vo.NowShowingVo
import com.google.gson.annotations.SerializedName

class NowShowingResponse(@SerializedName("results")
                         var nowShowingResponse: List<NowShowingVo>) {
}