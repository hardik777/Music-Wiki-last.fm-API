package com.practical.view

import com.practical.model.AlbumDetailsResponse
import com.practical.model.ArtistDetailsResponse
import com.practical.model.GenreCategoryResponse

/**
 * Created by Hardik(22/01/2021)
 */
interface ArtistDetailsView {

    fun showProgress()

    fun hideProgress()

    fun successArtistDetails(data: ArtistDetailsResponse)

    fun error(e: Throwable)

}