package com.soethan.movietalkies.data.response

import com.soethan.movietalkies.data.Vo.NowShowingVo
import com.google.gson.annotations.SerializedName

data class NowShowingResponse(@SerializedName("results")
                         var nowShowingVo: List<NowShowingVo>) {
}