package com.practical.model

import com.google.gson.annotations.SerializedName

data class GenreDetailsResponse(
    @SerializedName("tag") val tag : TagData
)

data class TagData (

    @SerializedName("name") val name : String,
    @SerializedName("total") val total : Int,
    @SerializedName("reach") val reach : Int,
    @SerializedName("wiki") val wiki : WikiTagData
)

data class WikiTagData (

    @SerializedName("summary") val summary : String,
    @SerializedName("content") val content : String
)
