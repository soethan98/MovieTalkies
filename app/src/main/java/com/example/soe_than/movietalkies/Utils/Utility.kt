package com.example.soe_than.movietalkies.Utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.bumptech.glide.load.engine.bitmap_recycle.IntegerArrayAdapter
import com.example.soe_than.movietalkies.Utils.Constants.EMPTY
import com.example.soe_than.movietalkies.Utils.Constants.SITE_YOUTUBE
import com.example.soe_than.movietalkies.Utils.Constants.YOUTUBE_THUMBNAIL_URL
import com.example.soe_than.movietalkies.Utils.Constants.YOUTUBE_VIDEO_URL
import com.example.soe_than.movietalkies.data.Vo.GenresVo
import com.example.soe_than.movietalkies.data.Vo.TrailerVo

object Utility {

    fun isNetworkAvailable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var activeNetworkInfo: NetworkInfo? = null
        activeNetworkInfo = cm.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
    }

    fun getUrl(video: TrailerVo): String {
        if (SITE_YOUTUBE.equals(video.site, true)) {
            return String.format(YOUTUBE_VIDEO_URL, video.key)
        } else {
            return EMPTY
        }

    }

    fun getThumbnailUrl(video: TrailerVo): String {
        if (Constants.SITE_YOUTUBE.equals(video.site, true)) {
            return String.format(YOUTUBE_THUMBNAIL_URL, video.key);
        } else {
            return EMPTY;
        }
    }


    fun getGeneresnames(code: Int): String {
        var type = ""
        when (code) {
            28 -> type = "Action"
            12 -> type = "Adventure"
            16 -> type = "Animation"
            35 -> type = "Comedy"
            80 -> type = "Crime"
            99 -> type = "Documentary"
            18 -> type = "Drama"
            10751 -> type = "Family"
            14 -> type = "Fantasy"
            36 -> type = "History"
            27 -> type = "Horror"
            10402 -> type = "Music"
            9648 -> type = "Mystery"
            10749 -> type = "Romance"
            878 -> type = "Sci-Fic"
            10770 -> type = "TV Movie"
            53 -> type = "Thriller"
            10752 -> type = "War"
            37 -> type = "Western"
            else -> type=""


        }
        return type
    }

    fun setGenresTypeForMovie(ids:List<String>):ArrayList<String>
    {

         var idsList: ArrayList<String> = ArrayList<String>()
        for (id in ids)
        {
          idsList.add(getGeneresnames(id.toInt()))

        }
        return idsList
    }



    fun setGenresTypeForMovie1(ids:List<GenresVo>):ArrayList<String>
    {

        var idsList: ArrayList<String> = ArrayList<String>()
        for (id in ids)
        {
            idsList.add(id.name)

        }
        return idsList
    }
}