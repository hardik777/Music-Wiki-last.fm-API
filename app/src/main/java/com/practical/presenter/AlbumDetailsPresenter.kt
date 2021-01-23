package com.practical.presenter

import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import com.practical.APIclient.API
import com.practical.model.AlbumDetailsResponse
import com.practical.model.MovieResponse
import com.practical.view.AlbumDetailsView
import com.practical.view.GenreCategoryView
import com.practical.view.MovieView

/**
 * Created by Hardik(22/01/2021)
 * Get Movie list from API
 */
class AlbumDetailsPresenter(internal var view: AlbumDetailsView) {

    fun getAlbumDetail(artist: String, album: String) {
        API()
            .getAlbumDetail(artist, album)
            .getObjectObservable(AlbumDetailsResponse::class.java)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<AlbumDetailsResponse> {
                override fun onSubscribe(d: Disposable) {
                    view.showProgress()
                }

                override fun onNext(data: AlbumDetailsResponse) {
                    view.successAlbumDetails(data)
                }

                override fun onError(e: Throwable) {
                    view.error(e)
                    view.hideProgress()
                }

                override fun onComplete() {
                    view.hideProgress()
                }
            })
    }

}