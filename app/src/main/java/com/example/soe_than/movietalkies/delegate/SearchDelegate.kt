package com.example.soe_than.movietalkies.delegate

import com.example.soe_than.movietalkies.data.Vo.SearchVo

interface SearchDelegate {

   fun  onTapSearchResult(searchVo: SearchVo)
}