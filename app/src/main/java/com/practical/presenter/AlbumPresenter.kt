package com.practical.presenter

import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import com.practical.APIclient.API
import com.practical.model.AlbumResponse
import com.practical.model.GenreCategoryResponse
import com.practical.model.MovieResponse
import com.practical.view.AlbumView
import com.practical.view.GenreCategoryView
import com.practical.view.MovieView
import org.json.JSONObject

/**
 * Created by Hardik(22/01/2021)
 * Get Movie list from API
 */
class AlbumPresenter(internal var view: AlbumView) {

    fun getAlbumList(genre: String) {
        API()
            .getAlbumList(genre)
            .getObjectObservable(AlbumResponse::class.java)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<AlbumResponse> {
                override fun onSubscribe(d: Disposable) {
                    view.showProgress()
                }

                override fun onNext(data: AlbumResponse) {
                    view.successAlbumList(data)
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