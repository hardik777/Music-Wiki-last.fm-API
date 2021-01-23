package com.practical.activity

import android.app.Activity
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.practical.R
import com.practical.adapter.AlbumAdapter
import com.practical.adapter.AlbumDetailsAdapter
import com.practical.adapter.TrackDetailsAdapter
import com.practical.controller.*
import com.practical.model.*
import com.practical.presenter.AlbumDetailsPresenter
import com.practical.presenter.TrackDetailsPresenter
import com.practical.view.AlbumDetailsView
import com.practical.view.TrackDetailsView
import kotlinx.android.synthetic.main.activity_album_details.*
import kotlinx.android.synthetic.main.fragment_album.*

/**
 * Created by Hardik(22/01/2021)
 */
class TrackDetailsActivity : AppCompatActivity(), TrackDetailsView, View.OnClickListener {

    lateinit var context: Activity
    lateinit var trackData: TrackData
    lateinit var adapter: TrackDetailsAdapter
    lateinit var presenter: TrackDetailsPresenter
    var linearLayoutManager: LinearLayoutManager? = null
    var arrayList = ArrayList<TagTrack>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track_details)
        context = this

        if (intent.getSerializableExtra(INTENT_DATA) != null) {

            val hashMap =
                intent.getSerializableExtra(INTENT_DATA) as (java.util.HashMap<String, Any>)

            trackData = Gson().fromJson(hashMap["track"].toString(), TrackData::class.java)
        }
        initLoad()
    }

    /**
     * Initialization
     */
    private fun initLoad() {
        imgBack.setOnClickListener(this)

        linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager!!.orientation = LinearLayoutManager.HORIZONTAL
        rvCategory.layoutManager = linearLayoutManager
        adapter = TrackDetailsAdapter(this, arrayList)
        rvCategory.adapter = adapter

        presenter = TrackDetailsPresenter(this)
        presenter.getTrackDetail(trackData.artist.name, trackData.name)
    }

    /**
     * OnClick of views
     */
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.imgBack -> {
                onBackPressed()
            }
        }
    }

    /**
     * Show Progress
     */
    override fun showProgress() {
        (context as Activity).OpenLoader()
    }

    /**
     * Hide Progress
     */
    override fun hideProgress() {
        (context as Activity).CloseLoader()
    }

    /**
     * Error Handling
     */
    override fun error(e: Throwable) {
        (context as Activity).ToastMessage(getString(R.string.error))
    }

    /**
     * Get Data from API
     */
    override fun successTrackDetails(data: TrackDetailsResponse) {
        hideProgress()

        LoadImage(data.track.album.image[data.track.album.image.size - 1].text, imgBackdrop)

        tvMoviesTitle.text = data.track.name
        tvArtist.text = data.track.artist.name
        tvAlbumDetails.text = Html.fromHtml(data.track.wiki.summary)

        adapter = TrackDetailsAdapter(this, data.track.toptags.tag)
        rvCategory.adapter = adapter
    }

}