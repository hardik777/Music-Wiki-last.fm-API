package com.practical.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Hardik(22/01/2021)
 */

data class AlbumDetailsResponse(
    @SerializedName("album") val album: AlbumData
)

data class AlbumData(

    @SerializedName("name") val name: String,
    @SerializedName("artist") val artist: String,
    @SerializedName("mbid") val mbid: String,
    @SerializedName("url") val url: String,
    @SerializedName("image") val image: List<ImageAlbum>,
    @SerializedName("listeners") val listeners: Int,
    @SerializedName("playcount") val playcount: Int,
    @SerializedName("tracks") val tracks: TracksAlbum,
    @SerializedName("tags") val tags: TagsAlbum,
    @SerializedName("wiki") val wiki: Wiki
)

data class ArtistAlbum(

    @SerializedName("name") val name: String,
    @SerializedName("mbid") val mbid: String,
    @SerializedName("url") val url: String
)


data class ImageAlbum(

    @SerializedName("#text") val text : String,
    @SerializedName("size") val size: String
)

data class TagsAlbum(

    @SerializedName("tag") val tag: List<TagAlbum>
)

data class TagAlbum(

    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)

data class StreamableAlbum(

    @SerializedName("#text") val text : Int,
    @SerializedName("fulltrack") val fulltrack: Int
)

data class Track(

    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String,
    @SerializedName("duration") val duration: Int,
    @SerializedName("@attr") val attr : attr,
    @SerializedName("streamable") val streamable: StreamableAlbum,
    @SerializedName("artist") val artist: ArtistAlbum
)

data class TracksAlbum(

    @SerializedName("track") val track: List<Track>
)

data class Wiki(

    @SerializedName("published") val published: String,
    @SerializedName("summary") val summary: String,
    @SerializedName("content") val content: String
)
