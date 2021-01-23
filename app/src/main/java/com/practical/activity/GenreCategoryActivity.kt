package com.practical.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable.ConstantState
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.practical.R
import com.practical.adapter.GenreCategoryAdapter
import com.practical.controller.CloseLoader
import com.practical.controller.OpenLoader
import com.practical.controller.ToastMessage
import com.practical.model.GenreCategoryResponse
import com.practical.model.Tag
import com.practical.presenter.GenreCategoryPresenter
import com.practical.view.GenreCategoryView
import kotlinx.android.synthetic.main.activity_genre_category.*


class GenreCategoryActivity : AppCompatActivity(), GenreCategoryView, View.OnClickListener {

    lateinit var presenter: GenreCategoryPresenter
    lateinit var context: Context
    lateinit var adapter: GenreCategoryAdapter
    var gridLayoutManager: GridLayoutManager? = null
    var arrayList = ArrayList<Tag>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genre_category)

        initLoad()
    }

    /**
     * Initialization
     */
    private fun initLoad() {
        context = this

        imgMore.setOnClickListener(this)
        gridLayoutManager = GridLayoutManager(context, 3)
        rvGenreCategory.layoutManager = gridLayoutManager
        adapter = GenreCategoryAdapter(this, arrayList)
        rvGenreCategory.adapter = adapter

        presenter = GenreCategoryPresenter(this)
        presenter.getGenreCategory()
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

    var flagDown: Boolean = true

    override fun onClick(view: View) {
        when (view) {
            imgMore -> {
                if (flagDown) {
                    flagDown = false
                    adapter.limit = arrayList.size
                    adapter.notifyDataSetChanged()
                    imgMore.setImageResource(R.drawable.ic_uparrow)
                } else {
                    flagDown = true
                    adapter.limit = 10
                    adapter.notifyDataSetChanged()
                    imgMore.setImageResource(R.drawable.ic_downarrow)
                }
            }
        }
    }

    /**
     * Get Data from API
     */
    override fun successGenreCategoryList(data: GenreCategoryResponse) {
        arrayList = data.tags.tag as ArrayList<Tag>
        adapter = GenreCategoryAdapter(this, data.tags.tag)
        rvGenreCategory.adapter = adapter
    }

}