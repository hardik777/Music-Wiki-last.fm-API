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
import com.practical.controller.*
import com.practical.model.Album
import com.practical.model.AlbumDetailsResponse
import com.practical.model.TagAlbum
import com.practical.presenter.AlbumDetailsPresenter
import com.practical.view.AlbumDetailsView
import kotlinx.android.synthetic.main.activity_album_details.*
import kotlinx.android.synthetic.main.fragment_album.*

/**
 * Created by Hardik(22/01/2021)
 */
class AlbumDetailsActivity : AppCompatActivity(), AlbumDetailsView, View.OnClickListener {

    lateinit var context: Activity
    lateinit var album: Album
    lateinit var adapter: AlbumDetailsAdapter
    lateinit var presenter: AlbumDetailsPresenter
    var linearLayoutManager: LinearLayoutManager? = null
    var arrayList = ArrayList<TagAlbum>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_details)
        context = this

        if (intent.getSerializableExtra(INTENT_DATA) != null) {

            val hashMap =
                intent.getSerializableExtra(INTENT_DATA) as (java.util.HashMap<String, Any>)

            album = Gson().fromJson(hashMap["album"].toString(), Album::class.java)
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
        adapter = AlbumDetailsAdapter(this, arrayList)
        rvCategory.adapter = adapter

        presenter = AlbumDetailsPresenter(this)
        presenter.getAlbumDetail(album.artist.name, album.name)
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
    override fun successAlbumDetails(data: AlbumDetailsResponse) {
        hideProgress()

        LoadImage(data.album.image[data.album.image.size - 1].text, imgBackdrop)

        tvMoviesTitle.text = data.album.name
        tvArtist.text = data.album.artist
        tvAlbumDetails.text = Html.fromHtml(data.album.wiki.summary)

        adapter = AlbumDetailsAdapter(this, data.album.tags.tag)
        rvCategory.adapter = adapter
    }

}