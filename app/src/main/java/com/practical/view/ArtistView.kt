package com.practical.view

import com.practical.model.AlbumResponse
import com.practical.model.ArtistResponse
import com.practical.model.GenreCategoryResponse

/**
 * Created by Hardik(22/01/2021)
 */
interface ArtistView {

    fun showProgress()

    fun hideProgress()

    fun successArtistList(data: ArtistResponse)

    fun error(e: Throwable)

}