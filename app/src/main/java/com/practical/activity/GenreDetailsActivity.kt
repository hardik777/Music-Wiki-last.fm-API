package com.practical.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.practical.R
import com.practical.adapter.AlbumAdapter
import com.practical.adapter.MyAdapter
import com.practical.controller.CloseLoader
import com.practical.controller.INTENT_DATA
import com.practical.controller.OpenLoader
import com.practical.controller.ToastMessage
import com.practical.model.*
import com.practical.presenter.AlbumPresenter
import com.practical.presenter.GenreDetailsPresenter
import com.practical.view.GenreDetailsView
import com.practical.view.TrackView
import kotlinx.android.synthetic.main.activity_genre_details.*
import kotlinx.android.synthetic.main.fragment_album.*

class GenreDetailsActivity : AppCompatActivity(), GenreDetailsView, View.OnClickListener {

    lateinit var tag: Tag
    lateinit var presenter: GenreDetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genre_details)

        if (intent.getSerializableExtra(INTENT_DATA) != null) {

            val hashMap =
                intent.getSerializableExtra(INTENT_DATA) as (java.util.HashMap<String, Any>)

            tag = Gson().fromJson(hashMap["genre"].toString(), Tag::class.java)

            txtGenre.text = tag.name
            txtDetail.text = tag.name
        }

        initLoad()
    }

    private fun initLoad() {
        imgBack.setOnClickListener(this)
        
        presenter = GenreDetailsPresenter(this)
        presenter.getGenreDetails(tag.name)

        tabLayout!!.addTab(tabLayout!!.newTab().setText("Album"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Artist"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Track"))
        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = MyAdapter(this, supportFragmentManager, tabLayout!!.tabCount, tag.name)
        viewPager!!.adapter = adapter

        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    /**
     * Get Data from API
     */
    override fun successGenreDetails(data: GenreDetailsResponse) {
        txtGenre.text = tag.name
        txtDetail.text = Html.fromHtml(data.tag.wiki.summary)
    }

    /**
     * Show Progress
     */
    override fun showProgress() {
        OpenLoader()
    }

    /**
     * Hide Progress
     */
    override fun hideProgress() {
        CloseLoader()
    }

    /**
     * Error Handling
     */
    override fun error(e: Throwable) {
        ToastMessage(getString(R.string.error))
    }

    override fun onClick(view: View) {
        when (view) {
            imgBack -> onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}