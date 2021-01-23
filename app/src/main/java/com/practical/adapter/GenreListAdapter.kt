package com.practical.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.practical.R
import com.practical.model.ModelMovies
import kotlinx.android.synthetic.main.raw_genre.view.*

/**
 * Created by Hardik(22/01/2021)
 */
class GenreListAdapter(
    var activity: Activity,
    private var arrayList: ArrayList<ModelMovies>
) : RecyclerView.Adapter<GenreListAdapter.ViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v: View? = null

        v = LayoutInflater.from(parent.context)
            .inflate(R.layout.raw_genre, parent, false)

        return ViewHolder(v!!)
    }

    /**
     * Get total item Count
     */
    override fun getItemCount(): Int {
        return arrayList.size
    }

    /**
     * Set data in views
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val data = arrayList[position]

        holder.tvGenre.text = data.genre

        val linearLayoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        holder.itemView.rvSub.layoutManager = linearLayoutManager

        holder.itemView.rvSub.apply {
            layoutManager = linearLayoutManager
            adapter = MovieListAdapter(activity, arrayList[position].childItem)
            setRecycledViewPool(viewPool)
        }
    }

    /**
     * declare views
     */
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvGenre = view.tvGenre
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
}