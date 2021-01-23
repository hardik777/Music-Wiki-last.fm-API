package com.practical.presenter

import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import com.practical.APIclient.API
import com.practical.model.ArtistResponse
import com.practical.model.GenreCategoryResponse
import com.practical.model.MovieResponse
import com.practical.view.AlbumView
import com.practical.view.ArtistView
import com.practical.view.GenreCategoryView
import com.practical.view.MovieView
import org.json.JSONObject

/**
 * Created by Hardik(22/01/2021)
 * Get Movie list from API
 */
class ArtistPresenter(internal var view: ArtistView) {

    fun getArtistList(genre: String) {
        API()
            .getArtistList(genre)
            .getObjectObservable(ArtistResponse::class.java)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ArtistResponse> {
                override fun onSubscribe(d: Disposable) {
                    view.showProgress()
                }

                override fun onNext(data: ArtistResponse) {
                    view.successArtistList(data)
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