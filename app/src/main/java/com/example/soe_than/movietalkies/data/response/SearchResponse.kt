package com.example.soe_than.movietalkies.data.response

import com.example.soe_than.movietalkies.data.Vo.PopularVo
import com.example.soe_than.movietalkies.data.Vo.SearchVo
import com.google.gson.annotations.SerializedName

data class SearchResponse(@SerializedName("results")
                          var searchResult: List<SearchVo>){

}