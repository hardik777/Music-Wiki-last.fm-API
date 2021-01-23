package com.practical.activity

import android.app.Activity
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.practical.R
import com.practical.adapter.ArtistDetailsAdapter
import com.practical.controller.*
import com.practical.model.*
import com.practical.presenter.ArtistDetailsPresenter
import com.practical.view.ArtistDetailsView
import kotlinx.android.synthetic.main.activity_album_details.*
import kotlinx.android.synthetic.main.activity_artists_details.imgBack
import kotlinx.android.synthetic.main.activity_artists_details.imgBackdrop
import kotlinx.android.synthetic.main.activity_artists_details.rvCategory
import kotlinx.android.synthetic.main.activity_artists_details.tvMoviesTitle

/**
 * Created by Hardik(22/01/2021)
 */
class ArtistsDetailsActivity : AppCompatActivity(), ArtistDetailsView, View.OnClickListener {

    lateinit var context: Activity
    lateinit var artistData: ArtistData
    lateinit var adapter: ArtistDetailsAdapter
    lateinit var presenter: ArtistDetailsPresenter
    var linearLayoutManager: LinearLayoutManager? = null
    var arrayList = ArrayList<TagArtist>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artists_details)
        context = this

        if (intent.getSerializableExtra(INTENT_DATA) != null) {

            val hashMap =
                intent.getSerializableExtra(INTENT_DATA) as (java.util.HashMap<String, Any>)

            artistData = Gson().fromJson(hashMap["artist"].toString(), ArtistData::class.java)
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
        adapter = ArtistDetailsAdapter(this, arrayList)
        rvCategory.adapter = adapter

        presenter = ArtistDetailsPresenter(this)
        presenter.getArtistDetail(artistData.name)
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
    override fun successArtistDetails(data: ArtistDetailsResponse) {
        hideProgress()

        LoadImage(data.artist.image[data.artist.image.size - 1].text, imgBackdrop)

        tvMoviesTitle.text = data.artist.name
        tvArtist.text = data.artist.name
        tvAlbumDetails.text = Html.fromHtml(data.artist.bio.summary)

        adapter = ArtistDetailsAdapter(this, data.artist.tags.tag)
        rvCategory.adapter = adapter
    }

}