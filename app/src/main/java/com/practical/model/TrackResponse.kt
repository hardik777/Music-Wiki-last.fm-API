package com.practical.model

import com.google.gson.annotations.SerializedName

data class TrackResponse(
    @SerializedName("tracks") val tracks: Tracks
)

data class Tracks (
    @SerializedName("track") val track : List<TrackData>
)

data class TrackData (

    @SerializedName("name") val name : String,
    @SerializedName("duration") val duration : Int,
    @SerializedName("mbid") val mbid : String,
    @SerializedName("url") val url : String,
    @SerializedName("streamable") val streamable : Streamable,
    @SerializedName("artist") val artist : ArtistTrack,
    @SerializedName("image") val image : List<ImageTrack>
)

data class Streamable (

    @SerializedName("#text") val text : Int,
    @SerializedName("fulltrack") val fulltrack : Int
)

data class ImageTrack (

    @SerializedName("#text") val text : String,
    @SerializedName("size") val size : String
)

data class ArtistTrack (

    @SerializedName("name") val name : String,
    @SerializedName("mbid") val mbid : String,
    @SerializedName("url") val url : String
)