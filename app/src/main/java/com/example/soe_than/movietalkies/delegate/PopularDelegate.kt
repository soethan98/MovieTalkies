package com.example.soe_than.movietalkies.delegate

import com.example.soe_than.movietalkies.data.Vo.NowShowingVo
import com.example.soe_than.movietalkies.data.Vo.PopularVo

interface PopularDelegate {

    fun onTapPopular(popularVo:PopularVo)

}