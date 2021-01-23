package com.practical.view

import com.practical.model.AlbumResponse
import com.practical.model.ArtistResponse
import com.practical.model.GenreCategoryResponse
import com.practical.model.TrackResponse

/**
 * Created by Hardik(22/01/2021)
 */
interface TrackView {

    fun showProgress()

    fun hideProgress()

    fun successTrackList(data: TrackResponse)

    fun error(e: Throwable)

}