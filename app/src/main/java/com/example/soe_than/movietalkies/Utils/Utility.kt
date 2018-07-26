package com.example.soe_than.movietalkies.Utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.soe_than.movietalkies.Utils.Constants.EMPTY
import com.example.soe_than.movietalkies.Utils.Constants.SITE_YOUTUBE
import com.example.soe_than.movietalkies.Utils.Constants.YOUTUBE_THUMBNAIL_URL
import com.example.soe_than.movietalkies.Utils.Constants.YOUTUBE_VIDEO_URL
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
}