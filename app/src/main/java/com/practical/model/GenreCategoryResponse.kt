package com.practical.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Hardik(22/01/2021)
 */
data class GenreCategoryResponse(
    @SerializedName("tags") val tags: Tags
)

data class Tag(

    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String,
    @SerializedName("reach") val reach: Int,
    @SerializedName("taggings") val taggings: Int,
    @SerializedName("streamable") val streamable: Int
)

data class Tags(

    @SerializedName("tag") val tag: List<Tag>,
    @SerializedName("@attr") val attr: attr
)

data class attr(
    @SerializedName("page") val page: Int,
    @SerializedName("perPage") val perPage: Int,
    @SerializedName("totalPages") val totalPages: Int,
    @SerializedName("total") val total: Int
)