package com.practical.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Hardik(22/01/2021)
 */

data class TrackDetailsResponse(
    @SerializedName("track") val track: TrackDataDetails
)

data class ImageTrackDetails(

    @SerializedName("#text") val text: String,
    @SerializedName("size") val size: String
)

data class TrackTrack(

    @SerializedName("name") val name: String,
    @SerializedName("mbid") val mbid: String,
    @SerializedName("url") val url: String
)

data class AlbumTrackDetails(

    @SerializedName("artist") val artist: String,
    @SerializedName("title") val title: String,
    @SerializedName("mbid") val mbid: String,
    @SerializedName("url") val url: String,
    @SerializedName("image") val image: List<ImageTrackDetails>
)

data class StreamableTrack(

    @SerializedName("#text") val text: Int,
    @SerializedName("fulltrack") val fulltrack: Int
)

data class TagTrack(

    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)

data class Toptags(

    @SerializedName("tag") val tag: List<TagTrack>
)

data class TrackDataDetails(

    @SerializedName("name") val name: String,
    @SerializedName("mbid") val mbid: String,
    @SerializedName("url") val url: String,
    @SerializedName("duration") val duration: Int,
    @SerializedName("streamable") val streamable: StreamableTrack,
    @SerializedName("listeners") val listeners: Int,
    @SerializedName("playcount") val playcount: Int,
    @SerializedName("artist") val artist: TrackTrack,
    @SerializedName("album") val album: AlbumTrackDetails,
    @SerializedName("toptags") val toptags: Toptags,
    @SerializedName("wiki") val wiki: WikiData
)

data class WikiData(

    @SerializedName("published") val published: String,
    @SerializedName("summary") val summary: String,
    @SerializedName("content") val content: String
)