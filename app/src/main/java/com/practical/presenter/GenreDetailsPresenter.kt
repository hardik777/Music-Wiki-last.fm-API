package com.practical.presenter

import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import com.practical.APIclient.API
import com.practical.model.GenreDetailsResponse
import com.practical.model.GenreCategoryResponse
import com.practical.model.MovieResponse
import com.practical.view.AlbumView
import com.practical.view.GenreCategoryView
import com.practical.view.GenreDetailsView
import com.practical.view.MovieView
import org.json.JSONObject

/**
 * Created by Hardik(22/01/2021)
 * Get Movie list from API
 */
class GenreDetailsPresenter(internal var view: GenreDetailsView) {

    fun getGenreDetails(genre: String) {
        API()
            .getGenreDetails(genre)
            .getObjectObservable(GenreDetailsResponse::class.java)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<GenreDetailsResponse> {
                override fun onSubscribe(d: Disposable) {
                    view.showProgress()
                }

                override fun onNext(data: GenreDetailsResponse) {
                    view.successGenreDetails(data)
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