package com.example.soe_than.movietalkies.delegate

import com.example.soe_than.movietalkies.data.Vo.NowShowingVo

interface NowShowingDelegate {

    fun onTapNowShowing(nowShowingVo: NowShowingVo)
}