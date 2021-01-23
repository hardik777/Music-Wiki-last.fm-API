package com.practical.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Hardik(22/01/2021)
 */
data class Movie(

    @SerializedName("backdrop") val backdrop: String,
    @SerializedName("cast") val cast: List<String>,
    @SerializedName("classification") val classification: String,
    @SerializedName("genres") val genres: List<String>,
    @SerializedName("id") val id: String,
    @SerializedName("imdb_rating") val imdb_rating: Double,
    @SerializedName("length") val length: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster") val poster: String,
    @SerializedName("released_on") val released_on: String,
    @SerializedName("slug") val slug: String,
    @SerializedName("title") val title: String
)