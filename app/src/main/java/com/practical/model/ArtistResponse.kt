package com.practical.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Hardik(22/01/2021)
 */
data class ArtistResponse(
    @SerializedName("topartists") val topartists: Topartists
)

data class Topartists(

    @SerializedName("artist") val artist: List<ArtistData>
)

data class ArtistImage(

    @SerializedName("#text") val text: String,
    @SerializedName("size") val size: String
)

data class ArtistData(

    @SerializedName("name") val name: String,
    @SerializedName("mbid") val mbid: String,
    @SerializedName("url") val url: String,
    @SerializedName("streamable") val streamable: Int,
    @SerializedName("image") val image: List<ArtistImage>
)