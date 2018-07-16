package com.example.soe_than.movietalkies.delegate

import com.example.soe_than.movietalkies.data.Vo.NowShowingVo
import com.example.soe_than.movietalkies.data.Vo.TopRatedVo

interface TopRatedDelegate {

    fun onTapTopRated(topRatedVo: TopRatedVo)

}