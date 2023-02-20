package com.example.flixster2


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class TvShows (

    @SerializedName("name")
    var tvName: String? = null,

    @SerializedName("overview")
    var tvOverview: String? = null,

    @SerializedName("poster_path")
    var tvPoster_path : String? = null,

    @SerializedName("id")
    var id : String? = null

): java.io.Serializable