package com.practical.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Hardik(22/01/2021)
 */
data class MovieResponse(
    @SerializedName("movies") val movies : List<Movie>
)