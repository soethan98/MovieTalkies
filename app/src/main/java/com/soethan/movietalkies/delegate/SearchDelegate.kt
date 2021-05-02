package com.soethan.movietalkies.delegate

import com.soethan.movietalkies.data.Vo.SearchVo

interface SearchDelegate {

   fun  onTapSearchResult(searchVo: SearchVo)
}