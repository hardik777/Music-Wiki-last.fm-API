package com.practical.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Hardik(22/01/2021)
 */
data class AlbumResponse(
    @SerializedName("albums") val albums : Albums
)

data class Albums (

    @SerializedName("album") val album : List<Album>
)

data class Album (

    @SerializedName("name") val name : String,
    @SerializedName("mbid") val mbid : String,
    @SerializedName("url") val url : String,
    @SerializedName("artist") val artist : Artist,
    @SerializedName("image") val image : List<Image>
)


data class Artist (

    @SerializedName("name") val name : String,
    @SerializedName("mbid") val mbid : String,
    @SerializedName("url") val url : String
)

data class Image (

    @SerializedName("#text") val text : String,
    @SerializedName("size") val size : String
)