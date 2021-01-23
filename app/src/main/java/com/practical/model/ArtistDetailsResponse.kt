package com.practical.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Hardik(22/01/2021)
 */

data class ArtistDetailsResponse(
    @SerializedName("artist") val artist: ArtistDetailsData
)

data class Link(

    @SerializedName("#text") val text : String,
    @SerializedName("rel") val rel: String,
    @SerializedName("href") val href: String
)

data class Links(

    @SerializedName("link") val link: Link
)

data class Similar(

    @SerializedName("artist") val artist: List<ArtistDetailsData>
)

data class Stats(

    @SerializedName("listeners") val listeners: Int,
    @SerializedName("playcount") val playcount: Int
)


data class TagArtist(

    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)

data class TagsArtist(

    @SerializedName("tag") val tag: List<TagArtist>
)

data class ImageArtist(

    @SerializedName("#text") val text : String,
    @SerializedName("size") val size: String
)

data class Bio(

    @SerializedName("links") val links: Links,
    @SerializedName("published") val published: String,
    @SerializedName("summary") val summary: String,
    @SerializedName("content") val content: String
)


data class ArtistDetailsData(

    @SerializedName("name") val name: String,
    @SerializedName("mbid") val mbid: String,
    @SerializedName("url") val url: String,
    @SerializedName("image") val image: List<ImageArtist>,
    @SerializedName("streamable") val streamable: Int,
    @SerializedName("ontour") val ontour: Int,
    @SerializedName("stats") val stats: Stats,
    @SerializedName("similar") val similar: Similar,
    @SerializedName("tags") val tags: TagsArtist,
    @SerializedName("bio") val bio: Bio
)