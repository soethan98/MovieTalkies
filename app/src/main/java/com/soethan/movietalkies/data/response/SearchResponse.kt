package com.soethan.movietalkies.data.response

import com.soethan.movietalkies.data.Vo.SearchVo
import com.google.gson.annotations.SerializedName

data class SearchResponse(@SerializedName("results")
                          var searchResult: List<SearchVo>){

}